#!/bin/bash

./gradlew buildPlugin || exit
cp build/distributions/*  ~/Downloads/ || exit
echo "Build successful! Check ~/Downloads/ for the result."