import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "kirimdoku-api"
    val appVersion      = "2.2"

    val appDependencies = Seq(
		javaCore,
		javaJdbc,
		javaEbean,
        //"com.jolbox" % "bonecp" % "0.8.0-rc3",
		// Add your project dependencies here,
		"be.objectify" %% "deadbolt-java" % "2.1.2",
		"postgresql" % "postgresql" % "9.1-901.jdbc4"
    )

	val main = play.Project(appName, appVersion, appDependencies).settings(
		// Add your own project settings here      
        resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns),
  		resolvers += Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.io/snapshots/"))(Resolver.ivyStylePatterns)
    )

}
