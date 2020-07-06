# Apigee TLS Certificate Java Callout

This is an example Apigee Java Callout policy for reading a TLS certificate.

## Prerequisites

Following prerequisites are required for compiling Java Callout source code:
1. JDK v1.8.x
2. Apache Maven v3.x

## How to Build

1. Clone this git repository and switch to tls-certificate-java-callout/ folder:

   ```bash
   git clone https://github.com/imesh/apigee-java-callout-examples
   cd apigee-java-callout-examples/tls-certificate-java-callout
   ```

2. Execute `setup.sh` script for downloading dependent Apigee JAR files and adding them to the local Maven repository:

   ```bash
   ./setup.sh
   ```

3. Execute `build.sh` script for building Java Callout JAR file and generating API proxy bundle:

   ```bash
   ./build.sh
   ```

4. Upload generated API bundle to an Apigee Edge organization and deploy it in an environment.

## How to Read a TLS Certificate

Use following Java Callout policy for reading a base64 encoded TLS certificate sent in a HTTP header:

```xml
<JavaCallout name='TLSCertificateJavaCallout'>
  <Properties>
    <Property name="TLS_CERTIFICATE_FLOW_VARIABLE">message.header.TLS_CERT</Property>
    <Property name="OUTPUT_PREFIX">tls-certificate-java-callout</Property>
  </Properties>
  <ClassName>com.imesh.apigee.callouts.TLSCertificateJavaCallout</ClassName>
  <ResourceURL>java://tls-certificate-java-callout-0.1.0.jar</ResourceURL>
</JavaCallout>
```

An Example API request:

```bash
curl -i -H "TLS_CERT: $(cat foo.org.pem | base64)" https://${org}-${env}.apigee.ne/tls-certificate-java-callout/read-tls-cert
HTTP/1.1 200 OK
Date: Mon, 06 Jul 2020 07:00:21 GMT
Content-Type: application/json
Content-Length: 418
Connection: keep-alive

{
    "subject": "EMAILADDRESS=abc@foo.org, CN=foo.org, OU=BB, O=BB Ltd, L=BB, ST=BB, C=BB",
    "serialNo": "13241496675932278445",
    "validFrom": "Fri Oct 04 05:04:35 UTC 2019",
    "validTo": "Sat Oct 03 05:04:35 UTC 2020",
    "issuer": "EMAILADDRESS=abc@ca.foo.org, CN=ca.foo.org, OU=AA, O=AA Ltd, L=AA, ST=AA, C=AA",
    "publicKey": "RSA",
    "sigAlgName": "SHA1withRSA",
    "subjectAlternativeNames": ""
}
```

## References
- [How to create an Apigee Java Callout](https://docs.apigee.com/api-platform/samples/cookbook/how-create-java-callout)
- [Apigee Java Callout Policy](https://docs.apigee.com/api-platform/reference/policies/java-callout-policy)
- [Apigee Flow Variables Reference](https://docs.apigee.com/api-platform/reference/variables-reference)

## Support

Java Callout policies included in this repository are not supported by Apigee. Please [create an issue](https://github.com/imesh/apigee-java-callout-examples/issues/new) for reporting any problems or providing feedback.

## License

[Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0)
