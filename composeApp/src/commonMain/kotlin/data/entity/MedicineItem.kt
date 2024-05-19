package data.entity

import kotlinx.datetime.LocalDate

data class MedicineItem(
    val id: Long? = null,
    val name: String,
    val description: String?,
    val expirationDate: LocalDate?,
    val type: MedicineTypeItem?
)
