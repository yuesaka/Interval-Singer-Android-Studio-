LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := FFT
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/Users/yuesaka/Code/Interval-Singer-AS/app/src/main/jni/Android.mk \
	/Users/yuesaka/Code/Interval-Singer-AS/app/src/main/jni/processRawData.c \

LOCAL_C_INCLUDES += /Users/yuesaka/Code/Interval-Singer-AS/app/src/debug/jni
LOCAL_C_INCLUDES += /Users/yuesaka/Code/Interval-Singer-AS/app/src/main/jni

include $(BUILD_SHARED_LIBRARY)
