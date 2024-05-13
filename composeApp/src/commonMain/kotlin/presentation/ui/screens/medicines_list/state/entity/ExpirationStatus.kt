package presentation.ui.screens.medicines_list.state.entity

import domain.utils.converters.DateToExpirationStatusConverter
import kotlinx.datetime.LocalDate

enum class ExpirationStatus {
    Normal,
    Expiring,
    Expired
}

fun LocalDate.toExpirationStatus(): ExpirationStatus {
    return DateToExpirationStatusConverter().getExpirationStatus(this)
}