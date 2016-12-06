package models.actions

import models.GitHubAction
import github.GitHubAPI
import github.GitHubEvent
import github.events.IssueCommentEvent
import github.events.PullRequestReviewEvent
import github.enums.PullRequestReviewAction
import github.enums.PullRequestReviewState

class LGTMAction extends GitHubAction {
  private def isIssueComment(msg: GitHubEvent) = msg.name == "issue_comment"
  private def isApprovedPullRequestReview(msg: GitHubEvent) = msg match {
    case x: PullRequestReviewEvent => x.action == PullRequestReviewAction.submitted &&
     x.review.state == PullRequestReviewState.approved
    case _ => false
  }

  private def process(api: GitHubAPI, msg: IssueCommentEvent): Unit = {
    val (text, number, opener) = (msg.comment.body, msg.issue.number, msg.issue.user.name)
    if (text.toUpperCase.indexOf("LGTM") != -1) {
      shipIt(api, number, opener)
    }
  }
  private def process(api: GitHubAPI, msg: PullRequestReviewEvent): Unit = {
    shipIt(api, msg.pull_request.number, msg.pull_request.user.name)
  }
  private def shipIt(api: GitHubAPI, number: Long, opener: String): Unit = {
    api.removeLabel(number, "Review me!")
    api.addLabels(number, "Ship it!")
    api.assign(number, opener)
  }

  def isMatch(msg: GitHubEvent): Boolean = {
    isIssueComment(msg) || isApprovedPullRequestReview(msg)
  }
  def process(api: GitHubAPI, msg: GitHubEvent): Unit = {
    msg match {
      case x: IssueCommentEvent => process(api, x)
      case x: PullRequestReviewEvent => process(api, x)
    }
  }
}