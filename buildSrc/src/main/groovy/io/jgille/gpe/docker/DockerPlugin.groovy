package io.jgille.gpe.docker

import org.gradle.api.Plugin
import org.gradle.api.Project

class DockerPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        if (project.hasProperty("dryRun")) {
            System.setProperty("dryRun", "true")
        }

        def dockerExtension = project.extensions.create('docker', DockerPluginExtension, project)

        project.tasks.create('dockerBuild', DockerBuild) {
            setGroup 'Docker'
            setDescription 'Build a Docker image'

            doFirst {
                tag = "$dockerExtension.imageName:$project.version"
                dockerFile = dockerExtension.dockerFile
                context = dockerExtension.context
            }
        }

        project.tasks.create('dockerPush', DockerPush) {
            setGroup 'Docker'
            setDescription 'Push a Docker image'

            doFirst {
                tag = "$dockerExtension.imageName:$project.version"
                repository = dockerExtension.repository
                pushCommand = dockerExtension.pushCommand
            }
        }

    }
}
