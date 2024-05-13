import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import presentation.navigation.AppScreen
import presentation.ui.screens.add_medicine.screen.AddMedicineScreen
import presentation.ui.screens.add_medicine.state.AddMedicineViewModel
import presentation.ui.screens.medicines_list.screen.MedicinesListScreen
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel

@Composable
fun App(
    medicinesListViewModel: MedicinesListViewModel,
    addMedicineViewModel: AddMedicineViewModel
) {

    ScreenRegistry {
        register<AppScreen.MedicinesListAppScreen> {
            MedicinesListScreen(medicinesListViewModel)
        }
        register<AppScreen.AddMedicineAppScreen> {
            AddMedicineScreen(addMedicineViewModel)
        }
    }

    Navigator(MedicinesListScreen(medicinesListViewModel))
}
