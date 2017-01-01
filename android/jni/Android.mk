LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
OPENCV_LIB_TYPE:=STATIC
OPENCV_INSTALL_MODULES:=on
include F:\newEclipseWorkspace\OpenCV-android-sdk\sdk\native\jni\OpenCV.mk
LOCAL_SRC_FILES := libgdxopencvnative.cpp
LOCAL_MODULE := jniModule
LOCAL_LDLIBS +=  -llog -ldl
include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)
LOCAL_MODULE := gdx
LOCAL_SRC_FILES := shared/libgdx/$(TARGET_ARCH_ABI)/libgdx.so
include $(PREBUILT_SHARED_LIBRARY)
