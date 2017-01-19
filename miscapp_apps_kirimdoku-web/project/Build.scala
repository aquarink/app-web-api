import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "kirimdoku-web"
    val appVersion      = "2.2"
	
	val appDependencies = Seq(
		javaCore,
		javaJdbc,
		javaEbean,
        "com.jolbox" % "bonecp" % "0.8.0-rc3",
	      //"be.objectify" %% "deadbolt-java" % "2.0-SNAPSHOT",
		  "postgresql" % "postgresql" % "9.1-901.jdbc4",
	      "org.apache.commons" % "commons-email" % "1.2",
	      "commons-lang" % "commons-lang" % "2.6",
	      //"commons-collections" % "commons-collections" % "3.2.1",
	      //"commons-configuration" % "commons-configuration" % "1.6",
	      "commons-io" % "commons-io" % "2.4",
	     //"pdf" % "pdf_2.10" % "0.4.1",
		  "org.apache.poi" % "poi" % "3.8",
	      "org.apache.poi" % "poi-ooxml" % "3.8",
	      "org.quartz-scheduler" % "quartz" % "2.2.1"
	)
	
	val main = play.Project(appName, appVersion, appDependencies).settings(
		//deadbolt-2
        resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns),
        resolvers += Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns),

		// pdf
		resolvers += Resolver.url("My GitHub Play Repository", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)
      
		// Add your own project settings here      
		//  routesImport += "controller._",
		//templatesImport += "play.mvc.Http.Context.Implicit._"
		//templatesImport += "be.objectify.deadbolt.java.views.html._",
		//templatesImport += "be.objectify.deadbolt.core.utils.TemplateUtils._"
	)

}
