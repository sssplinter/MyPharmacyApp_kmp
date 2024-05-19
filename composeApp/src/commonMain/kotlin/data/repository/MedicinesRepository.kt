package data.repository

import data.entity.MedicineItem
import data.entity.MedicineTypeItem

interface MedicinesRepository {

    suspend fun getAllMedicineTypes(): List<MedicineTypeItem>

    suspend fun insertMedicine(medicineItem: MedicineItem)

    suspend fun deleteMedicine(id: Long)

    suspend fun getAllMedicines(): List<MedicineItem>
}
