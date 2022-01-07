package com.gehouseexc.memberapi.applistener.xml;


import java.util.function.Consumer;

import com.gehouseexc.memberapi.model.Committee;
import com.gehouseexc.memberapi.model.CommitteeAssignment;
import com.gehouseexc.memberapi.model.Member;
import com.gehouseexc.memberapi.model.SubCommittee;
import com.gehouseexc.memberapi.model.SubcommitteeAssignment;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MembersSaxHandler  extends DefaultHandler {

    private final Consumer<Member> onMemberSink;

    public MembersSaxHandler(Consumer<Member> onCommitteeSink) {
        this.onMemberSink= onCommitteeSink;
    }
    
    private StringBuilder elementValue = new StringBuilder();
    private Member member;
    private boolean withinMembersElement= false;

    @Override
    public void startDocument() {
    }
  
    @Override
    public void endDocument() {
    }
  
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
  
        elementValue.setLength(0);
        
        if ("members".equals(qName)) {
            withinMembersElement= true;
            return;
        }
        if(!withinMembersElement) {
            return;
        }
  
        switch (qName) {

            case "member":
            member= new Member();
            break;

            case "committee":
                //cleaning data
                String comcode = attributes.getValue("comcode");
                if (comcode != null) {
                    CommitteeAssignment committeeAssignment= new CommitteeAssignment();
                    committeeAssignment.setRank(attributes.getValue("rank"));
                    Committee committee= new Committee();
                    committee.setComcode(comcode);
                    committeeAssignment.setCommittee(committee);
                    member.getComitteeAssignments().add(committeeAssignment);
                } else {
                    log.info("Skipped invalid-committee-assignment: " + member);
                }
                break;

            case "subcommittee":
                SubcommitteeAssignment subcommitteeAssignment= new SubcommitteeAssignment();
                subcommitteeAssignment.setRank(attributes.getValue("rank"));
                SubCommittee subcommittee= new SubCommittee();
                subcommittee.setSubcomcode(attributes.getValue("subcomcode"));
                subcommitteeAssignment.setSubcommittee(subcommittee);
                member.getSubcomitteeAssignments().add(subcommitteeAssignment);
                break;

            //TODO: load lot more attributes/elements data from xml
            //TODO: clean data? validate whether it's a right entry? skip? log?  
        }
    }
  
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (!withinMembersElement) return;
        
        switch (qName) {

            case "members":
            withinMembersElement= false;
            break;

            case "member":
            onMemberSink.accept(member);
            break;

            case "bioguideID":
            member.setBioguideID(elementValue.toString());
            break;
            
            case "statedistrict":
            member.setStateDistrict(elementValue.toString());
            break;
             
            case "firstname":
            member.setFirstname(elementValue.toString());
            break;
             
            case "lastname":
            member.setLastname(elementValue.toString());
            break;
             
            case "namelist":
            member.setNamelist(elementValue.toString());
            break;
             
            case "party":
            member.setParty(elementValue.toString());
            break;
        }
    }
  
    @Override
    public void characters(char ch[], int start, int length) {
        
        if (!withinMembersElement) return;
        elementValue.append(ch, start, length);
    }
}
