#!/bin/bash
cls
@echo off
timeout 2 /nobreak
echo "=========> Limpando o Lexer antigo...."
timeout 1 /nobreak
del Lexer.*
echo "=========> Limpando os Arquivos .class ...."
timeout 1 /nobreak
del *.class
echo "=========> Executando o JFLEX para criar o analisador lexico da linguagem Lang"
timeout 1 /nobreak
java -jar jflex-full-1.8.2.jar Lang.jflex
echo "=========> Compilando os arquivos java"
timeout 1 /nobreak
javac -cp . Lang.java
echo "=========> Executando o analisador léxico com o arquivo \'input.txt\'"
timeout 1 /nobreak
java Lang input.txt
pause
