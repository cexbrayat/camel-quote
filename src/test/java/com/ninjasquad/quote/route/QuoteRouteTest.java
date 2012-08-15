package com.ninjasquad.quote.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ninjasquad.quote.model.Quote;

public class QuoteRouteTest extends CamelSpringTestSupport {

	private static final String QUOTE_IN = "cxf:bean:quoteIn";
	private static final String PRICE = "price";
	private static final String GOOG = "GOOG";
	private static final String EUR = "EUR";

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("quote-test-context.xml");
	}

	@Override
	public boolean isCreateCamelContextPerClass() {
		return true;
	}

	@Test
	public void priceGoogleQuote() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(CxfConstants.OPERATION_NAME, PRICE);
		Object[] args = new Object[] { GOOG, EUR };
		Quote quote = template.requestBodyAndHeaders(QUOTE_IN, args, headers, Quote.class);
		assertNotNull(quote);
		assertEquals("Google Inc.", quote.getName());
		assertEquals(EUR, quote.getCurrency());
	}

	@Test
	public void currencyUSDToEUR() {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("currency", EUR);
		Double rate = template.requestBodyAndHeaders("direct:currency", "", headers, Double.class);
		assertNotNull(rate);
		assertTrue(rate > 0.5d);
		assertTrue(1.5d > rate);
	}

}
