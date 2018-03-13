package io.jgille.gpe.k8s

import groovy.text.GStringTemplateEngine
import org.gradle.api.Project

class KubernetesResourceConfig {

    private final Project project
    private final String configDir
    private final List<String> templateFiles
    private final String variablesFile

    KubernetesResourceConfig(Project project, String configDir, List<String> templateFiles, String variablesFile) {
        this.configDir = configDir
        this.project = project
        this.templateFiles = templateFiles
        this.variablesFile = variablesFile
    }

    File createMergedFile() {
        def lineSeparator = System.getProperty("line.separator")
        def tempDir = File.createTempDir()
        def yaml = new File(tempDir, "k8s.yml")
        templateFiles.collect { String f ->
            injectVars(f)
        }.each { String content ->
            yaml << "---" << lineSeparator << content << lineSeparator
        }

        return yaml
    }

    private String injectVars(String f) {
        def fileName = configDir != null ? "$configDir/$f" : f
        def template = project.file(fileName).text
        def variables = loadVariables()
        variables.serviceVersion = project.version
        new GStringTemplateEngine().createTemplate(template).make(variables)
    }

    private Map loadVariables() {
        def properties = new Properties()
        if (variablesFile) {
            def fileName = configDir != null ? "$configDir/$variablesFile" : variablesFile
            project.file(fileName).withInputStream {
                properties.load(it)
            }
        }

        def variables = [:]

        properties.forEach { String name, String value ->
            def fileMatch = (value =~ /:file\((\S+)\)/)
            if (fileMatch) {
                def file = fileMatch.group(1)
                def fileName = configDir != null ? "$configDir/$file" : file
                def projectFile = project.file(fileName)
                def base64Encoded = Base64.encoder.encodeToString(projectFile.text.getBytes("UTF-8"))
                variables[name] = base64Encoded
            } else {
                variables[name] = value
            }
        }

        variables
    }
}
