package com.ninjasquad.quote.endpoint.in;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.ninjasquad.quote.model.Quote;

@WebService
public interface QuoteInEndpoint {

	@WebResult(name = "quote")
	public Quote price(@WebParam(name = "symbol") String symbol, @WebParam(name = "currency") String currency);

}
