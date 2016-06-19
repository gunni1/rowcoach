import java.io.File

import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgcodecs._
import org.bytedeco.javacpp.opencv_imgproc._
import org.bytedeco.javacv._
import javax.swing._

import org.bytedeco.javacpp._

/**
  * Created by Guntram on 04.06.2016.
  */
object Main {
  def main(args: Array[String]) {
    val filePath = "src/main/resources/einsatz.png"

    val inputImage = imread(filePath)
    val grayImage = grayscale(inputImage)
    //val threshImage = binarize(grayImage)
    val threshImage = adaptiveBinarize(grayImage)

    display(threshImage, "demo")
  }

  def binarize(source: Mat): Mat = {
    val threshImage = new Mat
    val otsuImage = new Mat
    val thresh = threshold(source,otsuImage, 128, 255, CV_THRESH_OTSU)
    println("otsu threshold: " + thresh)
    threshold(source, threshImage, thresh,255, CV_THRESH_BINARY)

    threshImage
  }

  def smoothImage(source: Mat): Mat = {
    val smoothImage = new Mat

    smoothImage
  }

  def adaptiveBinarize(source: Mat): Mat = {
    val threshImage = new Mat
    val blockSize = 101
    val c = -7
    adaptiveThreshold(source, threshImage,255, CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY, blockSize, c)
    threshImage
  }

  def grayscale(source: Mat): Mat = {
    val threshImage = new Mat
    cvtColor(source, threshImage, CV_BGR2GRAY)
    threshImage
  }

  def display(image: Mat, caption: String): Unit = {
    // Create image window named "My Image."
    val canvas = new CanvasFrame(caption, 1)

    // Request closing of the application when the image window is closed.
    canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    // Convert from OpenCV Mat to Java Buffered image for display
    val converter = new OpenCVFrameConverter.ToMat()
    // Show image on window
    canvas.showImage(converter.convert(image))
  }
}
