package models.actions

import models.GitHubAction
import models.GitHubEvent

class LogAction extends GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean = true

  def process(msg: GitHubEvent): Unit = {
    println(msg)
  }
}