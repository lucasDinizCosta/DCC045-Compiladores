#!/bin/bash
cls
del LextTest.*
del *.class
java -jar jflex-full-1.8.2.jar Lang.jflex
javac -cp . Lang.java
java Lang input.txt