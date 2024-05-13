import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kr.sementsova.composeapp.db.DatabaseDriverFactory
import data.repository.MedicinesRepository

fun main() = application {
    val medicinesRepository = MedicinesRepository(DatabaseDriverFactory())

    val state = rememberWindowState(
        size = DpSize(700.dp, 650.dp),
        position = WindowPosition(100.dp, 100.dp)
    )
    Window(
        title = "My Pharmacy App",
        onCloseRequest = ::exitApplication,
        state = state
    ) {
        App(medicinesRepository)
    }
}
