#!/bin/bash -e

echo ""
echo "======================================================"
echo "Start to deploy packages"
echo "------------------------------------------------------"

export THIS_BRANCH="$BRANCH_NAME"
if [ "$THIS_BRANCH" == "release" ];
  then

  export VERSION_FILE="target/version.tmp"
  echo "Version File=$VERSION_FILE"

  if [ ! -f "$VERSION_FILE" ]
    then
    echo "NO version file, '$VERSION_FILE', exists so quit!" 1>&2
    exit 1
  fi

  export PROJECT_VERSION=`cat $VERSION_FILE`
  echo "PROJECT_VERSION=$PROJECT_VERSION"

  if [ ! -n "$PROJECT_VERSION" ]
    then
    echo "NO PROJECT_VERSION is found so quit!" 1>&2
    exit 1
  fi

  export GIT_TAG="v$PROJECT_VERSION"
  echo "GIT_TAG=$GIT_TAG"
  export PROJECT_BUILD_NAME="$GIT_TAG"
  echo "PROJECT_BUILD_NAME=$PROJECT_BUILD_NAME"

  echo "======================================================"
  echo "Running: git tag | xargs -n1 git tag -d"
  echo "------------------------------------------------------"
  git tag | xargs -n1 git tag -d
  echo "======================================================"
  echo "Running: git fetch --tags"
  echo "------------------------------------------------------"
  git fetch --tags
  echo "======================================================"

  echo "check git ls-remote --exit-code --tags origin $GIT_TAG 2>&1 > /dev/null"

  if git ls-remote --exit-code --tags origin $GIT_TAG 2>&1 > /dev/null ; then
    echo "------------------------------------------------------"
    echo "[ERROR] the given tag '$GIT_TAG' already exists!" 2>&1
    echo "======================================================"
    exit 1
  else
    echo "------------------------------------------------------"
    echo "the given tag '$GIT_TAG' does not exist so run it!"

    export REPO_LOCATION="git@github.com:Kevin-Lee/j8plus.git"
    echo "REPO_LOCATION=$REPO_LOCATION"

    export USER_EMAIL="builder+github@lckymn.com"
    echo "USER_EMAIL=$USER_EMAIL"

    export USER_NAME="Kevin-App-Builder"
    echo "USER_NAME=$USER_NAME"

    git config --global user.email "$USER_EMAIL"
    git config --global user.name "$USER_NAME"

    echo "======================================================"
    echo "Running: git tag $GIT_TAG -a -m Automatically generated tag by Semaphore CI for $GIT_TAG"
    echo "------------------------------------------------------"
    git tag "$GIT_TAG" -a -m "Automatically generated tag by Semaphore CI for $GIT_TAG"
    echo "======================================================"
    echo "git push $REPO_LOCATION --tags"
    echo "------------------------------------------------------"
    git push "$REPO_LOCATION" --tags
    echo "======================================================"

  fi

  ./.release-files-copy.sh
  ./.deploy-to-github.sh

  echo "======================================================"
  echo "Build and Deploy: Done"
  echo "======================================================"
else
  echo "======================================================"
  echo "It is not release branch so skip deployment."
  echo "Build: Done"
  echo "======================================================"
fi
