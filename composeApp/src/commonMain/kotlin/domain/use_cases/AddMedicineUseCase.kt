package domain.use_cases

import data.entity.MedicineItem
import data.repository.MedicinesRepository

class AddMedicineUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(medicineItem: MedicineItem) {
        repository.insertMedicine(medicineItem)
    }
}
