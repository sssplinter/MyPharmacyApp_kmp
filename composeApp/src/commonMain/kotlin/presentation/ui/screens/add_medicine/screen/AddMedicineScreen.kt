package presentation.ui.screens.add_medicine.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import presentation.base.mvi.UiState
import presentation.ui.screens.add_medicine.state.AddMedicineEffect.NavigateBack
import presentation.ui.screens.add_medicine.state.AddMedicineEffect.OnInvalidMedicineDataAlert
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineDescriptionChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineExpirationDateChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineNameChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.InputEvent.MedicineTypeChanged
import presentation.ui.screens.add_medicine.state.AddMedicineEvent.OnAddMedicineClick
import presentation.ui.screens.add_medicine.state.AddMedicineState
import presentation.ui.screens.add_medicine.state.AddMedicineState.FillingOut
import presentation.ui.screens.add_medicine.state.AddMedicineState.Loading
import presentation.ui.screens.add_medicine.state.AddMedicineViewModel
import presentation.ui.utils.ui.date_picker.ExpirationDateTextView
import presentation.ui.utils.ui.drop_down_menu.CustomDropDownMenu
import presentation.ui.utils.ui.loading_screen.LoadingScreen

class AddMedicineScreen(private val viewModel: AddMedicineViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val uiState = viewModel.uiState.collectAsState().value

        val snackbarHostState = remember { SnackbarHostState() }

        val scope = rememberCoroutineScope()
        initObservable(scope, viewModel, snackbarHostState) { navigator.pop() }

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = ""
                            )
                        }
                    },
                    title = { Text("New Medicine") }
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { _ ->
            when (uiState) {
                Loading -> LoadingScreen()
                is FillingOut -> AddMedicineContent(uiState, viewModel)
            }
        }
    }
}

@Composable
fun AddMedicineContent(uiState: FillingOut, viewModel: AddMedicineViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {

        Text(text = "New medicine details:", style = MaterialTheme.typography.h6)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.medicineName,
            onValueChange = { value -> viewModel.setEvent(MedicineNameChanged(value)) },
            label = { Text("Medicine name") })

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            value = uiState.medicineDescription,
            onValueChange = { value -> viewModel.setEvent(MedicineDescriptionChanged(value)) },
            label = { Text("Medicine description (optional)") })

        CustomDropDownMenu(
            expanded = expanded,
            options = viewModel.medicineTypes,
            selectedOption = uiState.medicineType,
            onSelectOption = { medicineType ->
                viewModel.setEvent(MedicineTypeChanged(medicineType))
                expanded = false
            },
            onExpandedChange = { isExpanded ->
                expanded = isExpanded
            },
            label = "Medicine type",
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        )

        ExpirationDateTextView(uiState.expirationDate, onConfirmDate = { date ->
            viewModel.setEvent(MedicineExpirationDateChanged(date))
        })

        Button(
            onClick = { viewModel.setEvent(OnAddMedicineClick) },
            modifier = Modifier.fillMaxWidth().height(90.dp).padding(vertical = 20.dp)
        ) { Text("Add medicine") }
    }

}

private fun initObservable(
    composableScope: CoroutineScope,
    viewModel: AddMedicineViewModel,
    snackbarHostState: SnackbarHostState,
    onNavigateBack: () -> Unit,
) {

    composableScope.launch {
        viewModel.effect.collect { effect ->
            composableScope.ensureActive()
            when (effect) {
                NavigateBack -> onNavigateBack()
                OnInvalidMedicineDataAlert -> composableScope.launch {
                    snackbarHostState.showSnackbar("Not valid data. Fix and try again")
                }
            }
        }
    }
}
