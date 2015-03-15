package models

import akka.actor.Actor

class GitHubService(oauthToken: String, am: ActionManager) extends Actor {

  def receive = { 
    case x: GitHubMessage => doProcess(x)
  }

  private def doProcess(msg: GitHubMessage) = {
    am.get(msg).foreach(_.process(msg))
  }

}

