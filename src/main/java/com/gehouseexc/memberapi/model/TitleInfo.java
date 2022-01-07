package com.gehouseexc.memberapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleInfo {
    @Id
    private Long congressNum;
    private String congressText;
    private String session;
    private String majority;
    private String minority;
    private String clerk;
    private String weburl;
}
