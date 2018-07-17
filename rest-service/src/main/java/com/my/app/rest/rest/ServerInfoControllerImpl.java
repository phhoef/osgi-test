package com.my.app.rest.rest;

import com.my.app.rest.repository.IRepository;
import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import com.my.app.rest.rest.config.MyConfig;
import org.osgi.service.component.annotations.*;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.my.app.rest.rest.ParamConstants.REPO_NAME;
import static com.my.app.rest.rest.ParamConstants.SIGNATURE;

@Component(service=ServerInfoControllerImpl.class,
        configurationPid = "my.config",
        configurationPolicy = ConfigurationPolicy.OPTIONAL)
@JaxrsResource
@Path("serverInfo")
public class ServerInfoControllerImpl
{
    private static final Logger _logger = LoggerFactory.getLogger(ServerInfoControllerImpl.class);

    @Reference
    private IRepository _repository;

    @Reference
    private SecurityChecker _securityChecker;

    private MyConfig _config;

    @Activate
    public void activate(MyConfig config)
    {
        _config = config;
    }

    @Modified
    public void modified(MyConfig config)
    {
        _config = config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServerInfo(@QueryParam(REPO_NAME) String repoName,
                                @QueryParam(SIGNATURE) String signature)
    {

        if(_config.isSecurityEnabled())
            _securityChecker.isSecure(repoName, signature);

        // if we pass the isSecure method, the security is successfully checked

        try
        {
            _logger.debug("Getting repository for Name {}", repoName);
           return _repository.getRepository(repoName).getRepoName();
        }
        catch (RepositoryUnauthorizedException rue)
        {
            throw new NotAuthorizedException(rue.getMessage(), rue);
        }
        catch (RepositoryItemNotFoundException rinfe)
        {
            throw new NotFoundException(rinfe.getMessage(), rinfe);
        }
    }
}