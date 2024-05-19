package kr.sementsova.composeapp.db

import data.entity.MedicineItem
import data.entity.MedicineTypeItem
import kotlinx.datetime.toLocalDate


internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = MyPharmacyDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.myPharmacyDatabaseQueries

    fun getAllMedicines(): List<MedicineItem> {
        val medicineTypes = dbQuery.getAllMedicineTypes(::mapMedicineTypeItem).executeAsList()
        return dbQuery.getAllMedicines().executeAsList()
            .map { item ->
                val type = medicineTypes.find { it.id == item.type_id }
                item.mapMedicineItem(type)
            }
    }

    fun insertMedicine(medicine: MedicineItem) {
        dbQuery.insertMedicine(
            name = medicine.name,
            description = medicine.description,
            expirationDateUTC = medicine.expirationDate.toString(),
            type_id = medicine.type?.id
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

    fun getAllMedicineTypes(): List<MedicineTypeItem> {
        return dbQuery.getAllMedicineTypes(::mapMedicineTypeItem).executeAsList()
    }

    private fun Medicine.mapMedicineItem(
        medicineType: MedicineTypeItem?
    ): MedicineItem {
        return MedicineItem(
            id = id,
            name = name,
            description = description,
            expirationDate = expirationDateUTC?.toLocalDate(),
            type = medicineType
        )
    }

    private fun mapMedicineTypeItem(id: Long, type: String): MedicineTypeItem {
        return MedicineTypeItem(
            id = id,
            type = type
        )
    }

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllMedicines()
        }
    }
}
