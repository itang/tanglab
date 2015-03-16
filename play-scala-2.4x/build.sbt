name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  //anorm,
  cache,
  ws
)

libraryDependencies += "com.typesafe.play" %% "anorm" % "2.4.0-M3"

libraryDependencies += specs2 % Test
