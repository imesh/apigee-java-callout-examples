# Apigee Base64 Java Callout

This is an example Apigee Java Callout policy for base64 encoding and decoding string values.

## Prerequisites

Following prerequisites are required for compiling Java Callout source code:
1. JDK v1.8.x
2. Apache Maven v3.x

## How to Build

1. Clone this git repository and switch to base64-java-callout/ folder:

   ```bash
   git clone https://github.com/imesh/apigee-java-callout-examples
   cd apigee-java-callout-examples/base64-java-callout
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

## How to Base64 Encode

Use following Java Callout policy for base64 encoding a string value:

```xml
<JavaCallout name='Base64JavaCallout-Encode'>
  <Properties>
    <Property name="INTPUT_FLOW_VARIABLE">message.header.INPUT_VALUE</Property>
    <Property name="OUTPUT_FLOW_VARIABLE">response.content</Property>
    <Property name="ACTION">encode</Property>
  </Properties>
  <ClassName>com.imesh.apigee.callouts.Base64JavaCallout</ClassName>
  <ResourceURL>java://base64-java-callout-0.1.0.jar</ResourceURL>
</JavaCallout>
```

An Example API request:

```bash
curl -i -H "INPUT_VALUE: Hello world" https://${org}-${env}.apigee.net/base64-java-callout/encode

HTTP/1.1 200 OK
Date: Thu, 02 Jul 2020 06:35:56 GMT
Content-Length: 16
Connection: keep-alive

SGVsbG8gd29ybGQ=
```

## How to Base64 Decode

Use following Java Callout policy for decoding a base64 encoded value:

```xml
<JavaCallout name='Base64JavaCallout-Decode'>
  <Properties>
    <Property name="INTPUT_FLOW_VARIABLE">message.header.INPUT_VALUE</Property>
    <Property name="OUTPUT_FLOW_VARIABLE">response.content</Property>
    <Property name="ACTION">decode</Property>
  </Properties>
  <ClassName>com.imesh.apigee.callouts.Base64JavaCallout</ClassName>
  <ResourceURL>java://base64-java-callout-0.1.0.jar</ResourceURL>
</JavaCallout>
```

An Example API request:

```bash
curl -i -H "INPUT_VALUE: SGVsbG8gd29ybGQ=" https://${org}-${env}.apigee.net/base64-java-callout/decode

HTTP/1.1 200 OK
Date: Thu, 02 Jul 2020 06:36:52 GMT
Content-Length: 11
Connection: keep-alive

Hello world
```

## References
- [How to create an Apigee Java Callout](https://docs.apigee.com/api-platform/samples/cookbook/how-create-java-callout)
- [Apigee Java Callout Policy](https://docs.apigee.com/api-platform/reference/policies/java-callout-policy)
- [Apigee Flow Variables Reference](https://docs.apigee.com/api-platform/reference/variables-reference)

## License

[Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0)