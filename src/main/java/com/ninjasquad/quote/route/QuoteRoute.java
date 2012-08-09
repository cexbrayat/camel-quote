package com.ninjasquad.quote.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;

import com.ninjasquad.quote.converter.QuoteConverter;
import com.ninjasquad.quote.model.Quote;
import com.ninjasquad.quote.model.StockQuotes;

public class QuoteRoute extends RouteBuilder {

	private static final String QUOTE_IN = "cxf:bean:quoteIn";
	private static final String QUOTE_OUT = "cxf:bean:quoteOut";
	private static final String GET_STOCK = "GetQuote";

	@Override
	public void configure() throws Exception {

		getContext().getTypeConverterRegistry().addTypeConverter(Quote.class, StockQuotes.class, new QuoteConverter());

		from(QUOTE_IN)//
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getOut().setHeader(CxfConstants.OPERATION_NAME, GET_STOCK);
						exchange.getOut().setBody(exchange.getIn().getBody(java.util.List.class).get(0));
					}
				})//
				.to(QUOTE_OUT)//
				.log("body ${body}")//
				.unmarshal().jaxb("com.ninjasquad.quote.model")//
				.convertBodyTo(Quote.class);

	}

}
