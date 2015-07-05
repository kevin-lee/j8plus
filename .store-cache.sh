#!/bin/bash

find $HOME/.sbt -name "*.lock" | xargs rm
find $HOME/.ivy2 -name "ivydata-*.properties" | xargs rm

echo "============================================"
echo "Copy Ivy cache to $SEMAPHORE_CACHE_DIR/app-ivy2-cache"
echo "--------------------------------------------"
if [ ! -d "$SEMAPHORE_CACHE_DIR/app-ivy2-cache" ];
  then
  echo "mkdir -p $SEMAPHORE_CACHE_DIR/app-ivy2-cache"
  mkdir -p "$SEMAPHORE_CACHE_DIR/app-ivy2-cache"
fi
cp -Rp "$HOME/.ivy2/cache" "$SEMAPHORE_CACHE_DIR/app-ivy2-cache/"
echo "Copy Ivy cache to $SEMAPHORE_CACHE_DIR/app-ivy2-cache: Done"
echo "============================================"
echo "Copy Sbt cache to $SEMAPHORE_CACHE_DIR/app-sbt-cache"
echo "--------------------------------------------"
if [ ! -d "$SEMAPHORE_CACHE_DIR/app-sbt-cache" ];
  then
  echo "mkdir -p $SEMAPHORE_CACHE_DIR/app-sbt-cache "
  mkdir -p "$SEMAPHORE_CACHE_DIR/app-sbt-cache"
fi
cp -Rp "$HOME/.sbt/boot" "$SEMAPHORE_CACHE_DIR/app-sbt-cache/"
echo "Copy Sbt cache to $SEMAPHORE_CACHE_DIR/app-sbt-cache: Done"
echo "============================================"
