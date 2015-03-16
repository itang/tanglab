import play.api.Logger
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object XRuntimeFilter extends Filter {
  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val s = System.nanoTime()
    val result = next(request).map { response =>
      val e = System.nanoTime()
      response.withHeaders("XRuntime" -> "%.4fms".format((e - s) / (1000.0 * 1000)))
    }
    result
  }
}

object Global extends WithFilters(XRuntimeFilter)
