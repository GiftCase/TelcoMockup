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

/* This class processes POST requests for Profile resources and creates the hypermedia to be returned to the client*/
public class PostProfileHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
    private JavaProfileModel oJavaProfileModel;

    public PostProfileHandler(JavaProfileModel oJavaProfileModel, UriInfo oApplicationUri){
        this.oJavaProfileModel = oJavaProfileModel;
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
    }

    public JavaProfileModel postJavaProfileModel(){
        return createHypermedia(oHibernateController.postProfile(oJavaProfileModel));
    }

    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaProfileModel createHypermedia(JavaProfileModel oJavaProfileModel){

        /* Create hypermedia links towards this specific Profile resource. These must be GET and POST as it is prescribed in the meta-models.*/
        oJavaProfileModel.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), oApplicationUri.getPath()), "Get all Profiles", "GET", "Sibling"));
        oJavaProfileModel.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), oApplicationUri.getPath()), "Create a new Profile", "POST", "Sibling"));

        /* Then calculate the relative path to any resources that are related of this one and add the according hypermedia links to the Linklist.*/
        String oRelativePath;
        oRelativePath = oApplicationUri.getPath();
        oJavaProfileModel.getLinkList().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oJavaProfileModel.getProfileId()), oJavaProfileModel.getUsername(), "GET", "Child", oJavaProfileModel.getProfileId()));
        return oJavaProfileModel;
    }


}
