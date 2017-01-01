I am on Windows, if you are using Linux/Mac, you might need to find your own way, should be simpler than this.

Why do I use Eclipse instead of Android Studio? Android Studio will have all the new features, however it is extremely laggy and may not respond for a very long time doing all it's gradle build and gradle download and building the project,etc etc.

**Eclipse Set up**

1.  Download NDK, extract it somewhere (let's refer to this location as "NDKROOT")
2. Install CDT, ADT
3. Download OpenCV (I was using version OpenCV4Android v3.0)
4. Extract the OpenCV4Android
5. It is recommended to use a new workspace, as it makes the project explorer much neater IMHO.

**Import Library Project**

1. Eclipse -> right click on the project explorer panel -> Import -> General -> Existing Projects into workspace
2. Select the OpenCV-android-sdk/sdk/ to import the OpenCVLibrary Project
3. If it's not already, Alt+Enter/rightclick->Properties while the OpenCVLibrary Project is selected... and on Android, select Android Project Build Target and make sure isLibrary is checked/selected/marked.

**Import Sample Projects**

1. Eclipse -> right click on the project explorer panel -> Import -> General -> Existing Projects into workspace
2. Select the OpenCV-android-sdk/samples/ to import the sample projects


**Setting up the NDK** (The tricky part, I spent so long trying to fix it)

1.  On Eclipse Preferences, Android -> NDK -> select the folder NDKROOT\build
2. On the OpenCV Project with the native portion, right click on that project and choose "Add Native Support"
3. You need the jni folder and I dont remember if libs folder is generated, if not create it,
4. Inside the jni folder, I needed a few files for this integration to work
    * Android.mk
    * Application.mk
    * SomeCPP.cpp
    * shared/libgdx/
      * TargetABI(Application Binary Interface)/libgdx.so

5. And viola, it should work, except that when it's an integration project that relies on the power of libGDX and OpenCV, and with the JNI part, when you run the project, the libgdx.so files are gonna be gone
6. That is the reason why inside the jni folder, the shared/libgdx/TargetAPI/libgdx.so files should exist. I copied it from another project.


**OpenCV manager installation**

1. There's a folder inside the OpenCV-android-sdk called apk
2. It has those OpenCV Manager apks and you dont have to install it from the playstore. Even if you had OpenCV Manager installed, it might not be the compatible version. Best solution is to use the apks that came in the same zip.
