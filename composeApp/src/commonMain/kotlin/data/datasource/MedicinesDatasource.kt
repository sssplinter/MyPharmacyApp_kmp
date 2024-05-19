package data.datasource

import data.entity.Medicine
import kr.sementsova.composeapp.db.MedicineType

interface MedicinesDatasource {

    fun getAllMedicines(): List<Medicine>

    fun insertMedicine(medicine: Medicine)

    fun deleteMedicine(id: Long)

    fun getAllMedicineTypes(): List<MedicineType>
}
