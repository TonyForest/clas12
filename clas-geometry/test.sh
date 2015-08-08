#!/usr/bin/bash

CLASSPATH=`find lib -name '*.jar' | sed s'/\n/:/g'`
CLASSPATH=$CLASSPATH:test
CLASSPATH=$CLASSPATH:src/main/java
CLASSPATH=$CLASSPATH:/usr/share/java/junit.jar

echo "classpath: $CLASSPATH"

SOURCE_FILES=`find src -name '*.java'`
TEST_FILES=`find test -name '*.java'`

javac -classpath $CLASSPATH $SOURCE_FILES || exit -1
javac -classpath $CLASSPATH $TEST_FILES || exit -2

# if no main in target class:
# java -cp 'test:src/main/java:/usr/share/java/junit.jar' org.junit.runner.JUnitCore org.jlab.geom.detector.dc.DriftChamberTest

# if there is a main which just calls the unit test:
java -cp $CLASSPATH org.jlab.geom.detector.dc.DriftChamberTest || exit -3
