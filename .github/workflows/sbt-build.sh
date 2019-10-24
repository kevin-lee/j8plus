#!/bin/bash -e

set -x

if [ -z "$1" ]
  then
    echo "Scala version is missing. Please enter the Scala version."
    echo "sbt-build.sh 2.11.12"
else
  scala_version=$1
  echo "============================================"
  echo "Build projects"
  echo "--------------------------------------------"
  echo ""
  if [[ "$CI_BRANCH" == "master" || "$CI_BRANCH" == "release" ]]
  then
    sbt -d -J-Xmx2048m "; ++ ${scala_version}!; clean; jacoco"
    sbt -d -J-Xmx2048m "; ++ ${scala_version}!; packagedArtifacts"
  else
    sbt -d -J-Xmx2048m "; ++ ${scala_version}!; clean; jacoco; package"
  fi
  sbt -d -J-Xmx2048m "; ++ ${scala_version}!; jacocoCoveralls"

  echo "============================================"
  echo "Building projects: Done"
  echo "============================================"
fi
