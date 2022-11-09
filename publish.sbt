ThisBuild / organizationName := "ianlintner"
ThisBuild / organizationHomepage := Some(url("https://github.com/ianlintner"))

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/ianlintner/tapir-zio-metrics"),
    "scm:git@github.username/project.git"
  )
)

ThisBuild / developers := List(
  Developer(
    id    = "ianlintner",
    name  = "Ian Lintner",
    email = "lintner.ian@gmail.com",
    url   = url("https://github.com/ianlintner")
  )
)

ThisBuild / description := "Zio Metrics Intercepter for Tapir"
ThisBuild / homepage := Some(url("https://github.com/ianlintner/tapir-zio-metrics"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

ThisBuild / publishMavenStyle := true

ThisBuild / versionScheme := Some("early-semver")
