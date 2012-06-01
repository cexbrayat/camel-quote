package com.sqli.quote.converter;

import org.apache.camel.Exchange;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.TypeConverter;

import com.sqli.quote.model.Quote;
import com.sqli.quote.model.StockQuotes;

public class QuoteConverter implements TypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convertTo(Class<T> type, Object value) {
		Quote quote = new Quote();
		if (value == null) { return null; }
		StockQuotes stockQuotes = (StockQuotes) value;
		quote.setName(stockQuotes.getQuote().getName());
		return (T) quote;
	}

	@Override
	public <T> T convertTo(Class<T> type, Exchange exchange, Object value) {
		return convertTo(type, value);
	}

	@Override
	public <T> T mandatoryConvertTo(Class<T> type, Object value) throws NoTypeConversionAvailableException {
		return convertTo(type, value);
	}

	@Override
	public <T> T mandatoryConvertTo(Class<T> type, Exchange exchange, Object value) throws NoTypeConversionAvailableException {
		return convertTo(type, value);
	}

}
