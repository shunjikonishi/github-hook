package models.actions

import models.GitHubAction
import models.GitHubEvent

class LGTMAction extends GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean = {
    msg.name == "issue_comment"
  }
  def process(msg: GitHubEvent): Unit = {

  }
}