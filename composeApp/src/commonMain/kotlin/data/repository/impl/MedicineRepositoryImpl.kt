package data.repository.impl

import data.datasource.MedicinesDatasource
import data.entity.Medicine
import data.repository.MedicinesRepository
import kotlinx.coroutines.withContext
import kr.sementsova.composeapp.db.MedicineType
import presentation.base.threads.ioD

class MedicineRepositoryImpl(
    private val localDatasource: MedicinesDatasource
) : MedicinesRepository {

    override suspend fun getAllMedicineTypes(): List<MedicineType> = withContext(ioD) {
        localDatasource.getAllMedicineTypes()
    }

    override suspend fun insertMedicine(medicine: Medicine) = withContext(ioD) {
        localDatasource.insertMedicine(medicine)
    }

    override suspend fun deleteMedicine(id: Long) = withContext(ioD) {
        localDatasource.deleteMedicine(id)
    }

    override suspend fun getAllMedicines(): List<Medicine> = withContext(ioD) {
        localDatasource.getAllMedicines()
    }
}
