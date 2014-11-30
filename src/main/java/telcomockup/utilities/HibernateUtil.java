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


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import src.main.java.telcomockup.profile.JavaProfileModel;

/* This class follows the singleton pattern in order to build once and provide a unique hibernate session instance*/
public class HibernateUtil{


    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
        /* Create the unique hibernate session. All resource models should be added here.*/
            return new AnnotationConfiguration().configure()
                    .addAnnotatedClass(JavaProfileModel.class)
                    .buildSessionFactory();
        }
        catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }


}
