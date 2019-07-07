export AGENTPATH="-Xbootclasspath/a:/home/jay/openjdk/jdk8-jdk/src/share/demo/jvmti/mtrace/mtrace.jar -agentpath:/home/jay/openjdk/jdk8-jdk/src/share/demo/jvmti/mtrace/libmtrace.so"

run_headless(){
	echo Starting $1
	cd $1
	#export AGENTPATH=""
	mvn -fn test > test_output_mtrace.log
	TIME=$(cat test_output_mtrace.log | awk '/Total time/' | awk -F': ' '{print $2}')
	echo Finished $1 with ${TIME} 
	#printf "$TIME," >> time
	cd ..
}



ls -d */ | while read line
do 
	run_headless ${line}	
done

