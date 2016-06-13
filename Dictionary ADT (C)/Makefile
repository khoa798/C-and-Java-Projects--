#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient2.c
OBJECTS = Dictionary.o DictionaryClient2.o
HEADERS = Dictionary.h
EXEBIN  = DictionaryClient
SUBMIT = submit cmps012b-pt.s16 pa5

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN)

submit:
	$(SUBMIT) $(SOURCES) Makefile README Dictionary.h DictionaryTest.c