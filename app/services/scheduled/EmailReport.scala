package services.scheduled

import models.audit.DailyMetric
import models.database.queries.report.ReportQueries
import org.joda.time.{ LocalDate, LocalDateTime }
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import services.EmailService
import services.audit.DailyMetricService
import services.database.Database

import scala.concurrent.Future

object EmailReport {
  def sendReportIfNeeded(emailService: EmailService) = {
    val yesterdayAndBuffer = new LocalDateTime().minusDays(1).plusHours(3).toLocalDate
    DailyMetricService.getMetric(yesterdayAndBuffer, DailyMetric.ReportSent).flatMap { reportSent =>
      if (reportSent.contains(1L)) {
        Future.successful("report" -> None)
      } else {
        val yesterday = new LocalDate().minusDays(1)
        for {
          tables <- Database.query(ReportQueries.ListTables)
          yesterdayMetrics <- DailyMetricService.getMetrics(yesterday)
          totals <- DailyMetricService.getTotals(yesterday)
          counts <- Future.sequence(tables.map(table => Database.query(ReportQueries.CountTable(table))))
          report <- emailService.sendDailyReport(yesterday, "greyblue", yesterdayMetrics._2, totals, counts)
        } yield {
          "report" -> Some("Sent report")
        }
      }
    }
  }
}
