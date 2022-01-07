package com.gehouseexc.memberapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class CommitteeAssignment {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     public Long id;

     @OneToOne(targetEntity = Committee.class)
     public Committee committee;
     public String rank;
}
