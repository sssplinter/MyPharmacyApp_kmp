package domain.use_cases

import data.repository.MedicinesRepository
import presentation.ui.screens.add_medicine.state.MedicineTypeUiItem
import presentation.ui.screens.add_medicine.state.toMedicineTypeUiItem

class GelAllMedicineTypesUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(): List<MedicineTypeUiItem> {
        return repository.getAllMedicineTypes().map { it.toMedicineTypeUiItem() }
    }
}
