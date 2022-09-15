#!/bin/bash

#  build java files in src directory and put them in bin directory
javac -d bin src/**.java; cd bin && rmic Server
