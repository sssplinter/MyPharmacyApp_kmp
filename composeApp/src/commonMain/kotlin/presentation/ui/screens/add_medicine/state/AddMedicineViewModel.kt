package presentation.ui.screens.add_medicine.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import data.entity.Medicine
import domain.use_cases.AddMedicineUseCase
import domain.use_cases.GelAllMedicineTypesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kr.sementsova.composeapp.db.MedicineType
import presentation.common.BaseViewModel

class AddMedicineViewModel(
    private val getAllMedicineTypesUseCase: GelAllMedicineTypesUseCase,
    private val addMedicineUseCase: AddMedicineUseCase
) : BaseViewModel() {

    val today = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

    val medicineTypes = mutableStateListOf<MedicineType>()

    init {
        viewModelScope.launch {
            initMedicineTypes()
        }
    }

    private suspend fun initMedicineTypes() {
        medicineTypes.addAll(getAllMedicineTypesUseCase())
    }

    fun addNewMedicine(medicine: Medicine) {
        if (isValidMedicine(medicine)) {
            viewModelScope.launch {
                addMedicineUseCase(medicine)
            }
        } else {
            // TODO alert
        }
    }

    private fun isValidMedicine(
        medicine: Medicine
    ): Boolean {
        return !(medicine.name.isBlank() || medicine.name.isEmpty()) &&
                // TODO get rid of !!
                medicine.expirationDate!! >= today
    }
}
