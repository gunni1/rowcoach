package operations.stackable

import org.bytedeco.javacpp.opencv_core.{Size, Mat}
import org.bytedeco.javacpp.opencv_imgproc._

class ImageFilter {
  def filter(input: Mat): Mat = input
}

trait GrayScale extends ImageFilter {
  override def filter(input: Mat) = {
    val preFiltered = super.filter(input); val out = new Mat; cvtColor(preFiltered, out, CV_BGR2GRAY); out}
}

trait Blur extends ImageFilter {
  override def filter(input: Mat) = {
    val preFiltered = super.filter(input); val out = new Mat; blur(preFiltered, out, new Size(5,5)); out
  }
}
