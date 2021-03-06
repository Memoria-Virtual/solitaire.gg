package utils

import java.net.InetAddress

import com.github.mauricio.async.db.{Configuration => DbConfig}
import play.api.{Environment, Mode}
import utils.metrics.MetricsConfig

object Config {
  val projectId = "solitaire-gg"
  val projectName = "Solitaire.gg"
  val projectDescription = "Solitaire.gg is a collection of card games. It's quite good."
  val version = "0.1"
  val hostname = InetAddress.getLocalHost.getHostName
}

@javax.inject.Singleton
class Config @javax.inject.Inject() (cnf: play.api.Configuration, env: Environment) {
  val debug = env.mode == Mode.Dev

  val hostname = cnf.getString("host").getOrElse("localhost")
  val fileCacheDir = cnf.getString("cache.dir").getOrElse("./cache")

  val metrics: MetricsConfig = MetricsConfig(
    jmxEnabled = cnf.getBoolean("metrics.jmx.enabled").getOrElse(true),
    graphiteEnabled = cnf.getBoolean("metrics.graphite.enabled").getOrElse(false),
    graphiteServer = cnf.getString("metrics.graphite.server").getOrElse("127.0.0.1"),
    graphitePort = cnf.getInt("metrics.graphite.port").getOrElse(2003),
    servletEnabled = cnf.getBoolean("metrics.servlet.enabled").getOrElse(true),
    servletPort = cnf.getInt("metrics.servlet.port").getOrElse(9001)
  )

  val databaseConfiguration = new DbConfig(
    host = cnf.getString("database.host").getOrElse("localhost"),
    port = 5432,
    database = cnf.getString("database.schema"),
    username = cnf.getString("database.username").getOrElse(Config.projectId),
    password = cnf.getString("database.password")
  )

  val sparkEnabled = cnf.getBoolean("spark.enabled").getOrElse(false)
  val sparkMaster = cnf.getString("spark.master").getOrElse("local[8]")
  val sparkPort = cnf.getInt("spark.uiport").getOrElse(4040)

  // Admin
  val adminEmail = cnf.getString("game.admin.email").getOrElse("admin@localhost")

  // Notifications
  val slackEnabled = cnf.getBoolean("slack.enabled").getOrElse(false)
  val slackUrl = cnf.getString("slack.url").getOrElse("no_url_provided")

  // Metrics
  val jmxEnabled = cnf.getBoolean("metrics.jmx.enabled").getOrElse(false)
  val graphiteEnabled = cnf.getBoolean("metrics.graphite.enabled").getOrElse(false)
  val graphiteServer = cnf.getString("metrics.graphite.server").getOrElse("localhost")
  val graphitePort = cnf.getInt("metrics.graphite.port").getOrElse(8080)
  val servletEnabled = cnf.getBoolean("metrics.servlet.enabled").getOrElse(false)
  val servletPort = cnf.getInt("metrics.servlet.port").getOrElse(9001)
}
