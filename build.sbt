ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.2"

run / connectInput := true

run / fork := true

run / javaOptions += "-Dstdout.encoding=UTF-8"

lazy val root = (project in file("."))
  .settings(
    name := "cc3002-project-template",
    idePackagePrefix := Some("cl.uchile.dcc")
  )

libraryDependencies += "org.scalameta" %% "munit" % "1.2.4" % Test

libraryDependencies += "org.scalafx" %% "scalafx" % "26.0.0-R38"