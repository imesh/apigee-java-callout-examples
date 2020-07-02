package com.imesh.apigee.callouts;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;
import java.util.Base64;
import java.util.Map;

/**
 * Apigee Java callout for base64 encoding and decoding string values.
 */
public class Base64JavaCallout implements Execution {

  private String inputFlowVariable;
  private String outputFlowVariable;
  private String action;

  public Base64JavaCallout(Map<String, String> properties) {
    this.inputFlowVariable = properties.get("INTPUT_FLOW_VARIABLE");
    this.outputFlowVariable = properties.get("OUTPUT_FLOW_VARIABLE");
    this.action = properties.get("ACTION");
  }

  public ExecutionResult execute(final MessageContext messageContext, final ExecutionContext executionContext) {
    try {
      if(this.inputFlowVariable != null && this.outputFlowVariable != null && this.action != null) {
        if("encode".equals(this.action)) {
          String inputValue = messageContext.getVariable(this.inputFlowVariable);
          if(inputValue != null) {
            byte[] encodedValue = Base64.getEncoder().encode(inputValue.getBytes());
            messageContext.setVariable(this.outputFlowVariable, new String(encodedValue, "UTF-8"));
          }
        } else if("decode".equals(this.action)) {
          String inputValue = messageContext.getVariable(this.inputFlowVariable);
          if(inputValue != null) {
            byte[] decodedValue = Base64.getDecoder().decode(inputValue.getBytes());
            messageContext.setVariable(this.outputFlowVariable, new String(decodedValue, "UTF-8"));
          }
        }
      }
      return ExecutionResult.SUCCESS;
    } catch (Exception e) {
      messageContext.setVariable("base64-java-callout-error", e.getMessage());
      return ExecutionResult.ABORT;
    }
  }
}
