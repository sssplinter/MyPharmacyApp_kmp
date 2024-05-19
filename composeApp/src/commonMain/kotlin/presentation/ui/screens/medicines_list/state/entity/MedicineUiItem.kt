package presentation.ui.screens.medicines_list.state.entity

import data.entity.MedicineItem

data class MedicineUiItem(
    val id: Long,
    val name: String,
    val description: String?,
    val typeString: String,
    val expirationDate: String,
    val expirationStatus: ExpirationStatus
)

fun MedicineItem.toMedicineUiItem(): MedicineUiItem {
    return MedicineUiItem(
        id = id!!,
        name = name,
        description = description,
        // TODO decide is it optional or not
        typeString = type?.type ?: "",
        expirationDate = expirationDate!!.toString(),
        expirationStatus = expirationDate.toExpirationStatus()
    )
}
