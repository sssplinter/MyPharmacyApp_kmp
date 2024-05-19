package data.repository

import data.entity.Medicine
import kr.sementsova.composeapp.db.MedicineType

interface MedicinesRepository {

    suspend fun getAllMedicineTypes(): List<MedicineType>

    suspend fun insertMedicine(medicine: Medicine)

    suspend fun deleteMedicine(id: Long)

    suspend fun getAllMedicines(): List<Medicine>
}
