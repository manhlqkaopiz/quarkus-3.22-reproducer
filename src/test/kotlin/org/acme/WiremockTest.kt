package org.acme

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.TestProfile
import io.restassured.RestAssured.given
import org.acme.profiles.TestProfiles
import org.acme.resourses.ExternalResource
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test



@QuarkusTest
@TestProfile(TestProfiles.WiremockProfile::class)
class WiremockTest {
    @ExternalResource
    lateinit var wiremock: WireMockServer

    @Test
    fun `hello wiremock`() {
        wiremock.addStubMapping(
            WireMock.get(WireMock.urlMatching("/wiremock"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBody("Hello from Wiremock")
                )
                .build()
        )

        given()
            .`when`().get("/wiremock")
            .then()
            .statusCode(200)
            .body(`is`("Hello from Wiremock"))
    }
}