@ECHO OFF
cls
cd ..
cd ..
echo "----  EXECUTANDO O TYPE-CHECK COM BASE NOS TESTES EM VARIOS ARQUIVOS ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -byt
echo "----  SCRIPT EXECUTADO COM SUCESSO ----"
pause