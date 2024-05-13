package kr.sementsova.kmp_compose

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import data.datasource.impl.local.LocalMedicinesDatasource
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import data.repository.MedicinesRepository
import data.repository.impl.MedicineRepositoryImpl
import domain.use_cases.AddMedicineUseCase
import domain.use_cases.DeleteMedicineUseCase
import domain.use_cases.GelAllMedicineTypesUseCase
import domain.use_cases.GetAllMedicinesUseCase
import presentation.ui.screens.add_medicine.state.AddMedicineViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val datasource = LocalMedicinesDatasource(DatabaseDriverFactory(this))
        val medicinesRepository = MedicineRepositoryImpl(datasource)
        val addMedicineUseCase = AddMedicineUseCase(medicinesRepository)
        val deleteMedicineUseCase = DeleteMedicineUseCase(medicinesRepository)
        val getAllMedicinesUseCase = GetAllMedicinesUseCase(medicinesRepository)
        val getAllMedicineTypesUseCase = GelAllMedicineTypesUseCase(medicinesRepository)
        val medicinesListViewModel =
            MedicinesListViewModel(getAllMedicinesUseCase, deleteMedicineUseCase)
        val addMedicineViewModel =
            AddMedicineViewModel(getAllMedicineTypesUseCase, addMedicineUseCase)

        setContent {
            App(medicinesListViewModel, addMedicineViewModel)
        }
    }
}
