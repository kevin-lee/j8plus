import ProjectInfo._

val testosteroneVersion = "0.2.0"
val junitJupiterVersion = "5.6.2"

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / version      := ProjectVersion
ThisBuild / organization := "io.kevinlee"

ThisBuild / developers   := projectDevelopers
ThisBuild / homepage     := projectHomePage
ThisBuild / scmInfo      := projectScmInfo

lazy val j8plus = (project in file("."))
  .enablePlugins(DevOopsJavaPlugin)
  .enablePlugins(DevOopsGitReleasePlugin)
  .enablePlugins(JacocoCoverallsPlugin)
  .settings(
    name := "j8plus"
  , javacOptions := Seq(
      "-source", javaVersion.value
    , "-encoding", "UTF-8"
    )
  , javacOptions in (Compile, compile) ++= Seq(
      "-target", javaVersion.value
    , "-Xlint:unchecked"
    , "-g"
    , "-deprecation"
    )
  , javacOptions in (Compile, test) := (javacOptions in (Compile, compile)).value
  , resolvers ++= List(
      Resolver.jcenterRepo
    , "kevin-public-releases" at "https://repo.kevinlee.io/repository/kevin-public-releases"
    , "kevin-public-snapshots" at "https://repo.kevinlee.io/repository/kevin-public-snapshots"
    )
  , libraryDependencies ++= List(
      "org.junit.jupiter" % "junit-jupiter" % junitJupiterVersion % Test
    , "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
    , "io.kevinlee" % "test0ster1" % testosteroneVersion % Test
    , "org.assertj" % "assertj-core" % "3.17.2" % Test
    , "org.mockito" % "mockito-core" % "3.5.10" % Test
    , "org.elixirian" % "kommonlee-test" % "0.0.18-SNAPSHOT" % Test
    )
  , testOptions += Tests.Argument(TestFrameworks.JUnit, "-a")

  , bintrayPackageLabels := Seq("maven", "java", "fp", "functional programming")
  , bintrayVcsUrl := Some("git@github.com:Kevin-Lee/j8plus.git")
  , bintrayRepository := "maven"

  , publishMavenStyle := true
  , publishArtifact in Test := false
  , pomIncludeRepository := { _ => false }
  , licenses += ("Apache-2.0", url("https://opensource.org/licenses/apache2.0"))
  , jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report"
    , None
    , JacocoThresholds()
    , Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)
    , "utf-8"
    )
  , jacocoCoverallsServiceName := "github-actions"
  , jacocoCoverallsBranch := sys.env.get("CI_BRANCH")
  , jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME")
  , jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN")
  /* GitHub Release { */
  , devOopsPackagedArtifacts := List(s"target/${name.value}*.jar")
  /* } GitHub Release */
  )