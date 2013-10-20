HowTo
=====

Package
-------
`sbt package`

Generate client
-------
generate Java client library (knedlo-v1-java.zip): `endpoints.sh get-client-lib --war=target/webapp cz.czechhackathon.knedlo.web.Endpoint`
generate API Discovery Document (knedlo-v1-rest.discovery): `endpoints.sh get-discovery-doc --war=target/webapp -f rest cz.czechhackathon.knedlo.web.Endpoint`
generate API Discovery Document (knedlo-v1-rpc.discovery): `endpoints.sh get-discovery-doc --war=target/webapp -f rpc cz.czechhackathon.knedlo.web.Endpoint`
generate iOS client: `ServiceGenerator knedlo-v1-rpc.discovery --outputDir target/knedlo-v1-ios`

Run
-------
`dev_appserver.sh --port=8888 target/webapp`
on public interface: `dev_appserver.sh --address="0.0.0.0" --port=8888 target/webapp`

Deploy
-------
`appcfg.sh update target/webapp`