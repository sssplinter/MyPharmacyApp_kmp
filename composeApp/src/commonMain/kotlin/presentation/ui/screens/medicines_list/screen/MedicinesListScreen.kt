package presentation.ui.screens.medicines_list.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import presentation.navigation.AppScreen
import presentation.ui.screens.add_medicine.screen.items.MedicinesList
import presentation.ui.screens.medicines_list.state.MedicinesListEffect.NavigateToAddMedicine
import presentation.ui.screens.medicines_list.state.MedicinesListEvent.OnAddMedicineClick
import presentation.ui.screens.medicines_list.state.MedicinesListEvent.OnDeleteMedicineClick
import presentation.ui.screens.medicines_list.state.MedicinesListState
import presentation.ui.screens.medicines_list.state.MedicinesListState.Content
import presentation.ui.screens.medicines_list.state.MedicinesListState.Loading
import presentation.ui.screens.medicines_list.state.MedicinesListViewModel
import presentation.ui.utils.ui.floating_action_button.AddFloatingActionButton
import presentation.ui.utils.ui.loading_screen.LoadingScreen

class MedicinesListScreen(private val viewModel: MedicinesListViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val addMedicineScreen = rememberScreen(AppScreen.AddMedicineAppScreen)

        val scope = rememberCoroutineScope()
        initObservable(scope, viewModel) { navigator.push(addMedicineScreen) }

        val uiState: MedicinesListState? = viewModel.uiState.collectAsState(initial = null).value

        LaunchedEffect(key1 = Unit) {
            viewModel.refreshList()
        }

        Scaffold(
            floatingActionButton = {
                if (uiState is Content) {
                    AddFloatingActionButton(
                        text = "Add medicine",
                        modifier = Modifier.padding(bottom = 15.dp),
                        onClick = { viewModel.setEvent(OnAddMedicineClick) })
                }
            },
            topBar = {
                TopAppBar(title = { Text("Medicines List") })
            }
        ) { _ ->
            when (uiState) {
                is Content -> MedicinesList(
                    uiState.medicines,
                    onDeleteClick = { medicine ->
                        viewModel.setEvent(OnDeleteMedicineClick(medicine))
                    })

                Loading, null -> LoadingScreen()
            }
        }
    }
}

private fun initObservable(
    composableScope: CoroutineScope,
    viewModel: MedicinesListViewModel,
    onAddMedicineClick: () -> Unit
) {

    composableScope.launch {
        viewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                NavigateToAddMedicine -> onAddMedicineClick()
            }
        }
    }
}
