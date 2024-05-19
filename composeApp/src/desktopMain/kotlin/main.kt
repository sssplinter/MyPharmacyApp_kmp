import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import data.datasource.impl.local.LocalMedicinesDatasource
import data.repository.impl.MedicineRepositoryImpl
import domain.use_cases.AddMedicineUseCase
import domain.use_cases.DeleteMedicineUseCase
import domain.use_cases.GelAllMedicineTypesUseCase
import domain.use_cases.GetAllMedicinesUseCase
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import presentation.ui.screens.add_medicine.state.AddMedicineViewModel
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel

fun main() = application {

    val datasource = LocalMedicinesDatasource(DatabaseDriverFactory())
    val medicinesRepository = MedicineRepositoryImpl(datasource)
    val addMedicineUseCase = AddMedicineUseCase(medicinesRepository)
    val deleteMedicineUseCase = DeleteMedicineUseCase(medicinesRepository)
    val getAllMedicinesUseCase = GetAllMedicinesUseCase(medicinesRepository)
    val getAllMedicineTypesUseCase = GelAllMedicineTypesUseCase(medicinesRepository)
    val medicinesListViewModel =
        MedicinesListViewModel(getAllMedicinesUseCase, deleteMedicineUseCase)
    val addMedicineViewModel =
        AddMedicineViewModel(getAllMedicineTypesUseCase, addMedicineUseCase)

    val state = rememberWindowState(
        size = DpSize(700.dp, 650.dp),
        position = WindowPosition(100.dp, 100.dp)
    )
    Window(
        title = "My Pharmacy App",
        onCloseRequest = ::exitApplication,
        state = state
    ) {
        App(medicinesListViewModel, addMedicineViewModel)
    }
}
