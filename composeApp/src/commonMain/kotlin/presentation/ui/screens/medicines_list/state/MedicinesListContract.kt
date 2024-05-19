package presentation.ui.screens.medicines_list.state

import data.entity.Medicine
import presentation.base.mvi.UiEffect
import presentation.base.mvi.UiEvent
import presentation.base.mvi.UiState

sealed interface MedicinesListState : UiState {

    data object Loading : MedicinesListState
    data class Content(val medicines: List<Medicine>) : MedicinesListState
}

sealed interface MedicinesListEvent : UiEvent {

    data object OnAddMedicineClick : MedicinesListEvent
    data class OnDeleteMedicineClick(val medicineId: Long) : MedicinesListEvent
}

sealed interface MedicinesListEffect : UiEffect {

    data object NavigateToAddMedicine : MedicinesListEffect
}
