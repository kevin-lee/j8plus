#!/bin/bash

if [ ! -n "$PROJECT_BUILD_NAME" ]
  then
  echo "NO PROJECT_BUILD_NAME is found so quit!" 1>&2
  exit 1
fi

echo "======================================================"
echo "Release to Bintray"
echo "======================================================"
echo ""
echo "Run: sbt publish"
echo "------------------------------------------------------"
if sbt publish ; then
  echo "Done: sbt publish"
else
  echo "Failed: sbt publish" 1>&2
  exit 1
fi
echo "======================================================"


echo "======================================================"
echo "Copy packaged files to deploy"
echo "======================================================"
echo "ls -l target/scala-2.11/*.jar"
ls -l target/scala-2.11/*.jar
echo ""
echo "======================================================"
if [ -d "target/ci" ]; then
  echo "Clean up existing target/ci/*"
  echo "rm -R target/ci/*"
  rm -R target/ci/*
  echo "------------------------------------------------------"
fi
echo "Create a folder to put all the binary files."
echo "------------------------------------------------------"
echo "mkdir -p target/ci/$PROJECT_BUILD_NAME"
mkdir -p "target/ci/$PROJECT_BUILD_NAME"
echo "ls -l target/ci/$PROJECT_BUILD_NAME"
ls -l "target/ci/$PROJECT_BUILD_NAME"

echo "------------------------------------------------------"
echo "cp target/scala-2.11/*.jar target/ci/$PROJECT_BUILD_NAME/"
cp target/scala-2.11/*.jar "target/ci/$PROJECT_BUILD_NAME/"
echo "------------------------------------------------------"
echo "ls -lR target/ci/$PROJECT_BUILD_NAME/"
ls -lR "target/ci/$PROJECT_BUILD_NAME"
echo "------------------------------------------------------"
echo "Copying all binary files to 'target/ci', Done!"
echo "======================================================"
