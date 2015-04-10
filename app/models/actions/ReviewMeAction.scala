package models.actions

import models.GitHubAction
import github.GitHubAPI
import github.GitHubEvent
import github.events.PullRequestEvent
import github.events.IssueCommentEvent
import github.enums.PullRequestAction


class ReviewMeAction extends GitHubAction {
  private def isPullRequestOpen(msg: GitHubEvent) = msg match {
    case x: PullRequestEvent => x.action == PullRequestAction.opened
    case _ => false
  }
  private def isIssueComment(msg: GitHubEvent) = msg.name == "issue_comment"

  def isMatch(msg: GitHubEvent): Boolean = {
    isPullRequestOpen(msg) || isIssueComment(msg)
  }
  def process(api: GitHubAPI, msg: GitHubEvent): Unit = {
    val (text, number) = msg match {
      case x: PullRequestEvent => (x.pull_request.body, x.pull_request.number)
      case x: IssueCommentEvent => (x.comment.body, x.issue.number)
    }
    text.split("\n").map(_.split("[\t\r\n\\., ]")).filter { words =>
      words.find(_.startsWith("@")).isDefined && 
      words.find(_.equalsIgnoreCase("review")).isDefined
    }.headOption.foreach { words =>
      val reviewee = words.find(_.startsWith("@")).map(_.substring(1))
      api.addLabels(number, "Review me!")
      api.removeLabel(number, "Fix me!")
      reviewee.foreach(api.assign(number, _))
    }
  }
}