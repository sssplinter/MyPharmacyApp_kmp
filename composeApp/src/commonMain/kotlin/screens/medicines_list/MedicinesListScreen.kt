package screens.medicines_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.entity.Medicine
import navigation.AppScreen
import repository.MedicinesRepository
import screens.medicines_list.items.MedicineListItem
import screens.medicines_list.model.toMedicineItem
import screens.utils.ui.floating_action_button.AddFloatingActionButton

class MedicinesListScreen(private val medicinesRepository: MedicinesRepository) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val addMedicineScreen = rememberScreen(AppScreen.AddMedicineAppScreen)

        var medicines by remember {
            mutableStateOf(
                medicinesRepository.getAllMedicines().sortedBy { it.name }
            )
        }

        Scaffold(
            floatingActionButton = {
                AddFloatingActionButton(
                    text = "Add medicine",
                    modifier = Modifier.padding(bottom = 15.dp, end = 10.dp),
                    onClick = { navigator.push(addMedicineScreen) })
            },
            topBar = {
                TopAppBar(
                    title = {
                        Text("Medicines List")
                    }
                )
            }
        ) { _ ->
            MedicinesList(
                medicines,
                onDeleteClick = {
                    medicinesRepository.deleteMedicine(it.toLong())
                    medicines = medicinesRepository.getAllMedicines().sortedBy { it.name }
                })
        }
    }
}

@Composable
fun MedicinesList(medicines: List<Medicine>, onDeleteClick: (Int) -> Unit) {
    LazyColumn {
        items(medicines) { medicineItem ->
            MedicineListItem(
                medicine = medicineItem.toMedicineItem(),
//                onEditClick = {},
                onDeleteClick = { onDeleteClick(it.id) },
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp)
            )
        }
    }
}
