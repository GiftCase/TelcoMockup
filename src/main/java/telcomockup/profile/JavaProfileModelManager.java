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
import java.util.List;

import src.main.java.telcomockup.utilities.HypermediaLink;
import javax.xml.bind.annotation.XmlRootElement;

/* This class models a Profile manager resource. It also has the needed JAXB annoations for automated representation parsing/marshalling.*/
@XmlRootElement
public class JavaProfileModelManager{


    private List<HypermediaLink> LinkList = new ArrayList<HypermediaLink>(); //The Linklist property holds any hypermedia links to be sent back to the client.


    public List<HypermediaLink> getLinkList(){
        return this.LinkList;
    }

    public void setLinkList(List<HypermediaLink> LinkList){
        this.LinkList = LinkList;
    }


}
