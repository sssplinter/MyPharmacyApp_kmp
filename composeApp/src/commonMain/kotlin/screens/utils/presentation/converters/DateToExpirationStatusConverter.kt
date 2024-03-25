package screens.utils.presentation.converters

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import screens.medicines_list.model.ExpirationStatus

class DateToExpirationStatusConverter {

    companion object {

        const val expiringRange = 10
    }

    fun getExpirationStatus(dateUts: String): ExpirationStatus {
        val currentMoment: Instant = Clock.System.now()
        val currentDate = currentMoment.toLocalDateTime(TimeZone.UTC).date
        val expirationDate = LocalDateTime.parse(dateUts).date

        val daysBeforeExpiration = currentDate.daysUntil(expirationDate)
        return when {
            daysBeforeExpiration >= expiringRange -> ExpirationStatus.Normal
            daysBeforeExpiration in 1..<expiringRange -> ExpirationStatus.Expiring
            else -> ExpirationStatus.Expired
        }
    }
}

fun String.fromUtcStringToLocalDate(): LocalDate{
    return LocalDateTime.parse(this).date
}
