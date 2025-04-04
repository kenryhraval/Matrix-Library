#!/bin/bash
javac -d out -cp src $(find src test -name "*.java")
java -cp out matrix.Main