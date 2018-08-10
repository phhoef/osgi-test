package com.my.app.rest.rest;

import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.annotations.CollectionType;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.ws.rs.InternalServerErrorException;
import java.util.List;
import java.util.Map;

@Component(service = MultiTenantSecurityChecker.class)
public class MultiTenantSecurityChecker
{
    @Reference(policyOption = ReferencePolicyOption.GREEDY, collectionType = CollectionType.TUPLE)
    private volatile List<Map.Entry<Map<String, Object>, ISecurityChecker>> _securityCheckers;

    public boolean isSecure(String repoName, String signature)
    {
        try
        {
            Filter filter = FrameworkUtil.createFilter("(" + Constants.SERVICE_PID + "=my.config~*"+ repoName + "*)");
            ISecurityChecker securityChecker = _securityCheckers.stream().filter(e -> filter.matches(e.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);

            if(securityChecker == null)
                throw new InternalServerErrorException("No Configuration found for " + repoName);

            return securityChecker.isSecure(repoName, signature);

        }
        catch(InvalidSyntaxException ise)
        {
            throw new InternalServerErrorException(ise);
        }
    }
}
