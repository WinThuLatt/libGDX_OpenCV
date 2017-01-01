package com.libgdxopencv;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.libgdxopencv.Utilities.AndroidUICallBack;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Toast;

public class AndroidLauncher extends AndroidApplication implements CvCameraViewListener2, AndroidUICallBack
{
    static
    {
	System.loadLibrary("jniModule");
    }
    private static final String TAG = "libGDXOpenCV";
    private CameraBridgeViewBase mOpenCvCameraView;
    Mat mRgba;
    Mat mGray;
    Mat buf;
    private final AndroidLauncher instance = this;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)
    {
	@Override
	public void onManagerConnected(int status)
	{
	    switch (status)
	    {
	    case LoaderCallbackInterface.SUCCESS:
	    {
		Log.i(TAG, "OpenCV loaded successfully");
		mOpenCvCameraView.enableView();
	    }
		break;
	    default:
	    {
		super.onManagerConnected(status);
	    }
		break;
	    }
	}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	Log.w(TAG, "OnCreate");

	super.onCreate(savedInstanceState);

	AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

	cfg.useGL30 = false;
	// we need to change the default pixel format - since it does not
	// include an alpha channel
	// we need the alpha channel so the camera preview will be seen behind
	// the GL scene
	cfg.r = 8;
	cfg.g = 8;
	cfg.b = 8;
	cfg.a = 8;

	View v = initializeForView(new libGDXOpenCV_Main(this), cfg);
	setContentView(R.layout.activity_main);
	if (graphics.getView() instanceof SurfaceView)
	{
	    SurfaceView glView = (SurfaceView) graphics.getView();
	    // force alpha channel - I'm not sure we need this as the GL surface
	    // is already using alpha channel
	    glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
	}

	getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.cameraview);

	mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

	mOpenCvCameraView.setCvCameraViewListener(this);

	// addContentView(mOpenCvCameraView, new
	// LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	addContentView(v, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void onPause()
    {
	Log.w(TAG, "onPause");

	super.onPause();
	if (mOpenCvCameraView != null)
	    mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
	System.out.println("onResume");

	super.onResume();
	if (!OpenCVLoader.initDebug())
	{
	    Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
	    OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
	} else
	{
	    Log.d(TAG, "OpenCV library found inside package. Using it!");
	    mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
	    
	}
	this.onCreate(null);
    }

    public void onDestroy()
    {
	Log.w(TAG, "onDestroy");

	super.onDestroy();
	if (mOpenCvCameraView != null)
	    mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height)
    {
	Log.w(TAG, "onCameraViewStarted");

	mRgba = new Mat(height, width, CvType.CV_8UC4);
	mGray = new Mat(height, width, CvType.CV_8UC1);
	buf = new Mat(height, width, CvType.CV_8UC4);
    }

    public void onCameraViewStopped()
    {
	Log.w(TAG, "onCameraViewStopped");

    }

    Size kSize = new Size(5, 5);

    public Mat onCameraFrame(CvCameraViewFrame inputFrame)
    {
	mRgba = inputFrame.rgba();
	Imgproc.cvtColor(mRgba, mGray, Imgproc.COLOR_BGR2GRAY);
	Imgproc.cvtColor(mRgba, buf, Imgproc.COLOR_BGR2GRAY);
	// Imgproc.Canny(buf, buf, 80, 100);
	// FindFeatures(mGray.getNativeObjAddr(), mRgba.getNativeObjAddr());
	return mRgba;
    }

    @Override
    public void ToastMessage(String s)
    {
	handler.post(new Runnable()
	{

	    @Override
	    public void run()
	    {
		// System.out.println("toatsing in launcher run");
		CharSequence sq = "HI";
		Toast.makeText(instance, sq, Toast.LENGTH_LONG).show();
	    }
	});
    }

    // public native void hi(long matAddrGray, long matAddrRgba);
    public native void FindFeatures(long matAddrGr, long matAddrRgba);

}
