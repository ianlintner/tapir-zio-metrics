ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "tapir-zio-metrics"
  )
val zio2 =  "2.0.3"
val tapir = "1.2.0"
val scalaTest = "3.2.14"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio" % zio2,
  "com.softwaremill.sttp.tapir" %% "tapir-core" % tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-server" % tapir,
  "com.softwaremill.sttp.tapir" %% "tapir-tests" % tapir % Test,
  "com.softwaremill.sttp.tapir" %% "tapir-server-tests" % tapir % Test,
  "dev.zio" %% "zio-test" % zio2 % Test,
  "dev.zio" %% "zio-test-sbt" % zio2 % Test,

)
