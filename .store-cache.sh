#!/bin/bash

find $HOME/.sbt -name "*.lock" | xargs rm
find $HOME/.ivy2 -name "ivydata-*.properties" | xargs rm

echo "============================================"
echo "Copy Ivy cache to $SEMAPHORE_CACHE_DIR/app-ivy2-cache"
echo "--------------------------------------------"
cp -Rp "$HOME/.ivy2/cache" "$SEMAPHORE_CACHE_DIR/app-ivy2-cache"
echo "Copy Ivy cache to $SEMAPHORE_CACHE_DIR/app-ivy2-cache: Done"
echo "============================================"
echo "Copy Sbt cache to $SEMAPHORE_CACHE_DIR/app-sbt-cache"
echo "--------------------------------------------"
cp -Rp "$HOME/.sbt/boot" "$SEMAPHORE_CACHE_DIR/app-sbt-cache"
echo "Copy Sbt cache to $SEMAPHORE_CACHE_DIR/app-sbt-cache: Done"
echo "============================================"
