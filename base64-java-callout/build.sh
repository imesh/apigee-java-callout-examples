#!/bin/bash
set -e

pushd callout
mvn clean install
popd

pushd bundle
zip -r base64-java-callout.zip *
mv base64-java-callout.zip ../
popd
echo "DONE!"
