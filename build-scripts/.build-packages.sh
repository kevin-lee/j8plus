#!/bin/bash -e

echo "======================================================"
echo "Build packages"
echo "------------------------------------------------------"

echo ""
echo "======================================================"
echo "Run: sbt package writeVersion "
echo "------------------------------------------------------"
if sbt clean package writeVersion ; then
  echo "Done: sbt clean package writeVersion "
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
