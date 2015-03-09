package models

trait GitHubAction {
  def isMatch(msg: GitHubMessage): Boolean
  def process(msg: GitHubMessage): Unit
}