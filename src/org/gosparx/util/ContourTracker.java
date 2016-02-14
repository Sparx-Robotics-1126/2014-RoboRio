package org.gosparx.util;

/*
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
import javax.swing.JFrame;
import javax.swing.JPanel;
*/

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
//import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class ContourTracker {

	private HashMap<Integer, VideoCapture> cameras = new HashMap<Integer, VideoCapture>();
	private Mat frame;
	private int activeCamera;
	private boolean debug = false;
	private double thresh;
	private double maxVal;
	private Scalar lowerB;
	private Scalar upperB;
	private double minArea;
	
	private Mat mat1;
	private Mat mat2;
	
	
	// DEBUG ONLY
	//private JFrame displayWindow = new JFrame();
	//private JPanelOpenCV panel = new JPanelOpenCV();
	
	// DEBUG ONLY
	//public void setDisplayDimensions(int width, int height)
	//{
		//displayWindow.setSize(width, height);
	//}
	
	public ContourTracker(double thresh,
	                      double maxVal,
	                      Scalar lowerB, 
	                      Scalar upperB, 
	                      double minArea)
	{
	    System.out.println("Hello, OpenCV");
	    // Load the native library.
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

	    this.thresh = thresh;
	    this.maxVal = maxVal;
	    this.lowerB = lowerB;
	    this.upperB = upperB;
	    this.minArea = minArea;
	    
		mat1 = new Mat();
		mat2 = new Mat();
	    
	    activeCamera = 0;
	    
	    
	    //frame = new Mat();
	    //findBoundingBoxes();
	    // DEBUG ONLY
	    //displayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //displayWindow.setTitle("ContourTracker");
	    //displayWindow.setVisible(true);
	    //displayWindow.add(panel);
	}
	
	public boolean addCamera(int cameraNumber)
	{
	    VideoCapture camera = new VideoCapture(cameraNumber);
	    camera.open(cameraNumber); //Useless
	    boolean success = camera.isOpened();
	    
	    if(success)
	    {
	    	cameras.put(cameraNumber, camera);
	    }
	    else
	    {
	    	System.err.println("Failed to init camera");
	    }

	    return success;
	}
	
	public void captureFrame(int cameraNumber)
	{	
		cameras.get(cameraNumber).read(frame);
		
		// DEBUG ONLY
		//panel.MatToBufferedImage(frame);
		//panel.repaint();
	}
	
	public void setActiveCamera(int activeCamera)
	{
		this.activeCamera = activeCamera;
	}
	
	public void findBoundingBoxes()
	{
	    // Consider the image for processing
	    //Mat bgrImg = Imgcodecs.imread("C:/Users/Jensen/Documents/Tst/rects.png");
		cameras.get(activeCamera).read(mat1);
	    Imgproc.cvtColor(mat1, mat2, Imgproc.COLOR_BGR2HSV);
	    if (debug)
	    {
	    	Imgcodecs.imwrite("C:/Users/Jensen/Documents/Tst/rectsHSV.png", mat2);
	    }
	    //Mat dilateImg = new Mat();
	    Imgproc.GaussianBlur(mat2, mat1, new Size(3, 3), 0);
        Imgproc.threshold(mat1, mat2, thresh, maxVal, Imgproc.THRESH_BINARY_INV);
        //Imgproc.dilate(threshImg, dilateImg, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2)));
	    if (debug)
	    {
	    	Imgcodecs.imwrite("C:/Users/Jensen/Documents/Tst/rectsBlurThresh.png",mat1);
	    	Imgcodecs.imwrite("C:/Users/Jensen/Documents/Tst/rectsThreshThresh.png",mat2);
	    	//Imgcodecs.imwrite("C:/Users/Jensen/Documents/Tst/rectsDilateThresh.png",dilateImg);
	    }
	    Core.inRange(mat2, lowerB, upperB, mat1);
	    //Imgproc.cvtColor(image, imageHSV, Imgproc.COLOR_BGR2GRAY);
	    //Imgproc.GaussianBlur(imageHSV, imageBlurr, new Size(5,5), 0);
	    //Imgproc.adaptiveThreshold(imageBlurr, imageA, 255,Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY,7, 5);
	    if (debug)
	    {
	    Imgcodecs.imwrite("C:/Users/Jensen/Documents/Tst/rectsHSVThresh.png", mat1);
	    }
	    List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
	    Imgproc.findContours(mat1, contours, mat2, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

	    //for (MatOfPoint m : contours)
	    //{
	    	//System.out.println(Imgproc.contourArea(m));
		    //System.out.print(m.width());
		    //System.out.print(", ");
		    //System.out.println(m.height());
	    //}
	   // Imgproc.drawContours(hsvThreshImg, contours, -1, new Scalar(255,255,255));
	   for(int i=0; i < contours.size();i++) {
		   //System.out.println(Imgproc.contourArea(contours.get(i)) + "s");
		   if (Imgproc.contourArea(contours.get(i)) > minArea) { 
			   Rect rect = Imgproc.boundingRect(contours.get(i));
			   //System.out.println(Imgproc.boundingRect(contours.get(i)).x);
			   //System.out.println(Imgproc.boundingRect(contours.get(i)).y);
			   //System.out.println(rect.height + "sd");
			   //if (rect.height > 5 && rect.width > 5) {
				   System.out.println("x,y,height,width: " + rect.x +","+rect.y+","+rect.height+","+rect.width);
				   Imgproc.rectangle(mat1, new Point(rect.x,rect.y), new Point(rect.x+rect.width,rect.y+rect.height),new Scalar(255,0,0));
			   //}
		   } 
	   }
	    if (debug)
	    {
	    	Imgcodecs.imwrite("C:/Users/Jensen/Documents/Tst/test2.png",mat1);
	    }
	}
	
	void releaseCameras()
	{
		for (VideoCapture camera : cameras.values())
		{
			camera.release();
		}
	}

	/*
	// DEBUG ONLY
    public static void main (String args[])
    {
    	ContourTracker tracker = new ContourTracker(80, 255, new Scalar(0,255,0), new Scalar(0,255,0), 8000);
    	tracker.addCamera(1);
    	tracker.setActiveCamera(1);
        //tracker.setDisplayDimensions(700, 500);
                 
        while(true)
        {
            tracker.findBoundingBoxes();   
        }
        //tracker.releaseCameras();
    }
    */
}

// DEBUG ONLY
/*class JPanelOpenCV extends JPanel
{

	private static final long serialVersionUID = 3579178907177546206L;
	
	BufferedImage image;

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(image, 0, 0, this);
    }

    public void MatToBufferedImage(Mat frame)
    {
        int type = 0;
        if (frame.channels() == 1)
        {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3)
        {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        this.image = image;
    }

}
*/