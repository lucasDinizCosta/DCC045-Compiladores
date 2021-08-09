 
all : compile


parsers/MiniLangParser.java: 
	java -jar parsers/beaver-cc-0.9.11.jar -T parsers/MiniLang.grammar 
	

parsers/MiniLangLex.java : 
	java -jar parsers/jflex-full-1.8.2.jar  -nobak parsers/MiniLang.jflex 

parser:  parsers/MiniLangParser.java parsers/MiniLangLex.java

compileAll : parsers/MiniLangParser.java parsers/MiniLangLex.java
	find . -name "*.java" > javalist
	javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jat @javalist
	rm -f javalist

compile : parsers/MiniLangParser.java parsers/MiniLangLex.java
	javac -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar MiniLang.java

run: compile
	java -cp .:beaver-rt-0.9.11.jar:ST-4.3.1.jar:antlr-3.5.2-runtime.jar MiniLang $(filter-out $@,$(MAKECMDGOALS))
	
%:
	@:

clean: 
	rm -f parsers/MiniLangParser.java
	rm -f parsers/MiniLangLex.java
	rm -f parsers/Terminals.java
	rm -f ast/*.class
	rm -f parsers/*.class
	rm -f *.class
	find . -name "*.class" -delete
	
cleanClass :
	find . -name "*.class" -delete
	
cleanParser: 
	rm -f parsers/MiniLangParser.java
	rm -f parsers/MiniLangLex.java
	rm -f parsers/Terminals.java
