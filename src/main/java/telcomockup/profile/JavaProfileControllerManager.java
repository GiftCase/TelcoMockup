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


/* This class defines the web API of the manager Profile resource. It handles POST and GET HTTP requests, as it is prescribed by the meta-models.*/
@Path("/Profile")
public class JavaProfileControllerManager{


    @Context
    private UriInfo oApplicationUri;


    /* This function handles POST requests that are sent with any media type stated in the @Consumes JAX-RS annotation below 
     and returns any response in any media type stated in the @Produces JAX-RS annotation below.*/
    @Path("/")
    @POST
    @Produces({"application/JSON", "application/XML"})
    @Consumes({"application/JSON", "application/XML"})
    public JavaProfileModel postProfile(JavaProfileModel oJavaProfileModel){
        PostProfileHandler oPostProfileHandler = new PostProfileHandler(oJavaProfileModel, oApplicationUri);
        return oPostProfileHandler.postJavaProfileModel();
    }

    /* This function handles GET requests  
     and returns any response in any media type stated in the @Produces JAX-RS annotation below.*/
    @Path("/")
    @GET
    @Produces({"application/JSON", "application/XML"})
    public JavaProfileModel getProfile(){
        String a[] = {"Prepaid", "Postpaid"};
        String l[] ={"Zagreb", "Vesteros", "Rome", "Sofia", "London", "New York"};

        return new JavaProfileModel("Random",
                a[(int) (Math.random()*1.99)],
                l[(int) (Math.random()*5.99)]);
    }


}
