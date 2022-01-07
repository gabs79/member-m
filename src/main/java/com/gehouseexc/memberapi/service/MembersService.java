package com.gehouseexc.memberapi.service;

import java.util.Optional;

import com.gehouseexc.memberapi.model.Committee;
import com.gehouseexc.memberapi.model.Member;
import com.gehouseexc.memberapi.model.TitleInfo;
import com.gehouseexc.memberapi.repo.CommitteeRepository;
import com.gehouseexc.memberapi.repo.MemberRepository;
import com.gehouseexc.memberapi.repo.TitleInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersService {

    @Autowired
    private final TitleInfoRepository titleInfoRepository;

    @Autowired
    private final CommitteeRepository committeeRepository;

    @Autowired
    private final MemberRepository memberRepository;
    
    public TitleInfo saveTitleInfo(TitleInfo titleInfo) {
        return titleInfoRepository.save(titleInfo);
    } 

    public Optional<TitleInfo> getTitleInfo() {
        return titleInfoRepository.findAll().stream().findFirst();
    }
    
    public Committee saveCommittee(Committee committee) {
        return committeeRepository.save(committee);
    } 
    
    public Optional<Committee> getCommitteeById(String id) {
        return committeeRepository.findById(id);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    } 

    public Optional<Member> getMemberById(String id) {
        return memberRepository.findById(id);
    }
}