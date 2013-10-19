name := "Knedlo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies ++= Seq (
  "com.google.appengine" % "appengine-api-1.0-sdk" % "1.8.6",
  "com.google.appengine" % "appengine-endpoints" % "1.8.6",
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "javax.inject" % "javax.inject" % "1", // todo needed?
  "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
)

seq(webSettings :_*)