package io.jgille.gpe.k8s

import io.jgille.gpe.shell.Shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class KubernetesApply extends DefaultTask {

    String context
    String configDir
    List<String> templateFiles
    String variablesFile

    @TaskAction
    def deploy() {
        def resourceConfig = new KubernetesResourceConfig(project, configDir, templateFiles, variablesFile)
        def mergedFile = resourceConfig.createMergedFile()
        Shell.sh("kubectl apply --context $context -f $mergedFile.absolutePath")
    }

}
