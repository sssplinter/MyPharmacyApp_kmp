package presentation.ui.screens.add_medicine.state

import kotlinx.datetime.LocalDate
import presentation.base.mvi.UiEffect
import presentation.base.mvi.UiEvent
import presentation.base.mvi.UiState

sealed interface AddMedicineState : UiState {

    data object Loading : AddMedicineState

    data class FillingOut(
        val medicineName: String = "",
        val medicineDescription: String = "",
        val medicineType: MedicineTypeUiItem,
        val expirationDate: LocalDate,
    ) : AddMedicineState
}

sealed interface AddMedicineEvent : UiEvent {

    data object OnNavigateBack : AddMedicineEvent
    data object OnAddMedicineClick : AddMedicineEvent

    sealed interface InputEvent : AddMedicineEvent {

        data class MedicineNameChanged(val medicineName: String) : InputEvent
        data class MedicineDescriptionChanged(val medicineDescription: String) : InputEvent
        data class MedicineTypeChanged(val medicineType: MedicineTypeUiItem) : InputEvent
        data class MedicineExpirationDateChanged(val expirationDate: LocalDate) : InputEvent
    }
}

sealed interface AddMedicineEffect : UiEffect {

    data object NavigateBack : AddMedicineEffect
    data object OnInvalidMedicineDataAlert : AddMedicineEffect
}
