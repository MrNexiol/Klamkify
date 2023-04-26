package kopycinski.tomasz.klamkify.data

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun dateToString(date: LocalDate?): String? =
        date?.toString()

    @TypeConverter
    fun stringToDate(dateString: String?): LocalDate? =
        LocalDate.parse(dateString)
}