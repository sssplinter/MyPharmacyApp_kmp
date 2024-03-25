package repository

import data.entity.Medicine
import kr.sementsova.composeapp.db.Database
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import kr.sementsova.composeapp.db.MedicineType

class MedicinesRepository(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = Database(databaseDriverFactory)

    fun insertMedicineType(type: String) {
        database.insertMedicineType(type)
    }

    fun getAllMedicineTypes(): List<MedicineType> {
        return database.getAllMedicineTypes()
    }

    fun insertMedicine(medicine: Medicine) {
        database.insertMedicine(medicine)
    }

    fun deleteMedicine(id: Long) {
        database.deleteMedicine(id)
    }

    fun getAllMedicines(): List<Medicine> {
        return database.getAllMedicines()
    }

    fun removeAllMedicines() {
        return database.removeAllMedicines()
    }

    fun clearDatabase() {
        database.clearDatabase()
    }
}