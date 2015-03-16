package models.actions

import models.GitHubAction
import github.GitHubAPI
import github.GitHubEvent

class LGTMAction extends GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean = {
    msg.name == "issue_comment"
  }
  def process(api: GitHubAPI, msg: GitHubEvent): Unit = {

  }
}