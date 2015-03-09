package models

import models.actions.ReviewMeAction
import models.actions.LGTMAction

class ActionManager(actions: Seq[GitHubAction]) {

  def get(msg: GitHubMessage) = actions.filter(_.isMatch(msg))
}

object ActionManager(List(
  new ReviewMeAction(),
  new LGTMAction()
))

