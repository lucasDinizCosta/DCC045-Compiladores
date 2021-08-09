@ECHO OFF
cls
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . ast/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . langUtil/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . parsers/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . visitors/*.java
javac -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar -d . MiniLang.java
pause