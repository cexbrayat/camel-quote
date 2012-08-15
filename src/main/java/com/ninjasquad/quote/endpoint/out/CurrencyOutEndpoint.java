package com.ninjasquad.quote.endpoint.out;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CurrencyOutEndpoint {

	public Double ConversionRate(@WebParam(name = "FromCurrency") String from, @WebParam(name = "ToCurrency") String to);

}
