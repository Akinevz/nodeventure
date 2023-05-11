name := "renderer"
scalaVersion := "3.2.2"

lazy val build = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.2.0",
      "org.scalatest" %%% "scalatest" % "3.2.10" % Test
    ),
    Compile / fastOptJS / dist := {
      val file = (Compile / fastOptJS / crossTarget).value / "renderer-fastopt.js"
      val dest = baseDirectory.value / "assets" / "client-fastopt.js"
      IO.copyFile(file, dest)
      Seq(dest)
    },
    Compile / dist := Def.sequential(Compile / fastOptJS, Compile / fastOptJS / dist).value,
    Compile / mainClass := Some("raytracer.Entry")
  )

lazy val dist =
  taskKey[Unit]("Copy client-fastopt.js to project base directory")
