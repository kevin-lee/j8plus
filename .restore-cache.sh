#!/bin/bash

echo "Create $HOME/.ivy2"
mkdir -p "$HOME/.ivy2"
echo "Done"
# ls -l "$HOME/.ivy2"
# ls -l "$HOME/.ivy2/cache"
echo ""
echo "Create $HOME/.sbt"
mkdir -p "$HOME/.sbt"
echo "Done"
# ls -l "$HOME/.sbt"

if [ -d "$SEMAPHORE_CACHE_DIR/app-ivy2-cache" ];
  then
  echo "============================================"
  echo "Ivy Cache exists so copy it"
  echo "--------------------------------------------"
  cp -Rp "$SEMAPHORE_CACHE_DIR/app-ivy2-cache/cache" "$HOME/.ivy2/"
  ls -ld "$HOME/.ivy2/cache"
  ls -l "$HOME/.ivy2/cache"
  echo "--------------------------------------------"
  echo "Ivy Cache Copying: Done"
  echo "============================================"
fi
if [ -d "$SEMAPHORE_CACHE_DIR/app-sbt-cache" ];
  then
  echo "============================================"
  echo "SBT Cache exists so copy it"
  echo "--------------------------------------------"
  cp -Rp "$SEMAPHORE_CACHE_DIR/app-sbt-cache/boot" "$HOME/.sbt/"
  ls -ld "$HOME/.sbt/boot"
  ls -l "$HOME/.sbt/boot"
  echo "--------------------------------------------"
  echo "SBT Cache Copying: Done"
  echo "============================================"
fi
