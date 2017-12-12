all: brun

build:
	javac Srt.java

run:
	java Srt

brun:
	make build && make run