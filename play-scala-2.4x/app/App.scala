import play.api.Logger
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object XRuntimeFilter extends Filter {

  val XRuntimeRespKey = "X-Runtime"

  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val s = System.nanoTime()
    val result = next(request).map { response =>
      val e = System.nanoTime()
      response.withHeaders(XRuntimeRespKey -> formatCostTime(s, e))
    }
    result
  }

  private def formatCostTime(start: Long, end: Long) = {
    val costInMs = (end - start) / (1000.0 * 1000)
    "%.4fms".format(costInMs)
  }

}

object Global extends WithFilters(XRuntimeFilter)
