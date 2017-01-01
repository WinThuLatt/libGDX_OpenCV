#include <jni.h>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/features2d/features2d.hpp>
#include <vector>

using namespace std;
using namespace cv;
//
//extern "C" {
//JNIEXPORT void JNICALL Java_com_libgdxopencv_AndroidLauncher_hi(JNIEnv*, jobject, jlong addrGray, jlong addrRgba);
//
//JNIEXPORT void JNICALL Java_com_libgdxopencv_AndroidLauncher_hi(JNIEnv*, jobject, jlong addrGray, jlong addrRgba)
//{
//    Mat& mGr  = *(Mat*)addrGray;
//    Mat& mRgb = *(Mat*)addrRgba;
//    vector<KeyPoint> v;
//
//    Ptr<FeatureDetector> detector = FastFeatureDetector::create(50);
//    detector->detect(mGr, v);
//    for( unsigned int i = 0; i < v.size(); i++ )
//    {
//        const KeyPoint& kp = v[i];
//        circle(mRgb, Point(kp.pt.x, kp.pt.y), 10, Scalar(255,0,0,255));
//    }
//}
//}

//JNIEXPORT void JNICALL Java_com_libgdxopencv_AndroidLauncher_FindFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba);
extern "C" {
JNIEXPORT void JNICALL Java_com_libgdxopencv_AndroidLauncher_FindFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba);

JNIEXPORT void JNICALL Java_com_libgdxopencv_AndroidLauncher_FindFeatures(JNIEnv*, jobject, jlong addrGray, jlong addrRgba)
{
	Mat& mGr = *(Mat*)addrGray;
	Mat& mRgb = *(Mat*)addrRgba;
	cv::Size size(3,3);
	cv::GaussianBlur(img,img,size,0);
	adaptiveThreshold(img, img,255,CV_ADAPTIVE_THRESH_MEAN_C, CV_THRESH_BINARY,75,10);
	cv::bitwise_not(img, img);
}
}
