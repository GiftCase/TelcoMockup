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
public class GetProfileListHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
    private Set<JavaProfileModel> SetOfProfileModel = new HashSet<JavaProfileModel>(); // Since this resource is not related of any other, this HashSet will hold the list to be sent back to client.

    public GetProfileListHandler(UriInfo oApplicationUri){
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
    }

    public JavaProfileModelManager getJavaProfileModelManager(){
        this.SetOfProfileModel = oHibernateController.getProfileList(this.SetOfProfileModel);
        return createHypermedia();
    }

    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaProfileModelManager createHypermedia(){
        JavaProfileModelManager oJavaProfileModelManager = new JavaProfileModelManager();

        /* Create hypermedia links towards this specific Profile resource. These must be GET and POST as it is prescribed in the meta-models.*/
        oJavaProfileModelManager.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), oApplicationUri.getPath()), "Get all Profiles", "GET", "Sibling"));
        oJavaProfileModelManager.getLinkList().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), oApplicationUri.getPath()), "Create a new Profile", "POST", "Sibling"));

        /* Then calculate the relative path to any related resource of this one and add for each one a hypermedia link to the Linklist.*/
        String oRelativePath;
        oRelativePath = oApplicationUri.getPath();
        Iterator<JavaProfileModel> setIterator = this.SetOfProfileModel.iterator();
        while(setIterator.hasNext()){
            JavaProfileModel oNextJavaProfileModel = new JavaProfileModel();
            oNextJavaProfileModel = setIterator.next();
            oJavaProfileModelManager.getLinkList().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oNextJavaProfileModel.getProfileId()), String.format("%s", oNextJavaProfileModel.getUsername()), "GET", "Child", oNextJavaProfileModel.getProfileId()));
        }
        return oJavaProfileModelManager;
    }


}
