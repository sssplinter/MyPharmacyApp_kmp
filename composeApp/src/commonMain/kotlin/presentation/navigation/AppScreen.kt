package presentation.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class AppScreen : ScreenProvider {

    data object MedicinesListAppScreen : AppScreen()
    data object AddMedicineAppScreen : AppScreen()
}
