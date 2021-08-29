echo "----  EXECUTANDO O CODE-GENERATOR ----"
java -cp lib/ANTLR.jar:lib/ST-4.3.1.jar:. lang/LangCompiler -Java ./testes/semantica/certo/teste3.lan -genFile ./codigosGerados/teste3.lan
echo "----  EXECUCAO CONCLUIDA COM SUCESSO ----"