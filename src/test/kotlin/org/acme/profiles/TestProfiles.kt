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
    }

}