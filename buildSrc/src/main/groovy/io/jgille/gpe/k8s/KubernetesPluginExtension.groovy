package io.jgille.gpe.k8s

import org.gradle.api.Project

class KubernetesPluginExtension {

    String context
    String configDir = ""
    List<String> templateFiles
    String variablesFile

    KubernetesPluginExtension(Project project) {
        context = project.properties["context"]
        configDir = project.properties["configDir"]
    }
}
