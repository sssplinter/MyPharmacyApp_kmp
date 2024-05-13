package data.repository

import data.datasource.MedicinesDatasource
import data.entity.Medicine
import kotlinx.coroutines.withContext
import kr.sementsova.composeapp.db.Database
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import kr.sementsova.composeapp.db.MedicineType
import presentation.common.threads.ioD

interface MedicinesRepository {

    suspend fun getAllMedicineTypes(): List<MedicineType>

    suspend fun insertMedicine(medicine: Medicine)

    suspend fun deleteMedicine(id: Long)

    suspend fun getAllMedicines(): List<Medicine>
}