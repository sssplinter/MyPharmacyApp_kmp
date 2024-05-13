package presentation.ui.screens.medicines_list.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.entity.Medicine
import presentation.navigation.AppScreen
import presentation.ui.screens.medicines_list.screen.items.MedicineListItem
import presentation.ui.screens.medicines_list.state.entity.toMedicineItem
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel
import presentation.ui.utils.ui.floating_action_button.AddFloatingActionButton

class MedicinesListScreen(private val viewModel: MedicinesListViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val addMedicineScreen = rememberScreen(AppScreen.AddMedicineAppScreen)

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
                viewModel.medicinesItems,
                onDeleteClick = { viewModel.deleteMedicine(it.toLong()) })
        }
    }
}

@Composable
fun MedicinesList(medicines: List<Medicine>, onDeleteClick: (Int) -> Unit) {
    LazyColumn {
        items(medicines) { medicineItem ->
            MedicineListItem(
                medicine = medicineItem.toMedicineItem(),
                onDeleteClick = { onDeleteClick(it.id) },
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp)
            )
        }
    }
}
