package presentation.ui.screens.medicines_list.state

import androidx.lifecycle.viewModelScope
import domain.use_cases.DeleteMedicineUseCase
import domain.use_cases.GetAllMedicinesUseCase
import kotlinx.coroutines.launch
import presentation.base.mvi.MVIViewModel
import presentation.ui.screens.medicines_list.state.MedicinesListEffect.NavigateToAddMedicine
import presentation.ui.screens.medicines_list.state.MedicinesListEvent.OnAddMedicineClick
import presentation.ui.screens.medicines_list.state.MedicinesListEvent.OnDeleteMedicineClick
import presentation.ui.screens.medicines_list.state.MedicinesListState.Content
import presentation.ui.screens.medicines_list.state.MedicinesListState.Loading

class MedicinesListViewModel(
    private val getAllMedicinesUseCase: GetAllMedicinesUseCase,
    private val deleteMedicineUseCase: DeleteMedicineUseCase
) : MVIViewModel<MedicinesListEvent, MedicinesListState, MedicinesListEffect>() {

    fun refreshList() {
        viewModelScope.launch {
            loadMedicines()
        }
    }

    private suspend fun loadMedicines() {
        setState { Loading }
        val medicines = getAllMedicinesUseCase()
        setState { Content(medicines = medicines) }
    }

    private fun deleteMedicine(medicineId: Long) {
        viewModelScope.launch {
            deleteMedicineUseCase(medicineId)
            loadMedicines()
        }
    }

    override fun createInitialState(): MedicinesListState = Loading

    override fun handleEvent(event: MedicinesListEvent) {
        when (event) {
            OnAddMedicineClick -> setEffect { NavigateToAddMedicine }
            is OnDeleteMedicineClick -> deleteMedicine(event.medicineId)
        }
    }
}
