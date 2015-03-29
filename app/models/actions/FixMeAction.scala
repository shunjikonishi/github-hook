package models.actions

import models.GitHubAction
import github.GitHubAPI
import github.GitHubEvent
import github.events.PullRequestEvent
import github.events.IssueCommentEvent
import github.enums.PullRequestAction


class FixMeAction extends GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean = {
    msg.name == "issue_comment"
  }
  def process(api: GitHubAPI, msg: GitHubEvent): Unit = {
    val (text, number, opener) = msg match {
      case x: IssueCommentEvent => (
        x.comment.body, 
        x.issue.number,
        x.issue.user.name
      )
    }
    text.split("\n").map(_.split("[\t\\., ]")).filter { words =>
      val fix = words.indexOf("fix")
      val me = words.indexOf("me")
      fix >= 0 && me == fix + 1
    }.headOption.foreach { words =>
      val assignee = words.find(_.startsWith("@")).map(_.substring(1)).getOrElse(opener)
      api.addLabels(number, "Fix me!")
      api.assign(number, assignee)
    }
  }
}