run_headless(){
	echo Starting $1 $2
	cd $1
	if [ $2 == "_" ]; then
		export AGENTPATH=""
	else	
		export AGENTPATH=-agentpath:/home/jay/openjdktest/test/jvmti-builds/$2
	fi
	echo $AGENTPATH
	mvn -fn test > test_output_$2.log
	TIME=$(cat test_output_$2.log | awk '/Total time/' | awk -F': ' '{print $2}')
	echo Finished $1 with ${TIME} 
	printf "$TIME," >> time
	cd ..
}



ls -d */ | while read line
do 
	run_headless ${line} "_"
	cat libs | while read lib
	do
		run_headless ${line} ${lib}
	done
	cd ${line}
	printf "\n" >> time
	cd ..
done


