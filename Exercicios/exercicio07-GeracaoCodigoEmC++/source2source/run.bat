@ECHO OFF
cls
echo "---- EXECUTANDO O PROGRAMA COM O ARQUIVO TESTE ----"
java -cp .;beaver-rt-0.9.11.jar;ST-4.3.1.jar;antlr-3.5.2-runtime.jar MiniLang Teste.txt -Python
echo "----------------------------------------------------"
pause