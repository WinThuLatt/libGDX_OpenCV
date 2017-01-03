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

//	Mat HSV;
//	cvtColor(mRgb, HSV, CV_RGB2HSV);
//
//	Scalar   min(220/2, 0, 0);
//	Scalar   max(260/2, 255, 255);
//
//	Mat threshold_frame;
//	inRange( HSV, min, max, mRgb);

	cv::Ptr<cv::ORB> detector = cv::ORB::create();

	cvtColor(mRgb, mRgb, COLOR_RGBA2RGB);

	vector<KeyPoint> keypoints;

	detector->detect(mRgb, keypoints);

	drawKeypoints(mRgb,keypoints, mRgb);

}
}
