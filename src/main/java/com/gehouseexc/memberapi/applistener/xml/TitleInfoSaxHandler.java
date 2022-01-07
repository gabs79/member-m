package com.gehouseexc.memberapi.applistener.xml;


import java.util.function.Consumer;

import com.gehouseexc.memberapi.model.TitleInfo;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class TitleInfoSaxHandler extends DefaultHandler {

    private final Consumer<TitleInfo> onTitleInfoSink;

    public TitleInfoSaxHandler(Consumer<TitleInfo> onTitleInfoSink) {
        this.onTitleInfoSink= onTitleInfoSink;
    }
    
    private StringBuilder elementValue = new StringBuilder();
    private TitleInfo titleInfo;
    private boolean withinTitleInfoElement= false;

    @Override
    public void startDocument() {
    }
  
    @Override
    public void endDocument() {
    }
  
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
  
        elementValue.setLength(0);
        
        if ("title-info".equals(qName)) {
            withinTitleInfoElement= true;
            titleInfo= new TitleInfo();
        }
    }
  
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (!withinTitleInfoElement) return;
        
        switch (qName) {

            case "title-info":
            withinTitleInfoElement= false;
            onTitleInfoSink.accept(titleInfo);
            break;

            case "congress-num":
            titleInfo.setCongressNum(Long.valueOf(elementValue.toString()));
            break;

            case "congress-text":
            titleInfo.setCongressText(elementValue.toString());
            break;

            case "session":
            titleInfo.setSession(elementValue.toString());
            break;

            case "majority":
            titleInfo.setMajority(elementValue.toString());
            break;

            case "minority":
            titleInfo.setMinority(elementValue.toString());
            break;

            case "clerk":
            titleInfo.setClerk(elementValue.toString());
            break;

            case "weburl":
            titleInfo.setWeburl(elementValue.toString());
            break;
        }
    }
  
    @Override
    public void characters(char ch[], int start, int length) {
        
        if (!withinTitleInfoElement) return;
        elementValue.append(ch, start, length);
    }
}
