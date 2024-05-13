package domain.use_cases

import data.repository.MedicinesRepository
import kr.sementsova.composeapp.db.MedicineType

class GelAllMedicineTypesUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(): List<MedicineType> {
        return repository.getAllMedicineTypes()
    }
}
