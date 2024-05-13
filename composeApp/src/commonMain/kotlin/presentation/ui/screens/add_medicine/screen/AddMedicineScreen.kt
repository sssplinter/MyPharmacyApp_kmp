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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.entity.Medicine
import presentation.ui.screens.add_medicine.state.AddMedicineViewModel
import presentation.ui.utils.ui.date_picker.ExpirationDateTextView
import presentation.ui.utils.ui.drop_down_menu.MyDropDownMenu

class AddMedicineScreen(private val viewModel: AddMedicineViewModel) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        var medicineName by remember { mutableStateOf("") }
        var medicineDescription by remember { mutableStateOf("") }

        val medicineTypes = viewModel.medicineTypes
        var expanded by remember { mutableStateOf(false) }
        var selectedMedicineType by remember { mutableStateOf(medicineTypes[0]) }

        var expirationDate by remember { mutableStateOf(viewModel.today) }

        val snackbarHostState = remember { SnackbarHostState() }

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
                    title = {
                        Text("New Medicine")
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }
        ) { _ ->
            Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {

                Text(text = "New medicine details:", style = MaterialTheme.typography.h6)

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = medicineName,
                    onValueChange = { value -> medicineName = value },
                    label = { Text("Medicine name") })
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    value = medicineDescription,
                    onValueChange = { value -> medicineDescription = value },
                    label = { Text("Medicine description (optional)") })

                MyDropDownMenu(
                    expanded = expanded,
                    options = medicineTypes,
                    selectedOption = selectedMedicineType,
                    onSelectOption = { new ->
                        selectedMedicineType = new
                        expanded = false
                    },
                    onExpandedChange = { isExpanded ->
                        expanded = isExpanded
                    },
                    label = "Medicine type",
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
                )

                ExpirationDateTextView(expirationDate, onConfirmDate = { expirationDate = it })

                Button(
                    onClick = {
                        viewModel.addNewMedicine(
                            Medicine(
                                name = medicineName,
                                description = medicineDescription,
                                expirationDate = expirationDate,
                                typeId = 1 // TODO selectedMedicineType.type.id.toInt()
                            )
                        )
                        // TODO navigte back should be executed only after success add
                        navigator.pop()
                        // TODO implement vm event for showing it
//                        } else {
//                            scope.launch {
//                                snackbarHostState.showSnackbar("Not valid data. Fix and try again")
//                            }
//                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(90.dp).padding(vertical = 20.dp)
                ) { Text("Add medicine") }
            }
        }
    }
}
