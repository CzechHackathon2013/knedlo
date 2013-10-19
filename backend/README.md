HowTo
=====

Package
-------
`sbt package`

Generate client
-------
`endpoints.sh get-client-lib --war=target/webapp cz.czechhackathon.knedlo.web.Endpoint`

Run
-------
`dev_appserver.sh --port=8888 target/webapp`

Deploy
-------
`appcfg.sh update target/webapp`