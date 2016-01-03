This is the Discount ASCII Warehouse Ecommerce REST API, which produces a JSON String with a list of "Popular purchases", so customers can see who else bought the same products as them.

The application accepts HTTP requests to WILDFLY_SERVER_URL/api/recent_purchases/{username} and respond with a list of recently purchased products, and the names of other users who recently purchased them.

This system was built for use on JBoss Wildfly-8.1.0.CR1 running on Java jdk1.8.0_60 for Windows 8.1 64b.

It was compiled with Apache Maven 3.2.5 and Java 1.6.0_29 on Windows 8.1 64b.


[REQUIREMENTS & INSTALATION]

- Server with installed JBoss Wildfly-8.1.0.CR1 at your own WILDFLY_PATH, running on Java jdk1.8.0_60.

- The provided WAR file deployed under the path:
WILDFLY_PATH\standalone\deployments

- The server must have visibility to perform HTTP GET operations to Consume the API "daw-purchases", started and installed according to this reference:
https://github.com/x-team/daw-purchases/blob/master/README.md#api-reference

- The addition of a system property directly into the configuration file:
WILDFLY_PATH\standalone\configuration\standalone.xml

..add this element after the </EXTENSIONS> element...

<system-properties>
    <property name="com.discountasciiwarehouse.ecommerce.outside.resource" value="http://localhost:8000/api/"/>
</system-properties>

... and change the {value="http://localhost:8000/api/"} according to your "daw-purchases" API exposition to this server.


[OPTIONS]

Under a new build (mvn clean install command in Maven able Prompt), the WILDFLY_SERVER_URL/api/ URL (please remember to also reflect the change at <system-properties> on standalone.xml) the can be changed such as:

- WILDFLY_SERVER_URL = edit your WILDFLY URL servers domain host name or IP.

- the silent domain name "/" = edit the <context-root>/</context-root> element under \src\main\webapp\WEB-INF\jboss-web.xml  

- /api/ = edit the sources files, under the \src\main\webapp\WEB-INF\web.xml, and change in the element <url-pattern>/api/*</url-pattern>


[DESIGN CONSIDERATIONS]

- The API returns a plain string in JSON format, not a ClientResponse. This helps the return to be "silent" in case of system errors, so no Consuming API will print Exception Errors or Codes by mistake to a user. The errors still are kept at the system log. The System can also be easily refactored to return a ClientResponse and proper error code like 500 and so on.

- There is some basic CDI Injections implementations, and they follow rules expected by the Wildfly's Weld API.

- Developing phase had some Junit Tests that were erased, since the system "daw-purchases" changes its values on every new startup, and the simple being to simple to mock it all.

- The REST response has CACHE http headers, so the Consuming API may be configured to use them and speed up the  operations. Also, the API itself has CACHE abilities over the "daw-purchases" responses, again, for the sake of speed. 
 
- Example response:
[{"recent":["Myriam_Nitzsche","Camylle.OKon93","Delilah59"],"id":508699,"face":"o_o","price":792.0,"size":14}]