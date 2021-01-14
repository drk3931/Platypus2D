#!/bin/bash

rm -rf ./docs/*

pushd plat_plus
./gradlew html:dist --console=plain
popd 
cp -r ./plat_plus/html/build/dist/* ./docs
git add .
git commit -m "web deploy updated"
git push origin master
