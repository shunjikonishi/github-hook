package github.enums

sealed abstract class PullRequestReviewState(val name: String) {
  override def toString = name
}

object PullRequestReviewState {
  case object approved    extends PullRequestReviewState("approved")

  val values = Array(
    approved
  )

  def fromString(str: String) = values.filter(_.name == str).head
}

