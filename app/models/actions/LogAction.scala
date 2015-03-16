package models.actions

import models.GitHubAction
import github.GitHubAPI
import github.GitHubEvent

class LogAction extends GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean = true

  def process(api: GitHubAPI, msg: GitHubEvent): Unit = {
    println(msg)
  }
}