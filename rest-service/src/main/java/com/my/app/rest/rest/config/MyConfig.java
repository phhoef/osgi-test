package com.my.app.rest.rest.config;

import org.osgi.service.component.annotations.ComponentPropertyType;

@ComponentPropertyType
public @interface MyConfig
{
    boolean test_securityEnabled() default true;
    String test_name() default "Empty";
}
