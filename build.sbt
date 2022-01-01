import ProjectInfo._

val testosteroneVersion = "0.2.0"
val junitJupiterVersion = "5.6.2"

val GitHubUsername = "Kevin-Lee"
val ProjectName    = "j8plus"

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / organization := "io.kevinlee"

ThisBuild / developers := projectDevelopers
ThisBuild / homepage := projectHomePage
ThisBuild / scmInfo := projectScmInfo

ThisBuild / resolvers += "sonatype-snapshots" at s"https://${props.SonatypeCredentialHost}/content/repositories/snapshots"

lazy val j8plus = (project in file("."))
  .enablePlugins(
    DevOopsJavaPlugin,
    DevOopsGitHubReleasePlugin,
    JacocoCoverallsPlugin,
    DocusaurPlugin,
  )
  .settings(
    name := ProjectName,
    javacOptions := Seq(
      "-source",
      javaVersion.value,
      "-encoding",
      "UTF-8"
    ),
    Compile / compile / javacOptions ++= Seq(
      "-target",
      javaVersion.value,
      "-Xlint:unchecked",
      "-g",
      "-deprecation"
    ),
    Compile / test / javacOptions := (Compile / compile / javacOptions).value,
    resolvers ++= List(
      Resolver.jcenterRepo,
      "kevin-public-releases" at "https://repo.kevinlee.io/repository/kevin-public-releases",
      "kevin-public-snapshots" at "https://repo.kevinlee.io/repository/kevin-public-snapshots"
    ),
    libraryDependencies ++= List(
      "org.junit.jupiter" % "junit-jupiter"     % junitJupiterVersion              % Test,
      "net.aichler"       % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
      "io.kevinlee"       % "test0ster1"        % testosteroneVersion              % Test,
      "org.assertj"       % "assertj-core"      % "3.17.2"                         % Test,
      "org.mockito"       % "mockito-core"      % "3.5.10"                         % Test,
    ),
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-a"),
    publishMavenStyle := true,
    Test / publishArtifact := false,
//    pomIncludeRepository := { _ => false },
    licenses := List("Apache-2.0" -> url("https://opensource.org/licenses/apache2.0")),
    jacocoReportSettings := JacocoReportSettings(
      "Jacoco Coverage Report",
      None,
      JacocoThresholds(),
      Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
      "utf-8"
    ),
    jacocoCoverallsServiceName := "github-actions",
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_REPO_TOKEN"),
    /* GitHub Release { */
    devOopsPackagedArtifacts := List(s"target/${name.value}*.jar"),
    /* } GitHub Release */

    /* Docs { */
    docusaurDir := (ThisBuild / baseDirectory).value / "website",
    docusaurBuildDir := docusaurDir.value / "build",
    /* } Docs */
  )
  .settings(mavenCentralPublishSettings)

lazy val props = new {
  val SonatypeCredentialHost = "s01.oss.sonatype.org"
  val SonatypeRepository     = s"https://$SonatypeCredentialHost/service/local"
}

lazy val mavenCentralPublishSettings: SettingsDefinition = List(
  /* Publish to Maven Central { */
  sonatypeCredentialHost := props.SonatypeCredentialHost,
  sonatypeRepository     := props.SonatypeRepository,
  /* } Publish to Maven Central */
)
