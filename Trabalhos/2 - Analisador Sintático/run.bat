@ECHO OFF
cls
echo "----  EXECUTANDO O PROGRAMA DO ANALISADOR SINTATICO COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar;. lang/LangCompiler -bs 
echo "----  EXECUCAO CONCLUIDA COM SUCESSO - GERADA NO ARQUIVO DE SAIDA.TXT ----"
pause