package data.repository.impl

import data.datasource.MedicinesDatasource
import data.entity.MedicineItem
import data.entity.MedicineTypeItem
import data.repository.MedicinesRepository
import kotlinx.coroutines.withContext
import presentation.base.threads.ioD

class MedicineRepositoryImpl(
    private val localDatasource: MedicinesDatasource
) : MedicinesRepository {

    override suspend fun getAllMedicineTypes(): List<MedicineTypeItem> = withContext(ioD) {
        localDatasource.getAllMedicineTypes()
    }

    override suspend fun insertMedicine(medicineItem: MedicineItem) = withContext(ioD) {
        localDatasource.insertMedicine(medicineItem)
    }

    override suspend fun deleteMedicine(id: Long) = withContext(ioD) {
        localDatasource.deleteMedicine(id)
    }

    override suspend fun getAllMedicines(): List<MedicineItem> = withContext(ioD) {
        localDatasource.getAllMedicines()
    }
}
