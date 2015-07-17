#include <jni.h>

#include <string.h>
jstring  Java_com_bcgtgjyb_myweather_tool_Test5_getsha(JNIEnv* env,jobject thiz)

{
	return (*env)->NewStringUTF(env,"0C:1C:E3:94:6B:99:DB:FD:41:85:34:25:8D:E7:3C:32:DC:A1:D7:02");
    //return (env)->NewStringUTF("hello from jni by  ndkTest!");
}

