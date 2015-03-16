package models.actions

import models.GitHubAction
import github.GitHubAPI
import github.GitHubEvent
import github.events.IssueCommentEvent

class LGTMAction extends GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean = {
    msg.name == "issue_comment"
  }
  def process(api: GitHubAPI, msg: GitHubEvent): Unit = {
    val (text, number, opener) = msg match {
      case x: IssueCommentEvent => (x.comment.body, x.issue.number, x.issue.user.name)
    }
    if (text.toUpperCase.indexOf("LGTM") != -1) {
      api.removeLabel(number, "Review me!")
      api.addLabels(number, "Ship it!")
      api.assign(number, opener)
    }
  }
}