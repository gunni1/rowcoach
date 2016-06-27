
import operations._
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
    val filePath = "src/main/resources/ausheben.png"

    val inputImage = imread(filePath)
    val startTime = System.currentTimeMillis
    val oneFourth = inputImage.size.width/4

    val filters = List(CutSides(oneFourth), GrayScale,Blur(3),BinarizeAdaptiveMean(31,31))
    val processedImage = processFilters(inputImage, filters)

    val endTime = System.currentTimeMillis
    println("Berechnungszeit: " + (endTime-startTime) + " ms")
    display(processedImage, "demo")
  }

  def processFilters(image: Mat,filters: List[ImageFilter]): Mat = { filters match{
    case Nil => image
    case f :: fs => processFilters(f.applyFilter(image), fs)
  }}



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
