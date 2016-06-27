package operations

import org.bytedeco.javacpp.opencv_core.{Size, Mat}
import org.bytedeco.javacpp.opencv_imgproc._

trait ImageFilter {
  def applyFilter(input: Mat): Mat = input
}

object GrayScale extends ImageFilter {
  override def applyFilter(input: Mat) = {
    val out = new Mat; cvtColor(input, out, CV_BGR2GRAY); out}
}

case class Blur(boxSize: Int) extends ImageFilter {
  override def applyFilter(input: Mat) = {
    val out = new Mat; blur(input, out, new Size(boxSize,boxSize)); out
  }
}

/**
  * @param blockSize
  * @param c - Konstante, wird vom Mittelwert abgezogen
  */
case class BinarizeAdaptiveGauss(blockSize: Int, c: Int) extends ImageFilter {
  override def applyFilter(input: Mat) = {
    val threshImage = new Mat
    adaptiveThreshold(input, threshImage,255, CV_ADAPTIVE_THRESH_GAUSSIAN_C, CV_THRESH_BINARY_INV, blockSize, c)
    threshImage
  }
}

case class CutSides(pixels: Int) extends ImageFilter {
  override def applyFilter(input: Mat) = {
    input.colRange(pixels, input.size.width-pixels)
  }
}

/**
  * @param blockSize
  * @param c - Konstante, wird vom Mittelwert abgezogen
  */
case class BinarizeAdaptiveMean(blockSize: Int, c: Int) extends ImageFilter {
  override def applyFilter(input: Mat) = {
    val threshImage = new Mat
    adaptiveThreshold(input, threshImage,255, CV_ADAPTIVE_THRESH_MEAN_C, CV_THRESH_BINARY_INV, blockSize, c)
    threshImage
  }
}

object BinarizeGlobal extends ImageFilter {
  override def applyFilter(input: Mat) = {
    val threshImage = new Mat
    val otsuImage = new Mat
    val thresh = threshold(input,otsuImage, 128, 255, CV_THRESH_OTSU)
    threshold(input, threshImage, thresh,255, CV_THRESH_BINARY)
    threshImage
  }
}


