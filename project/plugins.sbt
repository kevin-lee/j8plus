logLevel := Level.Warn

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.5")

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.2.0")

addSbtPlugin("kevinlee" % "sbt-devoops" % "0.3.1")

resolvers += Resolver.jcenterRepo

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.3")
