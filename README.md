FRODO
=====

Turn your application.conf file into a kubernetes-secret.yml file!

This program parses through a HOCON conf file for `${?ENVIRONMENT}` settings,
will ask you what those values should be, then dumps the k8s yml to std-out.

If you pass it a (relative) file path, it will only look for that file. If you pass it no
arguments, it will look for `./src/main/resources/application.conf` and then `./src/main/resources/reference.conf`

Leaving a field blank will omit it from the output.

To install it, 
1. you can run `sbt assembly` to generate a fat jar, and make a shell script to wrap calling java.
2. you can try your luck with `sbt nativeImage` - works on my M1 MBA!