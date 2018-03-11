package io.jgille.gpe.shell

import java.util.concurrent.TimeUnit

class Shell {

    static void sh(String cmd) {
        sh(cmd, 15, TimeUnit.MINUTES)
    }

    static void sh(String cmd, long timeout, TimeUnit timeUnit) {
        println cmd
        def dryRun = System.getProperty("dryRun")
        if (dryRun != "true") {
            def proc = cmd.execute()
            waitForProcess(proc, timeout, timeUnit)
        }
    }

    private static void waitForProcess(Process process, long timeout, TimeUnit timeUnit) {
        process.consumeProcessOutput(System.out as OutputStream, System.err as OutputStream)
        process.waitFor(timeout, timeUnit)
        if (process.exitValue() != 0) {
            throw new RuntimeException("Error trying to run command")
        }
    }

}
