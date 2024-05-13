package domain.utils.converters

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import presentation.ui.screens.medicines_list.state.entity.ExpirationStatus

// todo move to domain or data layer
// todo refactor
class DateToExpirationStatusConverter {

    companion object {

        const val expiringRange = 10
    }

    fun getExpirationStatus(date: LocalDate): ExpirationStatus {
        val currentMoment: Instant = Clock.System.now()
        val currentDate = currentMoment.toLocalDateTime(TimeZone.UTC).date

        val daysBeforeExpiration = currentDate.daysUntil(date)
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
