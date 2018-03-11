package io.jgille.gpe.docker

import io.jgille.gpe.shell.Shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DockerPush extends DefaultTask {

    String tag
    String repository
    String pushCommand

    @TaskAction
    def buildImage() {
        Shell.sh("docker tag $tag $repository/$tag")
        Shell.sh("$pushCommand $repository/$tag")
    }
}