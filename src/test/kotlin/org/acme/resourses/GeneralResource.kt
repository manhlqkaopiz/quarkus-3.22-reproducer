package org.acme.resourses

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager

class GeneralResource: QuarkusTestResourceLifecycleManager {
   override fun start() = mapOf("quarkus.http.port" to "8081")
    override fun stop() = Unit
}