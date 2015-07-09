#!/bin/bash

echo "Create $HOME/.ivy2 if not exists"
mkdir -p "$HOME/.ivy2"
echo "Done"
# ls -l "$HOME/.ivy2"
# ls -l "$HOME/.ivy2/cache"
echo ""
echo "Create $HOME/.sbt if not exists"
mkdir -p "$HOME/.sbt"
echo "Done"
# ls -l "$HOME/.sbt"

if [ -d "$SEMAPHORE_CACHE_DIR/app-ivy2-cache" ];
  then
  echo "============================================"
  echo "Ivy Cache exists so copy it"
  echo "--------------------------------------------"
  echo "cp -Rp $SEMAPHORE_CACHE_DIR/app-ivy2-cache/cache $HOME/.ivy2/"
  cp -Rp "$SEMAPHORE_CACHE_DIR/app-ivy2-cache/cache" "$HOME/.ivy2/"

  echo "ls -ld $HOME/.ivy2/cache"
  ls -ld "$HOME/.ivy2/cache"
  echo "ls -l $HOME/.ivy2/cache"
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
  echo "cp -Rp $SEMAPHORE_CACHE_DIR/app-sbt-cache/boot $HOME/.sbt/"
  cp -Rp "$SEMAPHORE_CACHE_DIR/app-sbt-cache/boot" "$HOME/.sbt/"

  echo "ls -ld $HOME/.sbt/boot"
  ls -ld "$HOME/.sbt/boot"
  echo "ls -l $HOME/.sbt/boot"
  ls -l "$HOME/.sbt/boot"
  echo "--------------------------------------------"
  echo "SBT Cache Copying: Done"
  echo "============================================"
fi
