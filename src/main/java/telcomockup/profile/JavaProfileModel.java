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


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import src.main.java.telcomockup.utilities.HypermediaLink;

/* This class models the data of a Profile resource. It is enhanced with JAXB annotations for automated representation
parsing/marshalling as well as with Hibernate annotations for ORM transformations.*/
@XmlRootElement
@Entity
@Table(name="profile")
public class JavaProfileModel{


    /* There follows a list with the properties that model the Profile resource, as prescribed in the service CIM*/
    @Column(name = "username")
    private String Username; 

    @Column(name = "billingplan")
    private String Billingplan; 

    @Column(name = "location")
    private String Location; 

    @Id
    @GeneratedValue
    @Column(name = "profileid")
    private int ProfileId; 

    @Transient
    private List<HypermediaLink> LinkList = new ArrayList<HypermediaLink>(); // The LinkList property holds all the hypermedia links to be sent back to the client.

    public JavaProfileModel(String username, String plan, String location) {
        this.Username = username;
        this.Billingplan = plan;
        this.Location=location;
    }

    public JavaProfileModel() {}

    /* There follows a list of setter and getter functions.*/
    public String getUsername(){
        return this.Username;
    }

    public String getBillingplan(){
        return this.Billingplan;
    }

    public String getLocation(){
        return this.Location;
    }

    public int getProfileId(){
        return this.ProfileId;
    }

    public List<HypermediaLink> getLinkList(){
        return this.LinkList;
    }

    public void setUsername(String Username){
        this.Username = Username;
    }

    public void setBillingplan(String Billingplan){
        this.Billingplan = Billingplan;
    }

    public void setLocation(String Location){
        this.Location = Location;
    }

    public void setProfileId(int ProfileId){
        this.ProfileId = ProfileId;
    }

    public void setLinkList(List<HypermediaLink> LinkList){
        this.LinkList = LinkList;
    }

    /* This function deletes explicitly any collentions of this resources that are stored in the database 
    and iteratively does so for any subsequent related resources.
    NOTE: this function is needed to handle erroneous handling of cascade delete of some hibernate versions.*/
    public void deleteAllCollections(Session hibernateSession){
        
    }


}
