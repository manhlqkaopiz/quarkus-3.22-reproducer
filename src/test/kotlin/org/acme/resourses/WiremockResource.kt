package org.acme.resourses

import com.github.tomakehurst.wiremock.WireMockServer
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager.TestInjector
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager.TestInjector.AnnotatedAndMatchesType

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ExternalResource

class WiremockResource : QuarkusTestResourceLifecycleManager {
    private val wireMockServer: WireMockServer by lazy { WireMockServer() }

    override fun start(): Map<String, String> {
        wireMockServer.start()

        return mapOf(
            WIREMOCK_SERVER_URL to wireMockServer.baseUrl(),
        )
    }

    override fun stop() {
        wireMockServer.stop()
    }

    override fun inject(testInjector: TestInjector) {
        testInjector.injectIntoFields(wireMockServer, AnnotatedAndMatchesType(org.acme.resourses.ExternalResource::class.java, WireMockServer::class.java))
    }

    companion object {
        const val WIREMOCK_SERVER_URL: String = "WIREMOCK_SERVER_URL"
        const val WIREMOCK_URL_QUARKUS_KEY: String = "\${$WIREMOCK_SERVER_URL}"
    }
}
