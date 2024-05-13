import androidx.compose.ui.window.ComposeUIViewController
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import data.repository.MedicinesRepository

fun MainViewController() = run {
    val medicinesRepository = MedicinesRepository(DatabaseDriverFactory())
    val viewModel = MedicinesListViewModel(medicinesRepository)
    ComposeUIViewController { App(medicinesRepository, viewModel) }
}
