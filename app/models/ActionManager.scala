package models

import models.actions.ReviewMeAction
import models.actions.LGTMAction
import models.actions.LogAction

class ActionManager(actions: Seq[GitHubAction]) {

  def get(msg: GitHubEvent) = actions.filter(_.isMatch(msg))
}

object ActionManager extends ActionManager(List(
  new ReviewMeAction(),
  new LGTMAction(),
  new LogAction()
))

