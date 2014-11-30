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


import java.util.Set;
import java.util.HashSet;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import src.main.java.telcomockup.utilities.HibernateUtil;
import src.main.java.telcomockup.profile.JavaProfileModel;

/* HibernateController class is responsible to handle the low level activity between Hibernate and the service database.
 You may not alter existing functions, or the service may not function properly.
 Should you need more functions these could be added at the end of this file.
 You may add any exception handling to existing and/or new functions of this file.*/
public class HibernateController{


    private static HibernateController oHibernateController = new HibernateController();

    /* Since the class follows the singleton design pattern its constructor is kept private. The unique instance of it is accessed through its public API "getHibernateControllerHandle()".*/
    private HibernateController(){}

    /* Since this class follows the singleton design pattern, this function offers to the rest of the system a handle to its unique instance.*/
    public static HibernateController getHibernateControllerHandle(){
        return oHibernateController;
    }

    /* This function handles the low level hibernate activities so as to add a new Profile resource to the service database.*/
    public JavaProfileModel postProfile(JavaProfileModel oJavaProfileModel){

    /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Insert the new Profile to database*/
        int ProfileId = (Integer) hibernateSession.save(oJavaProfileModel);

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();

        /* Return the JavaProfileModel with updated ProfileId*/
        oJavaProfileModel.setProfileId(ProfileId);
        return oJavaProfileModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve an existing Profile resource from the service database.*/
    public JavaProfileModel getProfile(JavaProfileModel oJavaProfileModel){

    /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the existing Profile from the database*/
        oJavaProfileModel = (JavaProfileModel) hibernateSession.get(JavaProfileModel.class, oJavaProfileModel.getProfileId());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return oJavaProfileModel;
    }

    /* This function handles the low level hibernate activities so as to retrieve all the Profile resources from the service database.*/
    public Set<JavaProfileModel> getProfileList(Set<JavaProfileModel> SetOfProfileList){

    /* Create a new hibernate session and begin the transaction*/
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        Transaction hibernateTransaction = hibernateSession.beginTransaction();

        /* Retrieve the list of Profile resources that are needed.*/
        String strHibernateQuery = "FROM JavaProfileModel";
        Query hibernateQuery = hibernateSession.createQuery(strHibernateQuery);
        SetOfProfileList = new HashSet(hibernateQuery.list());

        /* Commit and terminate the session*/
        hibernateTransaction.commit();
        hibernateSession.close();
        return SetOfProfileList;
    }


}
