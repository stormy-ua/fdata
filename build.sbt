import sbt._
import Keys._

val flinkVersion = "1.13.2"
val slf4jVersion = "1.7.32"
val beamVersion = "2.32.0"
val scioVersion = "0.11.0"
val avroVersion = "1.8.2"

val commonSettings = Def.settings(
  scalacOptions ++= Seq(
    "-language:existentials",
    "-language:experimental.macros",
    "-language:higherKinds",
    "-language:implicitConversions",
  ),
  resolvers += "confluent" at "https://packages.confluent.io/maven/"
)

lazy val root = (project in file(".")).
   settings(
     inThisBuild(List(
       organization := "com.fdata",
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
       "org.slf4j" % "slf4j-simple" % "1.7.32",
       "org.apache.avro" % "avro" % avroVersion
     )
   ).dependsOn(`fdata-core`, `fdata-flink`, `fdata-scio`)

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

lazy val `fdata-scio` = project
  .in(file("fdata-scio"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.spotify" %% "scio-core" % scioVersion,
      "com.spotify" %% "scio-test" % scioVersion % "test",
      "org.apache.beam" % "beam-runners-direct-java" % beamVersion,
      "org.apache.beam" % "beam-runners-google-cloud-dataflow-java" % beamVersion,
      "org.slf4j" % "slf4j-simple" % slf4jVersion
    )
  ).dependsOn(`fdata-core`)