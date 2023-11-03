#!/bin/sh

curl -s --user "api:$MAILGUN_API_KEY" "https://api.mailgun.net/v3/$MAILGUN_DOMAIN/messages" -F from='admin.gitlab@coral-io.com' -F to='a.aouadi@coral-io.fr'  -F subject='UWAS QA pipeline results' -F template='uwas-qa' -F attachment='@target/reports/report.html'

