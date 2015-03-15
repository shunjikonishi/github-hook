package models.actions

import models.GitHubAction
import models.GitHubMessage

class LogAction extends GitHubAction {
  def isMatch(msg: GitHubMessage): Boolean = true

  def process(msg: GitHubMessage): Unit = {
    println(msg)
  }
}