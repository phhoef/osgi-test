package com.my.app.rest.rest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletMultipart;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Component(service=MultipartController.class)
@JaxrsResource
@HttpWhiteboardServletMultipart
@Path("/multipart")
public class MultipartController
{
    @POST
    public void post(@Context HttpServletRequest request) throws Exception
    {
        for(Part part : request.getParts())
        {
            System.out.println(part.getName());
        }
    }
}
