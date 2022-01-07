package com.gehouseexc.memberapi.applistener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.gehouseexc.memberapi.applistener.xml.CommitteesSaxHandler;
import com.gehouseexc.memberapi.applistener.xml.MembersSaxHandler;
import com.gehouseexc.memberapi.applistener.xml.ReadXmlParser;
import com.gehouseexc.memberapi.applistener.xml.TitleInfoSaxHandler;
import com.gehouseexc.memberapi.model.Committee;
import com.gehouseexc.memberapi.model.SubCommittee;
import com.gehouseexc.memberapi.service.MembersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AppInitializer {

    private final MembersService membersService;
    
    public void execute() {
        etlTitleInfo();
        etlCommitteesAndMembers();
    }

    private void etlCommitteesAndMembers() {
        AtomicInteger membersCounter= new AtomicInteger(0);
        AtomicInteger skippedCounter= new AtomicInteger(0);
        Map<String,Committee> cachedCommittees= new HashMap<>();
        Map<String,SubCommittee> cachedSubcommittees= new HashMap<>();
        new ReadXmlParser(new CommitteesSaxHandler((committee) -> {
            Committee savedCommittee = membersService.saveCommittee(committee);
            cachedCommittees.put(savedCommittee.getComcode(), savedCommittee);
            savedCommittee.getSubcommittees().forEach((savedSubcommittee) -> 
                 cachedSubcommittees.put(savedSubcommittee.getSubcomcode(), savedSubcommittee));
        })).read();

        log.info(String.format("MembersETL-committees: saved %d committees. Cached %d subcommittees.", 
        cachedCommittees.size(), cachedSubcommittees.size()));

        new ReadXmlParser(new MembersSaxHandler((member) -> {
            member.getComitteeAssignments().forEach((ca) -> {
                String comcode = ca.getCommittee().getComcode();
                if (cachedCommittees.containsKey(comcode)) {
                    ca.setCommittee(cachedCommittees.get(ca.getCommittee().getComcode()));
                } else {
                    skippedCounter.incrementAndGet();
                }
            });
            member.getSubcomitteeAssignments().forEach((sa) -> {
                String subcomcode = sa.getSubcommittee().getSubcomcode();
                if (cachedSubcommittees.containsKey(subcomcode)) {
                    sa.setSubcommittee(cachedSubcommittees.get(subcomcode));
                } else {
                    skippedCounter.incrementAndGet();
                }
            });
            membersCounter.incrementAndGet();
            log.info("MembersETL-member: to save member:" + member.toString());
            membersService.saveMember(member);
        })).read();
        
        
        log.info(String.format("MembersETL-members: saved %d members.", membersCounter.get()));
        log.info("MembersETL-members: skipped not-found-subORcommittee assignments:" + skippedCounter.get());
    }

    private void etlTitleInfo() {
        new ReadXmlParser(new TitleInfoSaxHandler((titleinfo) -> {
            log.info("MembersETL-title-info, about to save: " + titleinfo);
            membersService.saveTitleInfo(titleinfo);
        })).read();
    }

}
