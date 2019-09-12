#!/bin/bash -e

echo "============================================"
echo "Java Info"
java -version
echo "--------------------------------------------"
echo "Scala Info"
scala -version
echo "--------------------------------------------"
echo "SBT Info"
sbt sbtVersion || :
echo "--------------------------------------------"
echo "Memory Info"
echo "--------------------------------------------"
echo "free -m"
free -m
echo "--------------------------------------------"
echo "grep MemTotal /proc/meminfo"
grep MemTotal /proc/meminfo
echo "--------------------------------------------"
