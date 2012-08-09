package com.ninjasquad.quote.endpoint.out;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface QuoteOutEndpoint {

	public String GetQuote(@WebParam(name = "symbol") String symbol);

}
