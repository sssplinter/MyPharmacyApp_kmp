package presentation.ui.screens.add_medicine.state

import data.entity.MedicineTypeItem

data class MedicineTypeUiItem(
    val id: Long,
    val type: String
) {

    override fun toString(): String {
        return type.replace("_", " ")
    }
}

fun MedicineTypeItem.toMedicineTypeUiItem(): MedicineTypeUiItem {
    return MedicineTypeUiItem(id = id, type = type)
}

fun MedicineTypeUiItem.toMedicineTypeItem(): MedicineTypeItem {
    return MedicineTypeItem(id = id, type = type)
}
