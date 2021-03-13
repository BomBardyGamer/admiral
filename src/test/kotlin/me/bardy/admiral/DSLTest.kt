package me.bardy.admiral

import com.mojang.brigadier.CommandDispatcher
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DSLTest {

    private val dispatcher = CommandDispatcher<TestPlayer>()

    @BeforeTest
    fun `register time command`() {
        dispatcher.register(
            literal("time") {
                literal("set") {
                    executes { 2 }
                    literal("day") {
                        executes { 5 }
                    }
                    literal("noon") {
                        executes { 6 }
                    }
                    literal("night") {
                        executes { 7 }
                    }
                    literal("midnight") {
                        executes { 8 }
                    }
                    required("time", integer) {
                        executes { 9 }
                    }
                }
                literal("add") {
                    executes { 3 }
                    required("time", integer) {
                        executes { 10 }
                    }
                }
                literal("query") {
                    executes { 4 }
                    literal("daytime") {
                        executes { 11 }
                    }
                    literal("gametime") {
                        executes { 12 }
                    }
                    literal("day") {
                        executes { 13 }
                    }
                }
                executes { 1 }
            }
        )
    }

    @Test
    fun `test time command`() {
        val time = dispatcher.execute("time", TestPlayer)

        val timeSet = dispatcher.execute("time set", TestPlayer)
        val timeAdd = dispatcher.execute("time add", TestPlayer)
        val timeQuery = dispatcher.execute("time query", TestPlayer)

        val timeSetDay = dispatcher.execute("time set day", TestPlayer)
        val timeSetNoon = dispatcher.execute("time set noon", TestPlayer)
        val timeSetNight = dispatcher.execute("time set night", TestPlayer)
        val timeSetMidnight = dispatcher.execute("time set midnight", TestPlayer)
        val timeSetTime = dispatcher.execute("time set 1", TestPlayer)

        val timeAddTime = dispatcher.execute("time add 1", TestPlayer)

        val timeQueryDaytime = dispatcher.execute("time query daytime", TestPlayer)
        val timeQueryGametime = dispatcher.execute("time query gametime", TestPlayer)
        val timeQueryDay = dispatcher.execute("time query day", TestPlayer)

        assertEquals(1, time)

        assertEquals(2, timeSet)
        assertEquals(3, timeAdd)
        assertEquals(4, timeQuery)

        assertEquals(5, timeSetDay)
        assertEquals(6, timeSetNoon)
        assertEquals(7, timeSetNight)
        assertEquals(8, timeSetMidnight)
        assertEquals(9, timeSetTime)

        assertEquals(10, timeAddTime)

        assertEquals(11, timeQueryDaytime)
        assertEquals(12, timeQueryGametime)
        assertEquals(13, timeQueryDay)
    }
}

private object TestPlayer