echo "----  EXECUTANDO O TYPE-CHECK COM BASE NOS TESTES ----"
java -cp lib/ANTLR.jar:. lang/LangCompiler -ti ./testes/semantica/certo/teste1.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"