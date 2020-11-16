logLevel := Level.Warn

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.5")

addSbtPlugin("io.kevinlee" % "sbt-devoops" % "1.0.3")

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.3.0")

resolvers += Resolver.jcenterRepo

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.3")

addSbtPlugin("io.kevinlee" % "sbt-docusaur" % "0.3.0")
