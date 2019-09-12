val ProjectVersion = "0.0.15"

val testosteroneVersion = "0.0.7"
val junitJupiterVersion = "5.5.0"


ThisBuild / scalaVersion     := "2.13.0"
ThisBuild / version          := ProjectVersion
ThisBuild / organization     := "kevinlee"

lazy val j8plus = (project in file("."))
  .settings(
    name := "j8plus"
  )
  .enablePlugins(DevOopsJavaPlugin)
  .enablePlugins(DevOopsGitReleasePlugin)
  .enablePlugins(JacocoCoverallsPlugin)
  .settings(
    javacOptions in (Compile, compile) ++= List(
      "-g",
      "-deprecation"
    )
  , resolvers ++= List(
      "kevin-public-releases" at "https://repo.kevinlee.io/repository/kevin-public-releases",
      "kevin-public-snapshots" at "https://repo.kevinlee.io/repository/kevin-public-snapshots",
      "kevin-bintray" at "http://dl.bintray.com/kevinlee/maven"
    )
  , libraryDependencies ++= List(
      "org.junit.jupiter" % "junit-jupiter" % junitJupiterVersion % Test,
      "net.aichler" % "jupiter-interface" % "0.8.2" % Test,
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
  , licenses += ("Apache-2.0", url("http://opensource.org/licenses/apache2.0"))
  , jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report"
    , None
    , JacocoThresholds()
    , Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)
    , "utf-8"
    )
  , jacocoCoverallsServiceName := "semaphore-ci"
  , jacocoCoverallsBuildNumber := sys.env.get("SEMAPHORE_BUILD_NUMBER")
  , jacocoCoverallsJobId :=
      sys.env.get("SEMAPHORE_CURRENT_JOB").map(jobId => s"$jobId-${jacocoCoverallsBuildNumber.value}")
        .getOrElse("")
  , jacocoCoverallsPullRequest := sys.env.get("PULL_REQUEST_NUMBER").filter(_.forall(_.isDigit))
  , jacocoCoverallsRepoToken :=
      sys.props.get("user.home")
        .map(home => file(s"$home/.coveralls-credentials"))
        .map(IO.readBytes).map(bs => new String(bs, "UTF-8"))
  /* GitHub Release { */
  , devOopsPackagedArtifacts := List(s"target/${name.value}*.jar")
  /* } GitHub Release */
  )