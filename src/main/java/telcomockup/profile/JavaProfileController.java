/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2014
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : TelcoMockup
 * WorkFile            : 
 * Compiler            : 
 * File Description    : 
 * Document Description: 
* Related Documents	   : 
* Note				   : 
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/


package src.main.java.telcomockup.profile;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


/* This class defines the web API of the individual Profile resource. It may handle PUT, GET and/or DELETE requests depending on the specific CIM of the service.*/
@Path("/Profile/{username}")
public class JavaProfileController{


    @Context
    private UriInfo oApplicationUri;


    /* This function handles http GET requests  
    and returns any response formatted as stated in the @Produces JAX-RS annotation below.*/
    @Path("/")
    @GET
    @Produces({"application/JSON", "application/XML"})
    public JavaProfileModel getProfile(@PathParam("username") String username){
        if(username.equals("ana") || username.equals("damir") || username.equals("vlatko"))
            return new JavaProfileModel(username, "Prepaid", "Zagreb");
        if(username.equals("alex") || username.equals("gijs") || username.equals("giulio"))
            return new JavaProfileModel(username, "Postpaid", "Vasteras");

        String a[] = {"Prepaid", "Postpaid"};
        String l[] ={"Zagreb", "Vesteros", "Rome", "Sofia", "London", "New York"};

        return new JavaProfileModel(username,
                a[(int) (Math.random()*1.99)],
                l[(int) (Math.random()*5.99)]);
    }


}
