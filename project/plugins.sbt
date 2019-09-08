logLevel := Level.Warn

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.5")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.0")

addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.2.7")

addSbtPlugin("kevinlee" % "sbt-devoops" % "0.3.1")

resolvers += Resolver.jcenterRepo

addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.2")
