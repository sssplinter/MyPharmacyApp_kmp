import androidx.compose.ui.window.ComposeUIViewController
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import repository.MedicinesRepository

fun MainViewController() = run {
    val medicinesRepository = MedicinesRepository(DatabaseDriverFactory())
    ComposeUIViewController { App(medicinesRepository) }
}
