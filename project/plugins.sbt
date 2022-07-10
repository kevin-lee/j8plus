logLevel := Level.Warn

addSbtPlugin("com.github.sbt" % "sbt-ci-release"        % "1.5.10")
addSbtPlugin("com.github.sbt" % "sbt-jacoco"            % "3.3.0")
addSbtPlugin("net.aichler"    % "sbt-jupiter-interface" % "0.9.1")

val sbtDevOopsVersion = "2.22.0"
addSbtPlugin("io.kevinlee" % "sbt-devoops-java"      % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-sbt-extra" % sbtDevOopsVersion)
addSbtPlugin("io.kevinlee" % "sbt-devoops-github"    % sbtDevOopsVersion)

addSbtPlugin("io.kevinlee" % "sbt-docusaur" % "0.11.0")
