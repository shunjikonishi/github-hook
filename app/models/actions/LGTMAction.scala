package models.actions

import models.GitHubAction
import models.GitHubMessage

class LGTMAction extends GitHubAction {
  def isMatch(msg: GitHubMessage): Boolean = {
    msg.name == "issue_comment"
  }
  def process(msg: GitHubMessage): Unit = {

  }
}