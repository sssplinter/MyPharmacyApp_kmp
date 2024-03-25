import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import navigation.AppScreen
import repository.MedicinesRepository
import screens.add_medicine.AddMedicineScreen
import screens.medicines_list.MedicinesListScreen

@Composable
fun App(medicinesRepository: MedicinesRepository) {

    ScreenRegistry {
        register<AppScreen.MedicinesListAppScreen> {
            MedicinesListScreen(medicinesRepository)
        }
        register<AppScreen.AddMedicineAppScreen> {
            AddMedicineScreen(medicinesRepository)
        }
    }

    Navigator(MedicinesListScreen(medicinesRepository))
}
