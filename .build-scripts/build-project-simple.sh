#!/bin/bash -e

set -x

if [ -z "$1" ]
  then
    echo "Scala version is missing. Please enter the Scala version."
    echo "build-project.sh 2.11.12"
else
  scala_version=$1
  echo "============================================"
  echo "Build projects"
  echo "--------------------------------------------"
  echo ""
  if [[ "$BRANCH_NAME" == "rc" ]]
  then
    sbt -d -J-Xmx2048m "; ++ ${scala_version}!; clean; test"
    sbt -d -J-Xmx2048m "; ++ ${scala_version}!; packageBin; packageSrc; packageDoc"
  else
    sbt -d -J-Xmx2048m "; ++ ${scala_version}!; clean; test; package"
  fi

  echo "============================================"
  echo "Building projects: Done"
  echo "============================================"
fi
