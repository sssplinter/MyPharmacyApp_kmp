package screens.medicines_list.model

import data.entity.Medicine
import data.entity.MedicineTypeEnum
import screens.utils.presentation.converters.fromUtcStringToLocalDate

data class MedicineItem(
    val id: Int,
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
        typeString = MedicineTypeEnum.values()[typeId!!].name,
        // TODO decide is it optional or not
        expirationDate = expirationDateUTC!!.fromUtcStringToLocalDate().toString(),
        expirationStatus = expirationDateUTC.toExpirationStatus()
    )
}
