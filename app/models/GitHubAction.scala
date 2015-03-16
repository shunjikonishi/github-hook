package models

import github.GitHubAPI
import github.GitHubEvent

trait GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean
  def process(api: GitHubAPI, msg: GitHubEvent): Unit
}