package presentation.ui.screens.medicines_list.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import presentation.common.threads.ioD
import data.entity.Medicine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import data.repository.MedicinesRepository
import domain.use_cases.DeleteMedicineUseCase
import domain.use_cases.GetAllMedicinesUseCase
import presentation.common.BaseViewModel

class MedicinesListViewModel(
    private val getAllMedicinesUseCase: GetAllMedicinesUseCase,
    private val deleteMedicineUseCase: DeleteMedicineUseCase
) : BaseViewModel() {

    val medicinesItems = mutableStateListOf<Medicine>()

    init {
        viewModelScope.launch {
            loadMedicines()
        }
    }

    private suspend fun loadMedicines() {
        medicinesItems.addAll(getAllMedicinesUseCase())
    }

    fun deleteMedicine(medicineId: Long) {
        viewModelScope.launch {
            deleteMedicineUseCase(medicineId)
            medicinesItems.clear()
            loadMedicines()
        }
    }
}
