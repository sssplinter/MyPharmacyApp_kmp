package data.datasource.impl.local

import data.datasource.MedicinesDatasource
import data.entity.MedicineItem
import data.entity.MedicineTypeItem
import kr.sementsova.composeapp.db.Database
import kr.sementsova.composeapp.db.DatabaseDriverFactory

class LocalMedicinesDatasource(databaseDriverFactory: DatabaseDriverFactory): MedicinesDatasource {

    private val database = Database(databaseDriverFactory)

    override fun getAllMedicines(): List<MedicineItem> {
        return database.getAllMedicines()
    }

    override fun insertMedicine(medicineItem: MedicineItem) {
        database.insertMedicine(medicineItem)
    }

    override fun deleteMedicine(id: Long) {
        database.deleteMedicine(id)
    }

    override fun getAllMedicineTypes(): List<MedicineTypeItem> {
        return database.getAllMedicineTypes()
    }
}
