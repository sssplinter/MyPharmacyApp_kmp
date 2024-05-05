import androidx.compose.ui.window.ComposeUIViewController
import common.MedicinesViewModel
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import repository.MedicinesRepository

fun MainViewController() = run {
    val medicinesRepository = MedicinesRepository(DatabaseDriverFactory())
    val viewModel = MedicinesViewModel(medicinesRepository)
    ComposeUIViewController { App(medicinesRepository, viewModel) }
}
