#!/usr/bin/env bash

ORIGIN_FILE="sample/sample-0-origin.html"
FILE_ONE="sample/sample-1-evil-gemini.html"
FILE_TWO="sample/sample-2-container-and-clone.html"
FILE_THREE="sample/sample-3-the-escape.html"
FILE_FOUR="sample/sample-4-the-mash.html"

java -jar target/smartxml-0.0.1-jar-with-dependencies.jar -o $ORIGIN_FILE -n $FILE_ONE -i make-everything-ok-button
#java -jar target/smartxml-0.0.1-jar-with-dependencies.jar -o $ORIGIN_FILE -n $FILE_TWO -i make-everything-ok-button
#java -jar target/smartxml-0.0.1-jar-with-dependencies.jar -o $ORIGIN_FILE -n $FILE_THREE -i make-everything-ok-button
#java -jar target/smartxml-0.0.1-jar-with-dependencies.jar -o $ORIGIN_FILE -n $FILE_FOUR -i make-everything-ok-button
