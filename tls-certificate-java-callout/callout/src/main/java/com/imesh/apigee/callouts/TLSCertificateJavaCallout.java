package com.imesh.apigee.callouts;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Map;

/**
 * Apigee Java callout for reading TLS certificate information.
 */
public class TLSCertificateJavaCallout implements Execution {

  private String tlsCertificateFlowVariable;
  private String outputPrefix;

  public TLSCertificateJavaCallout(Map<String, String> properties) {
    this.tlsCertificateFlowVariable = properties.get("TLS_CERTIFICATE_FLOW_VARIABLE");
    this.outputPrefix = properties.get("OUTPUT_PREFIX");
  }

  public ExecutionResult execute(final MessageContext messageContext, final ExecutionContext executionContext) {
    try {
      if (this.tlsCertificateFlowVariable != null) {
        String tlsCertificateEncodedValue = messageContext.getVariable(this.tlsCertificateFlowVariable);
        if (tlsCertificateEncodedValue != null) {
          byte[] decodedValue = Base64.getDecoder().decode(tlsCertificateEncodedValue.getBytes());
          String tlsCertificate = new String(decodedValue, "UTF-8");

          CertificateFactory fact = CertificateFactory.getInstance("X.509");
          InputStream stream = new ByteArrayInputStream(tlsCertificate.getBytes(StandardCharsets.UTF_8));

          X509Certificate cert = (X509Certificate) fact.generateCertificate(stream);
          if (cert != null) {
            messageContext.setVariable(outputPrefix + ".subject", cert.getSubjectDN());
            messageContext.setVariable(outputPrefix + ".serial.no", cert.getSerialNumber());
            messageContext.setVariable(outputPrefix + ".valid.from", cert.getNotBefore());
            messageContext.setVariable(outputPrefix + ".valid.to", cert.getNotAfter());
            messageContext.setVariable(outputPrefix + ".issuer", cert.getIssuerDN());
            messageContext.setVariable(outputPrefix + ".public.key", cert.getPublicKey().getAlgorithm());
            messageContext.setVariable(outputPrefix + ".sig.alg.name", cert.getSigAlgName());
            messageContext.setVariable(outputPrefix + ".subject.alternative.names", cert.getSubjectAlternativeNames());
          }
        }
      }
      return ExecutionResult.SUCCESS;
    } catch (Exception e) {
      messageContext.setVariable("tls-certificate-java-callout-error", e.getMessage());
      return ExecutionResult.ABORT;
    }
  }
}
