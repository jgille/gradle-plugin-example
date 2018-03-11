package io.jgille.gpe.docker

import io.jgille.gpe.shell.Shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DockerBuild extends DefaultTask {

    String tag
    String dockerFile
    String context

    @TaskAction
    def buildImage() {
        Shell.sh("docker build -t $tag -f $dockerFile $context")
    }
}
