package controllers.review

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import persistence.review.dao.ReviewCommitDAO
import model.site.review.SiteViewValueReview
import model.component.util.ViewValuePageLayout
import mvc.action.AuthenticationAction

class ReviewCommitController @Inject()(
  val daoReviewCommit: ReviewCommitDAO,
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  /**
    * 施設一覧ページ
    */
  def post = (Action andThen AuthenticationAction()).async { implicit request =>
    SiteViewValueReview.formReview.bindFromRequest.fold(
        errors => {
            val vv = SiteViewReview(
                layout  = ViewValuePageLayout(id = request.uri),
                form    = errors
            )
            Ok(views.html.site.review.post.Main(vv))
        }
  }
}