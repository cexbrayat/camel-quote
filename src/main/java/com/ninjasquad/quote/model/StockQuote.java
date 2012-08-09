package com.ninjasquad.quote.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class StockQuote {

	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Symbol")
	private String symbol;
	
	@XmlElement(name = "Last")
	private String last;
	
	@XmlElement(name = "Change")
	private String change;
	
	@XmlElement(name = "Open")
	private String open;
	
	@XmlElement(name = "High")
	private String high;
	
	@XmlElement(name = "Low")
	private String low;

}
