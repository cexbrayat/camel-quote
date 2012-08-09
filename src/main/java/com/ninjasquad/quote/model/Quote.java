package com.ninjasquad.quote.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Quote {

	public String name;
	public String symbol;
	public String currency;
	public String price;

}
