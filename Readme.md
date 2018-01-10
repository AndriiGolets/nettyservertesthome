#Test Program for compare perfomance of different netty realizations 

CamelNettyServer - Netty Server Based on Apache camel

PureNettyServer - Pure Netty Server 

SpringRestClient - client what create 100 client threads and use RestTemplate.exchange
for sending and reciveing Http request - response

Request and Response is very simple with little String body 

For Test run first server realization and after SpringRestClient

I got for CamelNetty near 13500 requests handled per second and 30000 for PureNettyRealization  


