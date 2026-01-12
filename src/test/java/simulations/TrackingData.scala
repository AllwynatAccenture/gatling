package simulations

import scala.io.Source
import scala.util.Random

object TrackingData {

  private def loadTrackingNumbers(resourcePath: String): Vector[String] = {
    val is = Option(getClass.getClassLoader.getResourceAsStream(resourcePath))
      .getOrElse(throw new IllegalArgumentException(s"Missing resource: $resourcePath"))
    val all = try Source.fromInputStream(is, "UTF-8").getLines().mkString("\n") finally is.close()
    all.split("""[,\r\n]+""").toVector
      .map(_.trim.stripPrefix("\"").stripSuffix("\""))
      .filter(_.matches("""\d{8,12}"""))
      .ensuring(_.nonEmpty, s"No valid tracking numbers in $resourcePath")
  }

  val trackingNumbers: Vector[String] = loadTrackingNumbers("tackingnumbers325.csv")

  def randomTrackingNumber(): String = trackingNumbers(Random.nextInt(trackingNumbers.size))
}

