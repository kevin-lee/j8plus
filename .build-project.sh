#!/bin/bash -e

echo "============================================"
echo "Build projects"
echo "--------------------------------------------"
echo ""
echo "============================================"
echo "Run: sbt clean coverage test package"
echo "--------------------------------------------"
if sbt clean coverage test package ; then
  echo "Done: sbt clean coverage test package"
  echo "============================================"
else
  echo "Failed: sbt clean coverage test package" 1>&2
  echo "============================================"
  exit 1
fi

echo ""

echo "============================================"
echo "Run: sbt coveralls"
echo "--------------------------------------------"
if sbt coveralls ; then
  echo "Done: sbt coveralls"
  echo "============================================"
else
  echo "Failed: sbt coveralls" 1>&2
  echo "============================================"
  exit 1
fi
echo ""
echo "============================================"
echo "Building projects: Done"
echo "============================================"
