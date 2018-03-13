package io.jgille.gpe.k8s

import org.gradle.api.Plugin
import org.gradle.api.Project

class KubernetesPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        if (project.hasProperty("dryRun")) {
            System.setProperty("dryRun", "true")
        }

        def k8sExtension = project.extensions.create('kubernetes', KubernetesPluginExtension, project)

        project.tasks.create('k8sApply', KubernetesApply) {
            setGroup 'Kubernetes'
            setDescription 'Runs kubectl apply with a set of config files that may or may not contain variables'

            doFirst {
                context = k8sExtension.context
                configDir = k8sExtension.configDir
                templateFiles = k8sExtension.templateFiles
                variablesFile = k8sExtension.variablesFile
            }
        }

    }
}
