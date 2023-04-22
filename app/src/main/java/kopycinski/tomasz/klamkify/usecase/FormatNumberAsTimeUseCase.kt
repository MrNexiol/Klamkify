package kopycinski.tomasz.klamkify.usecase

import java.util.concurrent.TimeUnit

class FormatNumberAsTimeUseCase {
    companion object {
        fun execute(seconds: Int): String {
            val minutes = TimeUnit.SECONDS.toMinutes(seconds.toLong())
            val remainingSeconds = seconds - TimeUnit.MINUTES.toSeconds(minutes)
            return String.format("%02d:%02d", minutes, remainingSeconds)
        }
    }
}