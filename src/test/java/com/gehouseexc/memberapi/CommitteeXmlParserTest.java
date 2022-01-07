package com.gehouseexc.memberapi;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import com.gehouseexc.memberapi.applistener.xml.CommitteesSaxHandler;
import com.gehouseexc.memberapi.applistener.xml.ReadXmlParser;
import com.gehouseexc.memberapi.model.Committee;

import org.junit.Test;

public class CommitteeXmlParserTest {

    @Test
    void parseTest() {
        List<Committee> parsedCommittees = new ArrayList<>();
        new ReadXmlParser(new CommitteesSaxHandler((committee) -> {
            parsedCommittees.add(committee);
        })).read();

        assertFalse(parsedCommittees.isEmpty());
    }

}
