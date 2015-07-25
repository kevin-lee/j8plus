#!/bin/bash

if [ -n "$1" ]
  then
  export GIT_TAG="v$1"
else
  export GIT_TAG="v$TRAVIS_BUILD_NUMBER"
fi
echo "GIT_TAG=$GIT_TAG"

echo "check: git ls-remote --exit-code --tags origin $GIT_TAG 2>&1 > /dev/null"

if git ls-remote --exit-code --tags origin $GIT_TAG 2>&1 > /dev/null ; then
  echo "the given tag: '$GIT_TAG' already exists so skip it!"
else
  echo "the given tag: '$GIT_TAG' does not exist so run it!"
  git config --global user.email "kevin.code@lckymn.com"
  git config --global user.name "Kecin-CI"

  git tag $GIT_TAG -a -m "Automatically generated tag by Travis CI for build $TRAVIS_BUILD_NUMBER"
  git push git@github.com:Kevin-Lee/j8plus --tags

  echo "======================================================"
  echo "ls -l target/scala-2.11/j8plus_*.jar"
  ls -l target/scala-2.11/j8plus_*.jar
  echo "======================================================"
  echo "Create a folder to put all the binary files."
  echo "------------------------------------------------------"
  echo "mkdir -p target/all-bin"
  mkdir -p target/all-bin
  echo "ls -l target/all-bin"
  ls -l target/all-bin

  echo "------------------------------------------------------"
  echo "cp target/scala-2.11/j8plus_*.jar target/all-bin/"
  cp target/scala-2.11/j8plus_*.jar target/all-bin/
  echo "------------------------------------------------------"
  echo "ls -lR target/all-bin"
  ls -lR target/all-bin
  echo "------------------------------------------------------"
  echo "Copying all binary files to 'target/all-bin': Done!"
  echo "======================================================"
fi
