package com.my.app.rest.rest;

import com.my.app.rest.repository.IRepository;
import com.my.app.rest.repository.exception.RepositoryItemNotFoundException;
import com.my.app.rest.repository.exception.RepositoryUnauthorizedException;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

import static com.my.app.rest.rest.ParamConstants.REPO_NAME;
import static com.my.app.rest.rest.ParamConstants.SIGNATURE;

@Component(service=ServerInfoControllerImpl.class)
@JaxrsResource
@Path("serverInfo")
public class ServerInfoControllerImpl
{
    @Reference(service = LoggerFactory.class)
    private Logger _logger;

    @Reference
    private IRepository _repository;

    //@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
    private volatile Map<Map<String, Object>, SecurityChecker> _securityCheckers;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getServerInfo(@QueryParam(REPO_NAME) String repoName,
                                @QueryParam(SIGNATURE) String signature)
    {
        _logger.debug("Reponame" + repoName);
        _logger.error("Signature: " + signature);

        return repoName + " " + signature;

//        try
//        {
//            Filter filter = FrameworkUtil.createFilter("(repoName=" + repoName + ")");
//            SecurityChecker securityChecker = _securityCheckers.entrySet().stream().filter(e -> filter.matches(e.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
//
//            if(securityChecker == null)
//                throw new InternalServerErrorException();
//
//            securityChecker.isSecure(repoName, signature);
//        }
//        catch(InvalidSyntaxException ise)
//        {
//            throw new InternalServerErrorException(ise);
//        }
//
//        // if we pass the isSecure method, the security is successfully checked
//
//        try
//        {
//            _logger.debug("Getting repository for Name {}", repoName);
//           return _repository.getRepository(repoName).getRepoName();
//        }
//        catch (RepositoryUnauthorizedException rue)
//        {
//            throw new NotAuthorizedException(rue.getMessage(), rue);
//        }
//        catch (RepositoryItemNotFoundException rinfe)
//        {
//            throw new NotFoundException(rinfe.getMessage(), rinfe);
//        }
    }
}