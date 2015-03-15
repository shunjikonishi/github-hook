package models

import akka.actor.Actor

class GitHubService(oauthToken: String, am: ActionManager) extends Actor {

  def receive = { 
    case x: GitHubEvent => doProcess(x)
  }

  private def doProcess(msg: GitHubEvent) = {
    am.get(msg).foreach(_.process(msg))
  }

}

