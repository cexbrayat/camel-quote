package com.sqli.quote.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class StockQuote {

	@XmlElement(name = "Name")
	private String name;
	
	//TODO A completer

}
