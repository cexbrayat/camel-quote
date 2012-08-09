package com.ninjasquad.quote.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import com.ninjasquad.quote.model.StockQuote;

@Data
@XmlRootElement(name = "StockQuotes")
@XmlAccessorType(XmlAccessType.FIELD)
public class StockQuotes {

	@XmlElement(name = "Stock")
	private StockQuote quote;

}
