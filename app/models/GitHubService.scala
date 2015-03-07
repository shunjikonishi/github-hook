package models

import akka.actor.Actor

class GitHubService(oauthToken: String) extends Actor {
  println("token: " + oauthToken)

  def receive = { 
    case x: Issue => doIssue(x)
    case x: GitHubMessage => doOther(x)
  }

  private def doOther(msg: GitHubMessage) = {
    println("Unhandle message")
    println(msg)
  }

  private def doIssue(issue: Issue) = {
    println("!!!!!!!!!!!!! Issue !!!!!!!!!!!!!!!!")
    println(issue)
  }

}

