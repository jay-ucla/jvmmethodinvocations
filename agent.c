#include<jvmti.h>
#include<stdio.h>
#include<string.h>

static void check_jvmti_errors(jvmtiEnv *jvmti, jvmtiError errnum, const char *str) {

    if (errnum != JVMTI_ERROR_NONE) {

       char *errnum_str;

        errnum_str = NULL;

        (void) (*jvmti)->GetErrorName(jvmti, errnum, &errnum_str);

        fprintf(stdout,"ERROR: JVMTI: [%d] %s - %s", errnum, (errnum_str == NULL ? "Unknown": errnum_str), (str == NULL? "" : str));

    }

};

static void JNICALL callbackMethodEntry(jvmtiEnv *jvmti, JNIEnv *jni, jthread thread, jmethodID method) {

    char *name_ptr;

    char *signature_ptr;

    char *generic_ptr;

    char *class_ptr;
    
    jclass dec_class;


    jvmtiError error, e2, e3;

    error = (*jvmti)->GetMethodName(jvmti, method, &name_ptr, &signature_ptr, &generic_ptr);
    //check_jvmti_errors(jvmti, error, "Unable to get the method name");
    e2 = (*jvmti)->GetMethodDeclaringClass(jvmti, method, &dec_class); 
    //check_jvmti_errors(jvmti, e2, "Unable to get the class");
    e3 = (*jvmti)->GetClassSignature(jvmti, dec_class, &class_ptr, &generic_ptr);	

    //check_jvmti_errors(jvmti, e3, "Unable to get the class name");
    	
    if(class_ptr == NULL)
	return;
    
    char class_name[5];
    for(int i=0;i<4;i++){
	class_name[i]=class_ptr[i+1];
    }
    class_name[4]='\0';
    if(strcmp(class_name,"java")==0 || strcmp(class_name,"sun/")==0 || strcmp(class_name, "jdk/")==0)
	return;
    
    fprintf(stdout,"Entered method %s %s %s\n",class_ptr, name_ptr, signature_ptr);
    //strcat(jmvti_string, class_ptr);
    //free(msg);
};


JNIEXPORT jint JNICALL Agent_OnLoad(JavaVM *jvm, char *options, void *reserved){

	jvmtiEnv *jvmti;

    	jvmtiError error;
	
    	jint res;

    	jvmtiEventCallbacks callbacks;

    	jvmtiCapabilities capa;

    	jrawMonitorID rawMonitorID;

	res = (*jvm)->GetEnv(jvm, (void **) &jvmti, JVMTI_VERSION_1_0);	
	if (res != JNI_OK || jvmti == NULL) {

       		 printf("Unable to get access to JVMTI version 1.0");

    	}
	capa.can_generate_method_entry_events = 1;

    error = (*jvmti)->AddCapabilities(jvmti, &capa);

    check_jvmti_errors(jvmti, error, "Unable to add the required capabilities");

    // Setup event notification

    error = (*jvmti)->SetEventNotificationMode(jvmti, JVMTI_ENABLE, JVMTI_EVENT_METHOD_ENTRY, (jthread) NULL);

    check_jvmti_errors(jvmti, error, "Unable to set the event notification mode");

    // Setup the callbacks

    (void) memset(&callbacks, 0, sizeof(callbacks));

    callbacks.MethodEntry = &callbackMethodEntry;

    error = (*jvmti)->SetEventCallbacks(jvmti, &callbacks, (jint) sizeof(callbacks));

    check_jvmti_errors(jvmti, error, "Unable to set event callbacks");

    // Get the raw monitor

    //error = (*jvmti)->CreateRawMonitor(jvmti, "JVMTI agent data", &rawMonitorID);

    //check_jvmti_errors(jvmti, error, "Unable to create a Raw monitor");
	
	return JNI_OK;

};

