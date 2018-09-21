package com.my.app.rest.rest;

import com.my.app.rest.repository.IRepository;
import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RepositoryRestService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RepositoryRestService.class);

    private IRepository repository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServerInfo(@QueryParam("repository") String repoName,
                                @QueryParam("signature") String signature)
    {
        //_logger.debug("Reponame" + repoName);
        //_logger.error("Signature: " + signature);

        //return repoName + " " + signature;

        // TODO re-add
        // _multiTenantSecurityChecker.isSecure(repoName, signature);

        // if we pass the isSecure method, the security is successfully checked

        try
        {
            LOGGER.debug("Getting repository for Name {}", repoName);
            return repository.getRepository(repoName).getRepoName();
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

    public IRepository getRepository() {
        return repository;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}
