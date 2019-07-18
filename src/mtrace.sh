export LD_LIBRARY_PATH=/home/jay/openjdktest/test/jshrink-mtrace/java_crw_jshrink
java -Xbootclasspath/a:/home/jay/openjdktest/test/jshrink-mtrace/jmtrace/jmtrace.jar -agentpath:/home/jay/openjdktest/test/jshrink-mtrace/jmtrace/libjmtrace.so $1
