package presentation.ui.screens.add_medicine.screen.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.screens.medicines_list.screen.items.MedicineListItem
import presentation.ui.screens.medicines_list.state.entity.MedicineUiItem

@Composable
fun MedicinesList(medicines: List<MedicineUiItem>, onDeleteClick: (Long) -> Unit) {
    LazyColumn {
        items(medicines) { medicineItem ->
            MedicineListItem(
                medicine = medicineItem,
                onDeleteClick = { medicine -> onDeleteClick(medicine.id) },
                modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp)
            )
        }
    }
}
