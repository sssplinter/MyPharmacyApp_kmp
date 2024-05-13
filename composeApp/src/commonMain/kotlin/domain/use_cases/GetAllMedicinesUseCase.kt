package domain.use_cases

import data.entity.Medicine
import data.repository.MedicinesRepository

class GetAllMedicinesUseCase(private val repository: MedicinesRepository) {

    suspend operator fun invoke(): List<Medicine> {
        return repository.getAllMedicines().sortedBy { it.name }
    }
}
