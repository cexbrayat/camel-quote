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

	private static final String PRICE = "price";
	private static final String QUOTE_IN = "cxf:bean:quoteIn";

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
		Object[] args = new Object[] { "GOOG", "USD" };
		Quote quote = template.requestBodyAndHeaders(QUOTE_IN, args, headers, Quote.class);
		assertNotNull(quote);
		assertEquals("Google Inc.", quote.getName());
		assertEquals("GOOG", quote.getSymbol());
	}

}
