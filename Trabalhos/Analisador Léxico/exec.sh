#!/bin/bash
rm *.class
echo "Limpando os Arquivos .class ...."
javac -cp . Lang.java
echo "Compilando o Código Java..."
java Lang input.txt