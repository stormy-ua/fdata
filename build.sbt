import sbt._
import Keys._

val flinkVersion = "1.13.2"
val slf4jVersion = "1.7.32"

val commonSettings = Def.settings(
  scalacOptions ++= Seq(
    "-language:existentials",
    "-language:experimental.macros",
    "-language:higherKinds",
    "-language:implicitConversions",
  )
)

lazy val root = (project in file(".")).
   settings(
     inThisBuild(List(
       organization := "ch.epfl.scala",
       scalaVersion := "2.12.15"
     )),
     name := "fdata"
   ).aggregate(`fdata-examples`, `fdata-core`, `fdata-flink`)

lazy val `fdata-examples` = project
   .in(file("fdata-examples"))
   .settings(commonSettings)
   .settings(
     libraryDependencies ++= Seq(
       "org.apache.flink" %% "flink-clients" % flinkVersion,
       "org.apache.flink" %% "flink-scala" % flinkVersion,
       "org.slf4j" % "slf4j-simple" % "1.7.32"
     )
   ).dependsOn(`fdata-core`, `fdata-flink`)

lazy val `fdata-core` = project
  .in(file("fdata-core"))
  .settings(commonSettings)

lazy val `fdata-flink` = project
  .in(file("fdata-flink"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.apache.flink" %% "flink-clients" % flinkVersion,
      "org.apache.flink" %% "flink-scala" % flinkVersion,
      "org.slf4j" % "slf4j-simple" % slf4jVersion
    )
  ).dependsOn(`fdata-core`)