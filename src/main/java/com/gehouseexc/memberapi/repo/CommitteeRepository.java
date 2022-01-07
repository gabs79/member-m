package com.gehouseexc.memberapi.repo;

import com.gehouseexc.memberapi.model.Committee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitteeRepository extends JpaRepository<Committee, String>{

}