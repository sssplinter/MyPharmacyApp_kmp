package data.datasource.impl.local

import data.datasource.MedicinesDatasource
import data.entity.Medicine
import kr.sementsova.composeapp.db.Database
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import kr.sementsova.composeapp.db.MedicineType

class LocalMedicinesDatasource(databaseDriverFactory: DatabaseDriverFactory): MedicinesDatasource {

    private val database = Database(databaseDriverFactory)

    override fun getAllMedicines(): List<Medicine> {
        return database.getAllMedicines()
    }

    override fun insertMedicine(medicine: Medicine) {
        database.insertMedicine(medicine)
    }

    override fun deleteMedicine(id: Long) {
        database.deleteMedicine(id)
    }

    override fun getAllMedicineTypes(): List<MedicineType> {
        return database.getAllMedicineTypes()
    }
}
