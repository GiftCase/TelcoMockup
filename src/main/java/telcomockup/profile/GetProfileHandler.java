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


import javax.ws.rs.core.UriInfo;

import java.util.Iterator;
import src.main.java.telcomockup.utilities.HypermediaLink;
import src.main.java.telcomockup.utilities.HibernateController;
import java.util.Set;
import java.util.HashSet;

/* This class processes GET requests for Profile resources and creates the hypermedia to be returned to the client*/
public class GetProfileHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
    private JavaProfileModel oJavaProfileModel;

    public GetProfileHandler(int ProfileId, UriInfo oApplicationUri){
        oJavaProfileModel = new JavaProfileModel();
        oJavaProfileModel.setProfileId(ProfileId);
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
    }

    public JavaProfileModel getJavaProfileModel(){
        return createHypermedia(oHibernateController.getProfile(oJavaProfileModel));
    }

    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaProfileModel createHypermedia(JavaProfileModel oJavaProfileModel){

        /* Create hypermedia links towards this specific Profile resource. These can be GET, PUT and/or delete depending on what was specified in the service CIM.*/
        oJavaProfileModel.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), oApplicationUri.getPath()), "Get the Profile", "GET", "Sibling"));

        /* Calculate the relative path towards any related resources of this one. Then add each new hypermedia link with that relative path to the hypermedia linklist to be sent back to client.*/
        String oRelativePath;

        /* Finally, truncate the current URI so as to point to the resource manager of which this resource is related.
        Then create the hypermedia links towards the parent resources.*/
        int iLastSlashIndex = String.format("%s%s", oApplicationUri.getBaseUri(), String.format("%s", oApplicationUri.getPath()).replaceAll("multiProfile","multiProfileManager")).lastIndexOf("/");
        oJavaProfileModel.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), String.format("%s", oApplicationUri.getPath()).replaceAll("multiProfile","multiProfileManager")).substring(0, iLastSlashIndex), "Create a new Profile", "POST", "Parent"));
        oJavaProfileModel.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), String.format("%s", oApplicationUri.getPath()).replaceAll("multiProfile","multiProfileManager")).substring(0, iLastSlashIndex), "Get all Profiles", "GET", "Parent"));
        return oJavaProfileModel;
    }


}
