#!/bin/bash -e

set -x

echo ""
echo "======================================================"
echo "Start to deploy packages"
echo "------------------------------------------------------"

export USER_EMAIL="builder+github@kevinlee.io"
echo "USER_EMAIL=$USER_EMAIL"

export USER_NAME="Kevin-App-Builder"
echo "USER_NAME=$USER_NAME"

git config --global user.email "$USER_EMAIL"
git config --global user.name "$USER_NAME"

alias sbt='sbt -d -J-Xmx2048m'

echo "======================================================"
echo "Release to Bintray"
echo "======================================================"
sbt -d -J-Xmx2048m +publish
echo "======================================================"

echo "======================================================"
echo "Release to GitHub"
echo "======================================================"
sbt gitHubRelease
echo "======================================================"

echo "======================================================"
echo "Build and Deploy: Done"
echo "======================================================"
