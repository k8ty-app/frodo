import sbtnativeimage.NativeImagePlugin
import sbtnativeimage.NativeImagePlugin.autoImport.nativeImageOptions

lazy val root = (project in file(""))
  .settings(
    name := "frodo",
    organization := "app.k8ty",
    version := "0.0.2",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(

    ),
    nativeImageOptions ++= Seq(
      "--allow-incomplete-classpath",
      "--report-unsupported-elements-at-runtime",
      "--initialize-at-build-time",
      "--no-fallback",
      //  "-H:ConfigurationFileDirectories=conf/"
    ),
    nativeImageReady := {
      () => {
        println("Zug Zug")
      }
    }
  ).enablePlugins(SbtTwirl, NativeImagePlugin)


