<html>
<body>
<h2>Discount ASCII Warehouse Ecommerce REST API</h2>
<p>
<pre>
This is the Discount ASCII Warehouse Ecommerce REST API, 
which produces a JSON String with a list of "Popular purchases", 
so customers can see who else bought the same products as them.

The application accepts HTTP requests to 
WILDFLY_SERVER_URL/api/recent_purchases/{username} and respond 
with a list of recently purchased products, and the names of 
other users who recently purchased them.

This system was built and documented for use on JBoss 
Wildfly-8.1.0.CR1 running on Java jdk1.8.0_60 for Windows 8.1 64b.

It was compiled with Apache Maven 3.2.5 and 
Java 1.6.0_29 on Windows 8.1 64b.


[REQUIREMENTS & INSTALATION]

- Requires server with installed JBoss Wildfly-8.1.0.CR1 at your
desired WILDFLY_PATH, running on Java jdk1.8.0_60.

- The provided WAR file (exam-1.0.war) must be deployed under the path:
WILDFLY_PATH\standalone\deployments

- The server must have visibility to perform HTTP GET operations 
to Consume the API "daw-purchases", started and installed according 
to this reference:
https://github.com/x-team/daw-purchases/blob/master/README.md#api-reference

- The addition of a system property directly into the configuration file:
WILDFLY_PATH\standalone\configuration\standalone.xml

..add this element after the </EXTENSIONS> element...

&lt;system-properties&gt;
	&lt;property name="com.discountasciiwarehouse.ecommerce.outside.resource" value="http://localhost:8000/api/"/&gt;
&lt;/system-properties&gt;

... and change the {value="http://localhost:8000/api/"} according 
to your "daw-purchases" API URL address from this server.

- This concludes the installation. Initialize your JBoss 
Wildfly-8.1.0.CR1 normally (please address your local 
support/vendor/admin about HOWTO).


[OPTIONS]

Under a new build ("mvn clean install" command, on a Maven able Prompt), 
the WILDFLY_SERVER_URL/api/ URL can be changed (please remember to also 
reflect the change at <system-properties> on standalone.xml) such as:

- WILDFLY_SERVER_URL = edit your WILDFLY URL servers domain host name or IP.

- the silent domain name "/" = edit the <context-root>/</context-root> 
element under \src\main\webapp\WEB-INF\jboss-web.xml  

- /api/ = edit the sources files, under the \src\main\webapp\WEB-INF\web.xml, 
and change in the element <url-pattern>/api/*</url-pattern>


[DESIGN CONSIDERATIONS]

- The API returns a ClientResponse in JSON format. 

- The errors are kept at the system log according to Log4J configurations.

- There is some basic CDI Injections implementations, and they follow 
rules expected by the Wildfly's Weld API.

- The REST response has some CACHE http headers, so the consumer API 
must return them in order for proper cache operation. Also, some specific 
refactors were made for the sake of greater speed. 

- Example call: http://localhost:8080/api/recent_purchases/Delilah59
 
- Example response: [{"recent":["Myriam_Nitzsche","Camylle.OKon93",
"Delilah59"],"id":508699,"face":"o_o","price":792.0,"size":14}]
</pre>
</p>
</body>
</html>
