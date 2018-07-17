package com.my.app.rest.rest;

import com.my.app.rest.rest.config.MyConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import javax.ws.rs.NotAuthorizedException;

@Component(service = SecurityChecker.class,
            configurationPid = "my.config",
            configurationPolicy = ConfigurationPolicy.OPTIONAL)
public class SecurityChecker
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

    public boolean isSecure(String name, String signature)
    {
        if(!_myConfig.isSecurityEnabled())
            return true;

        if(name.equals(signature))
            // do some fancy checks
             return true;

        throw new NotAuthorizedException("Signature check failed!");
    }
}
