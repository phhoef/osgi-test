package com.my.app.rest.rest;

import org.osgi.service.component.annotations.Component;

import javax.ws.rs.NotAuthorizedException;

@Component(service = SecurityChecker.class)
public class SecurityChecker
{
    public boolean isSecure(String name, String signature)
    {
        if(name.equals(signature))
            // do some fancy checks
             return true;

        throw new NotAuthorizedException("Signature check failed!");
    }
}
