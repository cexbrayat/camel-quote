package com.ninjasquad.quote.converter;

import org.apache.camel.Exchange;
import org.apache.camel.NoTypeConversionAvailableException;
import org.apache.camel.TypeConverter;
import org.springframework.beans.BeanUtils;

import com.ninjasquad.quote.model.Quote;
import com.ninjasquad.quote.model.StockQuote;
import com.ninjasquad.quote.model.StockQuotes;

public class QuoteConverter implements TypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convertTo(Class<T> type, Object value) {
		Quote quote = new Quote();
		if (value == null) {
			return null;
		}
		StockQuotes stockQuotes = (StockQuotes) value;
		StockQuote stockQuote = stockQuotes.getQuote();
		BeanUtils.copyProperties(stockQuote, quote);
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
