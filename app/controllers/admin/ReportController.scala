package controllers.admin

import controllers.BaseController
import models.audit.DailyMetric
import models.queries.history.RequestLogQueries
import models.queries.report.RowCountQueries
import org.joda.time.LocalDate
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.audit.DailyMetricService
import services.database.Database
import services.history.GameHistoryService
import utils.{ ApplicationContext, DateUtils }

import scala.concurrent.Future

@javax.inject.Singleton
class ReportController @javax.inject.Inject() (override val ctx: ApplicationContext) extends BaseController {

  def email(d: LocalDate = DateUtils.today) = withAdminSession("email") { implicit request =>
    for {
      tables <- Database.query(RowCountQueries.ListTables)
      metrics <- DailyMetricService.recalculateMetrics(d)
      totals <- DailyMetricService.getTotals(d)
      wins <- GameHistoryService.getWins(d)
      counts <- Future.sequence(tables.map(table => Database.query(RowCountQueries.CountTable(table))))
    } yield Ok(views.html.admin.report.emailReport(d, request.identity.preferences.color, metrics._2._1, totals, wins, counts))
  }

  def trend() = withAdminSession("trend") { implicit request =>
    implicit val identity = request.identity
    DailyMetricService.getAllMetrics.map { metrics =>
      Ok(views.html.admin.report.trend(metrics, toChartData(metrics)))
    }
  }

  def requests() = withAdminSession("requests") { implicit request =>
    implicit val identity = request.identity
    for {
      userCounts <- Database.query(RequestLogQueries.GetUserCounts)
      userAgentCounts <- Database.query(RequestLogQueries.GetCounts("user_agent"))
      pathCounts <- Database.query(RequestLogQueries.GetCounts("path"))
      referrerCounts <- Database.query(RequestLogQueries.GetCounts("referrer"))
    } yield Ok(views.html.admin.report.requests(userCounts, userAgentCounts, pathCounts, referrerCounts, ctx.config.hostname))
  }

  private[this] def toChartData(metrics: Seq[(org.joda.time.LocalDate, Map[models.audit.DailyMetric.Metric, Long])]) = {
    def toChartDataValues(metric: DailyMetric.Metric) = metrics.map(x => x._1 -> x._2.getOrElse(metric, 0L)).reverse.map { row =>
      val ms = utils.DateUtils.toMillis(row._1.toLocalDateTime(org.joda.time.LocalTime.MIDNIGHT).plusDays(1))
      s"""{ "x": $ms, "y": ${row._2} }"""
    }.mkString(", ")

    val pre = "[\n"
    val post = "\n]\n"
    val content = DailyMetric.all.map { metric =>
      s"""
      |  {
      |    "values": [ ${toChartDataValues(metric)} ],
      |    "key": "${metric.title}"
      |  }
      """.stripMargin
    }.mkString(",\n")
    pre + content + post
  }
}
