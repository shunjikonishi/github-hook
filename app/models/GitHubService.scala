package models

import akka.actor.Actor
import github.GitHubAPI
import github.GitHubEvent

class GitHubService(oauthToken: String, am: ActionManager) extends Actor {

  def receive = { 
    case x: GitHubEvent => doProcess(x)
  }

  private def doProcess(msg: GitHubEvent) = {
    val api = GitHubAPI(oauthToken, msg.repository.owner.name, msg.repository.name)
    am.get(msg).foreach(_.process(api, msg))
  }

}

