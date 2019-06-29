name := """biome404-java"""
organization := "fr.birmania"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.2"
libraryDependencies += "com.h2database" % "h2" % "1.4.192"
// utility
//libraryDependencies += "com.google.code.gson" % "gson" % "2.4"
// webjars
//libraryDependencies += "org.webjars" %% "webjars-play" % "2.7.3"
// webjars -> knockout
libraryDependencies += "org.webjars" % "jquery" % "2.1.4"
libraryDependencies += "org.webjars" % "knockout" % "3.4.0"
libraryDependencies += "org.webjars.bower" % "knockout-mapping" % "2.4.1"
libraryDependencies += "org.webjars.bower" % "knockout-validation" % "2.0.3" exclude("org.webjars.bower", "knockout")