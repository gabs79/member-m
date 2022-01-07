package com.gehouseexc.memberapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SubCommittee {

    @Id
    String subcomcode;
    
    String subcomRoom;
    String subcomZip;
    String subcommitteeFullname;
    String ratioMajority;
}
