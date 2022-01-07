package com.gehouseexc.memberapi.applistener.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReadXmlParser {
    //private static final String FILENAME= "src/main/resources/MemberDataCommitteesShort.xml";
    private static final String FILENAME= "MemberDataCommittees.xml";
    private final DefaultHandler handler;

    public void read() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            //saxParser.parse(ResourceUtils.extractJarFileURL(FILENAME), handler);
            saxParser.parse(fetchXmlResource(), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private InputStream fetchXmlResource() throws IOException {
        Resource resource = new ClassPathResource(FILENAME);
	    return resource.getInputStream();
    }
}