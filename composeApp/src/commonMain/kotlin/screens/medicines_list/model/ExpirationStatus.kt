package screens.medicines_list.model

import screens.utils.presentation.converters.DateToExpirationStatusConverter

enum class ExpirationStatus {
    Normal,
    Expiring,
    Expired
}

fun String.toExpirationStatus(): ExpirationStatus {
    return DateToExpirationStatusConverter().getExpirationStatus(this)
}