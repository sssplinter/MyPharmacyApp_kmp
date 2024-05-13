package kr.sementsova.composeapp.db

import kotlinx.datetime.toLocalDate
import data.entity.Medicine as MedicineItem


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = MyPharmacyDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.myPharmacyDatabaseQueries

    internal fun getAllMedicines(): List<MedicineItem> {
        return dbQuery.getAllMedicines(::mapMedicineItem).executeAsList()
    }

    fun insertMedicine(medicine: MedicineItem) {
        dbQuery.insertMedicine(
            name = medicine.name,
            description = medicine.description,
            expirationDateUTC = medicine.expirationDate.toString(),
            type_id = medicine.typeId?.toLong()
        )
    }

    fun deleteMedicine(id: Long) {
        dbQuery.deleteMedicine(id)
    }

    fun removeAllMedicines() {
        dbQuery.removeAllMedicines()
    }

    fun insertMedicineType(type: String) {
        dbQuery.insertMedicineType(type = type)
    }

    fun getAllMedicineTypes(): List<MedicineType> {
        return dbQuery.getAllMedicineTypes().executeAsList()
    }

    private fun mapMedicineItem(
        id: Long,
        name: String,
        description: String?,
        expirationDateUTC: String?,
        typeId: Long?
    ): MedicineItem {
        return MedicineItem(
            id = id.toInt(),
            name = name,
            description = description,
            expirationDate = expirationDateUTC?.toLocalDate(),
            typeId = typeId?.toInt()
        )
    }

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllMedicines()
        }
    }
}
