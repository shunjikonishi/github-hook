# GitHub Hook
Receiving GitHub notifications, and do something.

It consists of

- Framework for GitHub webhook
- Some implementation of GitHubAction

You can make your own GitHubAction as you like.

## Setup server

```
git clone git@github.com:shunjikonishi/github-hook.git
cd github-hook
heroku create
git push heroku master
heroku config:set GITHUB_TOKEN=[Your GitHub token]
```

## How to get GitHub token

Go to https://github.com/settings/applications

And generate new token.

## Setup repository

Go to https://github.com/[USER]/[REPOSITORY]/settings/hooks

And add following webhook.

https://[YOUR_APP_NAME].herokuapp.com/hook

The user which specified by GitHub token must has permission to target repository.

## Actions
Currently, following actions are implemented

### LogAction
Logging all github notifications.

### ReviewMeAction
If the pullrequest comment has a mention and the word 'review', do followings.

- Add a label 'Review me!'.
- Set assignee to mentioned user.

### LGTMAction
if the pullrequest comment has the word 'LGTM', do followings.

- Remove a label 'Review me!'.
- Add a label 'Ship it!'.
- Set assignee to pullrequest opener.

### License
MIT