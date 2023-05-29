package kopycinski.tomasz.domain.usecase

class FormatLongAsTimeString {
    operator fun invoke(seconds: Long): String {
        require(seconds >= 0) { "Seconds have to be non-negative" }

        return if (seconds < SECONDS_IN_HOUR) {
            formatMinutesAndSeconds(seconds)
        } else {
            formatAsHoursMinutesSeconds(seconds)
        }
    }

    private fun formatMinutesAndSeconds(seconds: Long): String {
        val minutes = seconds / SECONDS_IN_MINUTE
        val remainingSeconds = seconds % SECONDS_IN_MINUTE

        return "%02d:%02d".format(minutes, remainingSeconds)
    }

    private fun formatAsHoursMinutesSeconds(seconds: Long): String {
        val hours = seconds / SECONDS_IN_HOUR
        val minutes = seconds % SECONDS_IN_HOUR / SECONDS_IN_MINUTE
        val remainingSeconds = seconds % SECONDS_IN_HOUR % SECONDS_IN_MINUTE

        return "%d:%02d:%02d".format(hours, minutes, remainingSeconds)
    }

    companion object {
        private const val SECONDS_IN_HOUR = 3600
        private const val SECONDS_IN_MINUTE = 60
    }
}