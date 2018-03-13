# Gradle Plugin Examples

This project contains some examples on how you can write custom Gradle plugins to simplify your CI/CD pipelines.

There is a module called app that contains a dead simple Dropwizard service. There is also a module named buildSrc,
which is the naming convention when you have Gradle plugins in the same project, that contains a couple of Gradle
tasks and plugins.

## Build a docker image
```
./gradlew dockerBuild
```

## Deploy in Kuberneters running locally (minikube)

```
./gradlew k8sApply -Pcontext=minikube -PconfigDir=k8s/minikube
```