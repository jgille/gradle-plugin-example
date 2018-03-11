package io.jgille.gpe.docker

import org.gradle.api.Plugin
import org.gradle.api.Project

class DockerPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('docker', DockerPluginExtension)

        if (project.hasProperty("dryRun")) {
            System.setProperty("dryRun", "true")
        }

        project.tasks.create('dockerBuild', DockerBuild) {
            setGroup 'Docker'
            setDescription 'Build a Docker image'

            doFirst {
                tag = "$extension.imageName:$project.version"
                dockerFile = extension.dockerFile
                context = extension.context
            }
        }

        project.tasks.create('dockerPush', DockerPush) {
            setGroup 'Docker'
            setDescription 'Push a Docker image'

            doFirst {
                tag = "$extension.imageName:$project.version"
                repository = project.property("dockerRepository")
                pushCommand = extension.pushCommand
            }
        }

    }
}
