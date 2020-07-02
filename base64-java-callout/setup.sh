#!/bin/bash
set -e

echo "Downloading expressions-1.0.0.jar..."
curl -O https://raw.githubusercontent.com/apigee/api-platform-samples/master/doc-samples/java-cookbook/lib/expressions-1.0.0.jar

echo "Installing expressions-1.0.0.jar to the local Maven repository..."
mvn install:install-file \
  -Dfile=expressions-1.0.0.jar \
  -DgroupId=com.apigee.edge \
  -DartifactId=expressions \
  -Dversion=1.0.0 \
  -Dpackaging=jar \
  -DgeneratePom=true
rm expressions-1.0.0.jar 

echo "Downloading message-flow-1.0.0.jar..."
curl -O https://raw.githubusercontent.com/apigee/api-platform-samples/master/doc-samples/java-cookbook/lib/message-flow-1.0.0.jar

echo "Installing message-flow-1.0.0.jar to the local Maven repository..."
mvn install:install-file \
  -Dfile=message-flow-1.0.0.jar \
  -DgroupId=com.apigee.edge \
  -DartifactId=message-flow \
  -Dversion=1.0.0 \
  -Dpackaging=jar \
  -DgeneratePom=true
rm message-flow-1.0.0.jar 

echo "DONE!"
