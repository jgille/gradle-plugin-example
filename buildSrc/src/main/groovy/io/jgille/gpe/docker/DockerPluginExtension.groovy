package io.jgille.gpe.docker

class DockerPluginExtension {

    String dockerFile = "Dockerfile"
    String context = "."
    String imageName
    String pushCommand = "docker push"
}
