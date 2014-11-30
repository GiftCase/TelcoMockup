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


package src.main.java.telcomockup.utilities;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

import src.main.java.telcomockup.profile.JavaProfileController;
import src.main.java.telcomockup.profile.JavaProfileControllerManager;

/* This class is responsible to publish any resource controllers that can handle incoming http requests
to the web service container (Jetty etc)*/
@ApplicationPath("/api/")
public class JAXRSPublisher extends Application{



    public JAXRSPublisher(){}

    /* This function returns to the container (Jetty, Tomcat etc) the classes that expose any web API*/
    @Override
    public Set<Class<?>> getClasses(){
        HashSet<Class<?>> SetOfClasses = new HashSet<Class<?>>();
        SetOfClasses.add(JavaProfileController.class);
        SetOfClasses.add(JavaProfileControllerManager.class);

        return SetOfClasses;
    }

    @Override
    public Set<Object> getSingletons(){
        return new HashSet<Object>();
    }


}
