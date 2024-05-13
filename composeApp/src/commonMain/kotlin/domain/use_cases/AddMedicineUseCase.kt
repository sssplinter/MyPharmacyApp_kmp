package domain.use_cases

import data.entity.Medicine
import data.repository.MedicinesRepository

class AddMedicineUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(medicine: Medicine) {
        repository.insertMedicine(medicine)
    }
}