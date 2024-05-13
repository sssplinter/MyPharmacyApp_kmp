package domain.use_cases

import data.repository.MedicinesRepository

class DeleteMedicineUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(medicineId: Long) {
        repository.deleteMedicine(medicineId)
    }
}
