#!/bin/bash

pushd plat_plus
./gradlew html:dist
popd 
cp -r ./plat_plus/html/build/dist/* ./docs
git add .
git commit -m "web deploy updated"
git push origin master
