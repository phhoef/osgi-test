package com.my.app.rest.rest;

import com.my.app.rest.rest.config.MyConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.configurator.annotations.RequireConfigurator;

import javax.ws.rs.NotAuthorizedException;

import static org.osgi.service.cm.ConfigurationAdmin.SERVICE_FACTORYPID;

@RequireConfigurator
@Component(service = ISecurityChecker.class,
            configurationPid = "my.config",
            configurationPolicy = ConfigurationPolicy.REQUIRE)
public class SecurityChecker implements ISecurityChecker
{
    private MyConfig _myConfig;

    @Activate
    public void activate(MyConfig config)
    {
        _myConfig = config;
    }

    @Modified
    public void modified(MyConfig config)
    {
        _myConfig = config;
    }

    @Override
    public boolean isSecure(String name, String signature)
    {
        if(!_myConfig.test_securityEnabled())
            return true;

        if(name.equals(signature))
            // do some fancy checks
             return true;

        throw new NotAuthorizedException("Signature check failed!");
    }
}
