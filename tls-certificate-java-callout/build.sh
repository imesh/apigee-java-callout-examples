#!/bin/bash
set -e

pushd callout
mvn clean install
popd

pushd bundle
zip -r tls-certificate-java-callout.zip *
mv tls-certificate-java-callout.zip ../
popd
echo "DONE!"
