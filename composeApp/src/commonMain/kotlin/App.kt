import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import common.MedicinesViewModel
import navigation.AppScreen
import repository.MedicinesRepository
import screens.add_medicine.AddMedicineScreen
import screens.medicines_list.MedicinesListScreen

@Composable
fun App(medicinesRepository: MedicinesRepository, viewModel: MedicinesViewModel) {

    ScreenRegistry {
        register<AppScreen.MedicinesListAppScreen> {
            MedicinesListScreen(viewModel)
        }
        register<AppScreen.AddMedicineAppScreen> {
            AddMedicineScreen(medicinesRepository)
        }
    }

    Navigator(MedicinesListScreen(viewModel))
}
