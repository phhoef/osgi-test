package com.my.app.rest.rest;

public interface ISecurityChecker
{
    boolean isSecure(String name, String signature);
}
