VERSION=1.0

LegoBIM.jar: src/*.groovy src/META-INF/MANIFEST.MF
	groovyc -cp "lib/*" -d build/ src/*.groovy
	cd build && jar cfvm ../LegoBIM.jar ../src/META-INF/MANIFEST.MF  *.class

LegoBIM.zip: LegoBIM.jar lib/* data/*
	zip -r LegoBIM.zip LegoBIM.jar lib data

LegoBIM-data.zip: data/*
	zip -r LegoBIM-data.zip data

LegoBIM-lib.zip: lib/*
	zip -r LegoBIM-lib.zip lib 

