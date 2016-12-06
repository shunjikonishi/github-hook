package github.enums

sealed abstract class PullRequestReviewAction(val name: String) {
  override def toString = name
}

object PullRequestReviewAction {
  case object submitted    extends PullRequestReviewAction("submitted")

  val values = Array(
    submitted
  )

  def fromString(str: String) = values.filter(_.name == str).head
}

