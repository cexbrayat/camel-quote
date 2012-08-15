camel-quote
===========

Why
---
Sample camel project that aggregate two soap web services to create a new one.

The first webservice is [stockquote](http://www.webservicex.net/stockquote.asmx?wsdl). It takes a stock name (e.g. GOOG) and send back various informations, such as complete name, last quote in USD, etc...

The second webservice is [currencyconvertor](http://www.webservicex.net/currencyconvertor.asmx?wsdl). It takes two currencies and return the rate to convert the first one into the second.

The camel program wait for a soap request with a quote name and a currency, then call the two webservices to return the quote's price in the asked currency.

How
---
A soap request to http://localhost:9000/quote
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:in="http://in.endpoint.quote.ninjasquad.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <in:price>
         <symbol>GOOG</symbol>
         <currency>EUR</currency>
      </in:price>
   </soapenv:Body>
</soapenv:Envelope>
```

will return

```
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:priceResponse xmlns:ns2="http://in.endpoint.quote.ninjasquad.com/">
         <quote>
            <name>Google Inc.</name>
            <symbol>GOOG</symbol>
            <currency>EUR</currency>
            <price>542.35</price>
         </quote>
      </ns2:priceResponse>
   </soap:Body>
</soap:Envelope>
```
