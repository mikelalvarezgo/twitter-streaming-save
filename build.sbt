name := "Spark-Twitter"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= {
  val sparkVersion = "1.2.1"
  val casbahV = "3.1.1"
  val sprayVersion = "1.2.2"
  Seq(
    "org.spire-math" %% "cats" % "0.3.0",
    "org.scalaz" %% "scalaz-core" % "7.2.0",
    "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.0",
    "com.typesafe.akka" %% "akka-actor" % "2.3.2",
    "com.etaty.rediscala" %% "rediscala" % "1.3.1",
    "io.spray" %% "spray-client" % "1.3.3",
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "org.apache.spark" %% "spark-streaming" % sparkVersion,
    "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion,
    "org.slf4j" % "slf4j-simple" % "1.7.5",
    "org.scalatest" %% "scalatest" % "2.2.6",
    "org.mongodb" %% "casbah-commons" % casbahV,
    "org.mongodb" %% "casbah-core" % casbahV,
    "org.mongodb" %% "casbah-query" % casbahV,
    "io.spray" % "spray-json_2.11" % "1.3.2"
  )
}
