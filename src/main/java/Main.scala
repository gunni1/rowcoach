import java.io.File

import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_imgcodecs._
import org.bytedeco.javacpp.opencv_imgproc._
import org.bytedeco.javacv._
import javax.swing._

/**
  * Created by Guntram on 04.06.2016.
  */
object Main {
  def main(args: Array[String]) {
    val filePath = "src/main/resources/einsatz.png"

    val inputImage = imread(filePath)
    val theshedImage = binarize(inputImage)
    display(theshedImage, "demo")
  }

  def binarize(source: Mat): Mat = {
    var threshImage = new Mat()

    threshold(source,threshImage, 100, 150, THRESH_BINARY_INV)

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
