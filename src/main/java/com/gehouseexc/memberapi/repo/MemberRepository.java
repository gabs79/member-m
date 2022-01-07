package com.gehouseexc.memberapi.repo;

import com.gehouseexc.memberapi.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String>{

}