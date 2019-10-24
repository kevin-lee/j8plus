logLevel := Level.Warn

addSbtPlugin("org.foundweekends" % "sbt-bintray" % "0.5.5")

resolvers += Resolver.url("Kevin's bintray", url("https://dl.bintray.com/kevinlee/sbt-plugins"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.2.1")

addSbtPlugin("io.kevinlee" % "sbt-devoops" % "1.0.2")

resolvers += Resolver.jcenterRepo


addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.3")
