package data.entity

import kotlinx.datetime.LocalDate

data class Medicine(
    val id: Long? = null,
    val name: String,
    val description: String?,
    val expirationDate: LocalDate?,
    val typeId: Long?
)
