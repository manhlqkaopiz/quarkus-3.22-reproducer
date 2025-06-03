package org.acme.profiles

import io.quarkus.test.junit.QuarkusTestProfile
import io.quarkus.test.junit.QuarkusTestProfile.TestResourceEntry
import org.acme.resourses.GeneralResource
import org.acme.resourses.WiremockResource

object TestProfiles {
    class GeneralProfile: QuarkusTestProfile {
        override fun testResources(): List<TestResourceEntry> = listOf (
            TestResourceEntry(GeneralResource::class.java, emptyMap(), true)
        )
    }

    class WiremockProfile: QuarkusTestProfile {
        override fun testResources(): List<TestResourceEntry> = listOf (
            TestResourceEntry(WiremockResource::class.java, emptyMap(), true)
        )

        override fun getConfigOverrides(): Map<String, String> {
            val currentMap = mutableMapOf(
                "quarkus.lambda.mock-event-server.test-port" to "8082"
            )

            val configOverrides = super.getConfigOverrides()
            return (configOverrides + currentMap).toMutableMap()
        }
    }

}