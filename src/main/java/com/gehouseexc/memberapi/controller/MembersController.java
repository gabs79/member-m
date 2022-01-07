package com.gehouseexc.memberapi.controller;

import com.gehouseexc.memberapi.model.Committee;
import com.gehouseexc.memberapi.model.Member;
import com.gehouseexc.memberapi.model.TitleInfo;
import com.gehouseexc.memberapi.service.MembersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MembersController {
  
    @Autowired
    private final MembersService membersService;

    @PostMapping("/title-info")
    public TitleInfo saveTitleInfo(@RequestBody TitleInfo titleInfo) {
        return membersService.saveTitleInfo(titleInfo);
    }

    @GetMapping("/title-info")
    public TitleInfo getTitleInfo() {
        return membersService.getTitleInfo().orElse(null);
    }

    @GetMapping("/committee/{id}")
    public Committee getCommittee(@PathVariable("id") String committeeComcode) {
        return membersService.getCommitteeById(committeeComcode).orElse(null);
    }

    @GetMapping("/member/{id}")
    public Member getMember(@PathVariable("id") String bioguideID) {
        return membersService.getMemberById(bioguideID).orElse(null);
    }
}

