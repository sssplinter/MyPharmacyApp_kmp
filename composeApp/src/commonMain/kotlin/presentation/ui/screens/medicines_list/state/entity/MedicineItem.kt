package presentation.ui.screens.medicines_list.state.entity

import data.entity.Medicine
import data.entity.MedicineTypeEnum

data class MedicineItem(
    val id: Long,
    val name: String,
    val description: String?,
    val typeString: String,
    val expirationDate: String,
    val expirationStatus: ExpirationStatus
)

fun Medicine.toMedicineItem(): MedicineItem {
    return MedicineItem(
        id = id!!,
        name = name,
        description = description,
        typeString = MedicineTypeEnum.entries[typeId?.toInt()!!].name,
        // TODO decide is it optional or not
        expirationDate = expirationDate!!.toString(),
        expirationStatus = expirationDate.toExpirationStatus()
    )
}
