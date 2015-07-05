#!/bin/bash -e

echo "======================================================"
echo "Build packages"
echo "------------------------------------------------------"

sbt clean
sbt writeVersion

echo ""
echo "======================================================"
echo "Run: sbt package"
echo "------------------------------------------------------"
if sbt clean package ; then
  echo "Done: sbt package"
  echo "======================================================"
else
  echo "Failed: sbt package" 1>&2
  echo "======================================================"
  exit 1
fi

echo ""
echo "======================================================"
echo "Building Packages: Done"
echo "======================================================"

sbt writeVersion
