all:
	gcc -shared -fPIC -o libjvmti.so -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include -I/usr/lib/jvm/java-1.8.0-openjdk-amd64/include/linux agent.c
