val ProjectVersion = "0.0.15"

val testosteroneVersion = "0.0.7"
val junitJupiterVersion = "5.5.0"


ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := ProjectVersion
ThisBuild / organization     := "io.kevinlee"

lazy val j8plus = (project in file("."))
  .settings(
    name := "j8plus"
  )
  .enablePlugins(DevOopsJavaPlugin)
  .enablePlugins(DevOopsGitReleasePlugin)
  .enablePlugins(JacocoCoverallsPlugin)
  .settings(
    javacOptions := Seq(
      "-source", javaVersion.value
    , "-encoding", "UTF-8"
    )
  , javacOptions ++= Seq(
      "-g"
    , "-deprecation"
    )
  , javacOptions in Compile ++= Seq(
      "-target", javaVersion.value
    , "-Xlint:unchecked"
    )
  , resolvers ++= List(
      Resolver.jcenterRepo,
      "kevin-public-releases" at "https://repo.kevinlee.io/repository/kevin-public-releases",
      "kevin-public-snapshots" at "https://repo.kevinlee.io/repository/kevin-public-snapshots",
      "kevin-bintray" at "https://dl.bintray.com/kevinlee/maven"
    )
  , libraryDependencies ++= List(
      "org.junit.jupiter" % "junit-jupiter" % junitJupiterVersion % Test,
      "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
      "kevinlee" % "test0ster1" % s"${testosteroneVersion}" % Test,
      "org.assertj" % "assertj-core" % "3.12.2" % Test,
      "org.mockito" % "mockito-core" % "3.0.0" % Test,
      "org.elixirian" % "kommonlee-test" % "0.0.18-SNAPSHOT" % Test

    )
  , testOptions += Tests.Argument(TestFrameworks.JUnit, "-a")

  , bintrayPackageLabels := Seq("maven", "java", "fp", "functional programming")
  , bintrayVcsUrl := Some("git@github.com:Kevin-Lee/j8plus.git")
  , bintrayRepository := "maven"

  , publishMavenStyle := true
  , publishArtifact in Test := false
  , pomIncludeRepository := { _ => false }
  , pomExtra := (
      <url>https://github.com/Kevin-Lee/j8plus</url>
        <licenses>
          <license>
            <name>The Apache License</name>
            <url>https://github.com/Kevin-Lee/j8plus/blob/master/LICENSE</url>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:Kevin-Lee/j8plus.git</url>
          <connection>scm:git:git@github.com:Kevin-Lee/j8plus.git</connection>
        </scm>)
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