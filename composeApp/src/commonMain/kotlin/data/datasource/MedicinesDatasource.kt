package data.datasource

import data.entity.MedicineItem
import data.entity.MedicineTypeItem

interface MedicinesDatasource {

    fun getAllMedicines(): List<MedicineItem>

    fun insertMedicine(medicineItem: MedicineItem)

    fun deleteMedicine(id: Long)

    fun getAllMedicineTypes(): List<MedicineTypeItem>
}
