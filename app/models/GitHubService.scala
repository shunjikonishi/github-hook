package models

import akka.actor.Actor

class GitHubService(oauthToken: String) extends Actor {

  def receive = { 
    case x: IssueMessage => doIssue(x)
    case x: IssueCommentMessage => doIssueComment(x)
    case x: GitHubMessage => doOther(x)
  }

  private def doOther(msg: GitHubMessage) = {
    println("Unhandled message")
    println(msg)
  }

  private def doIssue(msg: IssueMessage) = {
    println("!!!!!!!!!!!!! Issue !!!!!!!!!!!!!!!!")
    println(msg)
  }

  private def doIssueComment(msg: IssueCommentMessage) = {
    println("!!!!!!!!!!!!! IssueComment !!!!!!!!!!!!!!!!")
    println(msg.comment)
  }

}

