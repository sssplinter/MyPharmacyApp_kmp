package presentation.ui.screens.add_medicine.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import data.entity.MedicineItem
import domain.use_cases.AddMedicineUseCase
import domain.use_cases.GelAllMedicineTypesUseCase
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import presentation.base.mvi.MVIViewModel
import presentation.ui.screens.add_medicine.state.AddMedicineEffect.NavigateBack
import presentation.ui.screens.add_medicine.state.AddMedicineEffect.OnInvalidMedicineDataAlert
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineDescriptionChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineExpirationDateChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineNameChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineTypeChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.OnAddMedicineClick
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.OnNavigateBack
import presentation.ui.screens.add_medicine.state.AddMedicineState.FillingOut
import presentation.ui.screens.add_medicine.state.AddMedicineState.Loading

class AddMedicineViewModel(
    private val getAllMedicineTypesUseCase: GelAllMedicineTypesUseCase,
    private val addMedicineUseCase: AddMedicineUseCase
) : MVIViewModel<AddMedicineEvent, AddMedicineState, AddMedicineEffect>() {

    private val today: LocalDate
        get() = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

    val medicineTypes = mutableStateListOf<MedicineTypeUiItem>()

    init {
        viewModelScope.launch {
            initMedicineTypes()
        }
    }

    private suspend fun initMedicineTypes() {
        medicineTypes.addAll(getAllMedicineTypesUseCase())
        setState { FillingOut(expirationDate = today, medicineType = medicineTypes[0]) }
    }

    override fun createInitialState(): AddMedicineState = Loading

    override fun handleEvent(event: AddMedicineEvent) {
        when (event) {
            OnAddMedicineClick -> addNewMedicine()
            OnNavigateBack -> setEffect { NavigateBack }
            is InputEvent -> handleInputEvent(event)
        }
    }

    private fun handleInputEvent(event: InputEvent) {
        (currentState as? FillingOut)?.let { currentState ->
            when (event) {
                is MedicineDescriptionChanged -> setState {
                    currentState.copy(medicineDescription = event.medicineDescription)
                }

                is MedicineExpirationDateChanged -> setState {
                    currentState.copy(expirationDate = event.expirationDate)
                }

                is MedicineNameChanged -> setState {
                    currentState.copy(medicineName = event.medicineName)
                }

                is MedicineTypeChanged -> setState {
                    currentState.copy(medicineType = event.medicineType)
                }
            }
        }
    }

    private fun addNewMedicine() {
        (currentState as? FillingOut)?.let { currentState ->
            if (isValidMedicine()) {
                viewModelScope.launch {
                    addMedicineUseCase(
                        MedicineItem(
                            name = currentState.medicineName,
                            description = currentState.medicineDescription,
                            expirationDate = currentState.expirationDate,
                            type = currentState.medicineType.toMedicineTypeItem()
                        )
                    )
                    setEffect { NavigateBack }
                }
            } else {
                setEffect { OnInvalidMedicineDataAlert }
            }
        }
    }

    private fun isValidMedicine(): Boolean {
        return (currentState as? FillingOut)?.let {
            !(it.medicineName.isEmpty() || it.medicineDescription.isEmpty())
        } ?: false
    }
}
