package kopycinski.tomasz.domain.usecase

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import java.lang.RuntimeException

class FormatLongAsTimeStringUseCaseTest {
    private val formatter = FormatLongAsTimeStringUseCase()

    @Test
    fun timeEqualsZero() {
        val seconds = 0L
        val expected = "00:00"
        assertEquals(expected, formatter(seconds))
    }

    @Test
    fun timeLowerThanHour() {
        val seconds = 120L
        val expected = "02:00"
        assertEquals(expected, formatter(seconds))
    }

    @Test
    fun timeHigherThanHour() {
        val seconds = 3660L
        val expected = "1:01:00"
        assertEquals(expected, formatter(seconds))
    }

    @Test
    fun timeHigherThanDay() {
        val seconds = 90000L
        val expected = "25:00:00"
        assertEquals(expected, formatter(seconds))
    }

    @Test
    fun throwsErrorWhenSecondsNegative() {
        val seconds = -1L
        try {
            formatter(seconds)
            fail("Formatter should throw error")
        } catch (exception: RuntimeException) {
            assertEquals("Seconds have to be non-negative", exception.message)
        }
    }
}