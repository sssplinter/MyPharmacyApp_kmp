package domain.use_cases

import data.repository.MedicinesRepository
import presentation.ui.screens.medicines_list.state.entity.MedicineUiItem
import presentation.ui.screens.medicines_list.state.entity.toMedicineUiItem

class GetAllMedicinesUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(): List<MedicineUiItem> {
        return repository.getAllMedicines().sortedBy { it.name }.map { it.toMedicineUiItem() }
    }
}
