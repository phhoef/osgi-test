package com.my.app.rest.rest;

import com.my.app.rest.repository.IRepository;
import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;
import org.osgi.service.log.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component(service=ServerInfoControllerImpl.class)
@JaxrsResource
@Path("serverInfo")
public class ServerInfoControllerImpl
{
    private static final String REPOSITORY_NAME = "repoName";

    @Reference
    private IRepository _repository;

    @Reference
    private Logger _logger;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServerInfo(@QueryParam(REPOSITORY_NAME) String repoName)
    {
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