MAKEFLAGS := --silent
OUTDIR := build

clean:
	rm -rf $(OUTDIR)

build: clean
	javac -d $(OUTDIR) $(shell find . -name "*.java")
	jar -cmf manifest.mf cli.jar -C $(OUTDIR) .
