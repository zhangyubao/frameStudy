//
// Created by Administrator on 2016/5/10.
//
#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_zbao_news_FlashActivity_getUrl(JNIEnv *env, jobject obj) {
    char* tmpstr = "return string succeeded";
    return env->NewStringUTF(tmpstr);
}