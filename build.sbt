import CommonUtils._

name := "j8plus"

organization := "cc.kevinlee"

val projectVersion = "0.0.11"

version := projectVersion

scalaVersion := "2.11.7"

javacOptions ++= List(
  "-deprecation",
  "-target", "1.8",
  "-source", "1.8",
  "-encoding", "utf8"
)

val testosteroneVersion = "0.0.6"
val junitVersion = "4.11"

resolvers ++= List(
  "kevin-public-releases" at "http://nexus.lckymn.com/content/repositories/kevin-public-releases",
  "kevin-public-snapshots" at "http://nexus.lckymn.com/content/repositories/kevin-public-snapshots",
  "kevin-bintray" at "http://dl.bintray.com/kevinlee/maven"
)

libraryDependencies ++= List(
  "junit" % "junit" % s"${junitVersion}",
  "cc.kevinlee" % "test0ster1" % s"${testosteroneVersion}" % "test",
  "org.assertj" % "assertj-core" % "3.0.0",
  "org.mockito" % "mockito-all" % "1.10.19",
  "org.elixirian" % "kommonlee-test" % "0.0.18-SNAPSHOT"

)

crossPaths := false

autoScalaLibrary := false

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
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

licenses += ("Apache-2.0", url("http://opensource.org/licenses/apache2.0"))


lazy val writeVersion = inputKey[Unit]("Write Version in File'")

writeVersion := versionWriter(() => Def.spaceDelimited("filename").parsed)(projectVersion)


import org.scoverage.coveralls.Imports.CoverallsKeys._

coverallsTokenFile := Option(s"""${sys.props("user.home")}/.coveralls-credentials""")


val repoLocation = "Kevin-Lee/j8plus"

/* GitHub Release { */
GithubRelease.repo := repoLocation

GithubRelease.tag := s"v${projectVersion}"

GithubRelease.releaseName := GithubRelease.tag.value

GithubRelease.commitish := "release"

GithubRelease.notesFile := GithubRelease.notesDir.value / s"${projectVersion}.md"

GithubRelease.releaseAssets := {

  val binNames = listFiles(target.value / "ci", "*.jar")

  println(s"fileNames: $binNames")

  binNames
}
/* } GitHub Release */
