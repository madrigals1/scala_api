name := "scala_api"

version := "0.1"

scalaVersion := "2.13.5"

val AkkaActorStreamVersion = "2.6.13"
val AkkaHttpVersion = "10.2.4"
val SprayJsonVersion = "1.3.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % AkkaActorStreamVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaActorStreamVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
)

