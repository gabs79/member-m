package com.gehouseexc.memberapi.applistener.xml;

import java.util.function.Consumer;

import com.gehouseexc.memberapi.model.Committee;
import com.gehouseexc.memberapi.model.SubCommittee;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class CommitteesSaxHandler  extends DefaultHandler {

    private final Consumer<Committee> onCommitteeSink;

    public CommitteesSaxHandler(Consumer<Committee> onCommitteeSink) {
        this.onCommitteeSink= onCommitteeSink;
    }
    
    private StringBuilder elementValue = new StringBuilder();
    private Committee committee;
    private SubCommittee subCommittee;
    private boolean withinCommitteesElement= false;

    @Override
    public void startDocument() {
    }
  
    @Override
    public void endDocument() {
    }
  
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
  
        elementValue.setLength(0);
        
        if ("committees".equals(qName)) {
            withinCommitteesElement= true;
            return;
        }
        if(!withinCommitteesElement) {
            return;
        }
  
        switch (qName) {

            case "committee":
            committee= new Committee();
            committee.setType(attributes.getValue("type"));
            committee.setComcode(attributes.getValue("comcode"));
            committee.setComRoom(attributes.getValue("com-room"));
            committee.setComZip(attributes.getValue("com-zip"));
            break;
            
            case "subcommittee":
            subCommittee= new SubCommittee();
            subCommittee.setSubcomRoom(attributes.getValue("subcom-room"));
            subCommittee.setSubcomZip(attributes.getValue("subcom-zip"));
            subCommittee.setSubcomcode(attributes.getValue("subcomcode"));
            break;


            //TODO: load lot more attributes/elements data from xml
            //TODO: clean data? validate whether it's a right entry? skip? log?  
        }
    }
  
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (!withinCommitteesElement) return;
        
        switch (qName) {

            case "committees":
            withinCommitteesElement= false;
            break;

            case "committee":
            onCommitteeSink.accept(committee);
            break;
             
            case "committee-fullname":
            committee.setCommitteeFullName(elementValue.toString());
            break;
             
            case "majority":
            if (subCommittee == null) {
                committee.setRatioMajority(elementValue.toString());
            } else {
                subCommittee.setRatioMajority(elementValue.toString());
            }
            break;

            case "subcommittee":
            committee.getSubcommittees().add(subCommittee);
            subCommittee= null;
            break;
             
            case "subcommittee-fullname":
            subCommittee.setSubcommitteeFullname(elementValue.toString());
            break;
        }
    }
  
    @Override
    public void characters(char ch[], int start, int length) {
        
        if (!withinCommitteesElement) return;
        elementValue.append(ch, start, length);
    }
}