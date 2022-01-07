package com.gehouseexc.memberapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.gehouseexc.memberapi.model.TitleInfo;
import com.gehouseexc.memberapi.repo.TitleInfoRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TitleInfoRepositoryTest {
    @Autowired
    TitleInfoRepository titleInfoRepository;

    @Test
    public void getTitleInfo() {

        assertNull(titleInfoRepository.findAll());

        TitleInfo titleInfo= new TitleInfo();
        titleInfo.setClerk("test clerk");
        titleInfo.setCongressNum(5L);
        titleInfo.setCongressText("test congress text");
        titleInfo.setMajority("a majority");
        
        titleInfoRepository.save(titleInfo);
        assertNotNull(titleInfoRepository.findAll());
    }
}
