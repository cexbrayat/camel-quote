package com.ninjasquad.quote.route;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.ninjasquad.quote.converter.QuoteConverter;
import com.ninjasquad.quote.model.Quote;
import com.ninjasquad.quote.model.StockQuotes;

public class QuoteRoute extends RouteBuilder {

	private static final String QUOTE_IN = "cxf:bean:quoteIn";
	private static final String QUOTE_OUT = "cxf:bean:quoteOut";
	private static final String GET_QUOTE = "GetQuote";
	private static final String CURRENCY_OUT = "cxf:bean:currencyOut";
	private static final String GET_RATE = "ConversionRate";
	private static final String DIRECT_QUOTE = "direct:quote";
	private static final String DIRECT_CURRENCY = "direct:currency";

	@Override
	public void configure() throws Exception {

		getContext().getTypeConverterRegistry().addTypeConverter(Quote.class, StockQuotes.class, new QuoteConverter());

		AggregationStrategy aggregationStrategy = new AggregationStrategy() {

			@Override
			public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
				Quote quote = oldExchange.getIn().getBody(Quote.class);
				Double rate = newExchange.getIn().getBody(Double.class);
				if (0d == rate) {
					rate = 1.0d;
				}
				BigDecimal newPrice = new BigDecimal(rate).multiply(quote.getPrice()).setScale(2, RoundingMode.CEILING);
				quote.setPrice(newPrice);
				quote.setCurrency(newExchange.getIn().getHeader("currency", String.class));
				oldExchange.getIn().setBody(quote);
				return oldExchange;
			}
		};

		from(QUOTE_IN)//
				.to(DIRECT_QUOTE).enrich(DIRECT_CURRENCY, aggregationStrategy);

		from(DIRECT_QUOTE)//
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getOut().setHeader(CxfConstants.OPERATION_NAME, GET_QUOTE);
						exchange.getOut().setBody(exchange.getIn().getBody(java.util.List.class).get(0));
						String toCurrency = (String) exchange.getIn().getBody(java.util.List.class).get(1);
						exchange.getOut().setHeader("currency", toCurrency);
					}
				})//
				.to(QUOTE_OUT)//
				.unmarshal().jaxb("com.ninjasquad.quote.model")//
				.convertBodyTo(Quote.class);

		from(DIRECT_CURRENCY)//
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getOut().setHeader(CxfConstants.OPERATION_NAME, GET_RATE);
						String toCurrency = exchange.getIn().getHeader("currency", String.class);
						exchange.getOut().setHeader("currency", toCurrency);
						exchange.getOut().setBody(new Object[] { "USD", toCurrency });
					}
				})//
				.to(CURRENCY_OUT)//
				.log("currency rate : ${body.toString()}");

	}
}
