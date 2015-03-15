package models

trait GitHubAction {
  def isMatch(msg: GitHubEvent): Boolean
  def process(msg: GitHubEvent): Unit
}