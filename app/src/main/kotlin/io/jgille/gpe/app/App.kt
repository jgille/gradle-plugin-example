package io.jgille.gpe.app

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.setup.Environment

class App: Application<Configuration>() {
    override fun run(configuration: Configuration, environment: Environment) {
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run(*args)
        }
    }
}