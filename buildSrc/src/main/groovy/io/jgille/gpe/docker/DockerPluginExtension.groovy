package io.jgille.gpe.docker

import org.gradle.api.Project

class DockerPluginExtension {

    String dockerFile = "Dockerfile"
    String context = "."
    String pushCommand = "docker push"

    String imageName
    String repository

    DockerPluginExtension(Project project) {
        imageName = project.name
    }
}
