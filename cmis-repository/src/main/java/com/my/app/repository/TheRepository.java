package com.my.app.repository;

import com.my.app.rest.repository.IRepository;
import com.my.app.rest.repository.dto.Repository;
import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Property;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisUnauthorizedException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.HashMap;
import java.util.Map;

@Component(service = IRepository.class, immediate = true)
public class TheRepository implements IRepository
{
    private static final String SERVICE_URL = "<THE_URL>/cmis/versions/1.1/browser"; // TODO USE CONFIG

    @Reference(cardinality = ReferenceCardinality.MANDATORY, policy = ReferencePolicy.STATIC)
    private SessionFactory _sessionFactory;
    private Session _session;

    protected Session getSession() throws RepositoryUnauthorizedException
    {
        if(_session == null)
        {
            // default factory implementation
            Map<String, String> params = new HashMap<String, String>();

            params.put(SessionParameter.REPOSITORY_ID, "repoID");
            // user credentials
            params.put(SessionParameter.USER, "theUser");
            params.put(SessionParameter.PASSWORD, "thePassword");

            // connection settings
            params.put(SessionParameter.BROWSER_URL, SERVICE_URL);
            params.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
            params.put(SessionParameter.BROWSER_SUCCINCT, Boolean.toString(true));

            try
            {
                _session = _sessionFactory.createSession(params);
                _session.getDefaultContext().setCacheEnabled(true);
            }
            catch(CmisUnauthorizedException cue)
            {
                throw new RepositoryUnauthorizedException(cue.getMessage(), cue);
            }
        }

        return _session;
    }

    private CmisObject getNodeByPath(String path) throws RepositoryItemNotFoundException, RepositoryUnauthorizedException
    {
        try
        {
            return getSession().getObjectByPath(path);
        }
        catch(CmisObjectNotFoundException confe)
        {
            throw new RepositoryItemNotFoundException("File/Folder with path " + path + " not found", confe);
        }
    }

    public boolean exists(String path) throws RepositoryUnauthorizedException
    {
        try
        {
            CmisObject node = getNodeByPath(path);
            return node != null;
        }
        catch (RepositoryItemNotFoundException e)
        {
            return false;
        }
    }

    @Override
    public Repository getRepository(String repoName) throws RepositoryUnauthorizedException, RepositoryItemNotFoundException
    {
        final String repositoryPath = "/DATA/Repos/" + repoName;

        CmisObject node = getNodeByPath(repositoryPath);

        Property<Object> propRepoName = node.getProperty("REPO_NAME");

        return new Repository(propRepoName.getValueAsString());
    }
}
