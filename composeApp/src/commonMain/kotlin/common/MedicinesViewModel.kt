package common

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import common.threads.ioD
import data.entity.Medicine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repository.MedicinesRepository

class MedicinesViewModel(
    private val repository: MedicinesRepository
) : BaseViewModel() {
    val medicinesItems = mutableStateListOf<Medicine>()

    init {
//        initTypes()
        loadMedicines()
    }

    fun initTypes() {
        repository.insertMedicineType("Analgesics")
        repository.insertMedicineType("Antibiotics")
    }

    private fun loadMedicines() {
        viewModelScope.launch {
            val result = withContext(ioD) {
                repository.getAllMedicines().sortedBy { it.name }
            }
            medicinesItems.addAll(result)
        }
    }

    fun deleteMedicine(medicineId: Long) {
        repository.deleteMedicine(medicineId)
        medicinesItems.clear()
        loadMedicines()
    }
}