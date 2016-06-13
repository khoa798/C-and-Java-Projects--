#------------------------------------------------------------------------------
# A Makefile with macros for Search
#------------------------------------------------------------------------------
JAVASRC = Search.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Search
CLASSES = Search.class
JARFILE = Search
SUBMIT = submit cmps012b-pt.s16 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
	
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
	
check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s16/pa2/klhoang

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
