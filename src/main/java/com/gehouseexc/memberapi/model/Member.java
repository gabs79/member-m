package com.gehouseexc.memberapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    public String bioguideID;
    
    public String stateDistrict;
    public String namelist;
    public String lastname;
    public String firstname;
    public String party;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_bioguideID")
    public List<CommitteeAssignment> comitteeAssignments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_bioguideID")
    public List<SubcommitteeAssignment> subcomitteeAssignments = new ArrayList<>(); 
}
