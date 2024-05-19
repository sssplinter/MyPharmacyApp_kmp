import androidx.compose.ui.window.ComposeUIViewController
import data.datasource.impl.local.LocalMedicinesDatasource
import data.repository.impl.MedicineRepositoryImpl
import domain.use_cases.AddMedicineUseCase
import domain.use_cases.DeleteMedicineUseCase
import domain.use_cases.GelAllMedicineTypesUseCase
import domain.use_cases.GetAllMedicinesUseCase
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import presentation.ui.screens.add_medicine.state.AddMedicineViewModel
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel

fun MainViewController() = run {

    // todo imlement DI
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

    ComposeUIViewController { App(medicinesListViewModel, addMedicineViewModel) }
}
