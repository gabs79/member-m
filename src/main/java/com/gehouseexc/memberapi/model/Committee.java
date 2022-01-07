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
public class Committee {
    @Id
    String comcode;
    
    String type;
    String comRoom;
    String comHeaderText;
    String comZip;
    String ratioMajority;
    String committeeFullName;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "committee_comcode")
    List<SubCommittee> subcommittees= new ArrayList<>();

}
