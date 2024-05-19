package presentation.ui.screens.medicines_list.screen.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.ui.screens.medicines_list.state.entity.ExpirationStatus
import presentation.ui.screens.medicines_list.state.entity.MedicineUiItem
import presentation.ui.utils.ui.tag.TagView

@Composable
fun MedicineListItem(
    medicine: MedicineUiItem,
    onDeleteClick: (MedicineUiItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = medicine.expirationStatus.toMedicineItemColor(),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 18.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = medicine.name,
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = medicine.typeString,
                        style = MaterialTheme.typography.caption
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Row {
                        TagView("until ${medicine.expirationDate}")
                        IconButton(
                            onClick = { onDeleteClick(medicine) },
                            modifier = Modifier.size(32.dp).padding(start = 12.dp)
                        ) {
                            Icon(Icons.Filled.Delete, "Delete medicine")
                        }
                    }
                }
            }
            medicine.description?.let { Text(it, modifier = Modifier.padding(vertical = 8.dp)) }
        }
    }
}

fun ExpirationStatus.toMedicineItemColor(): Color {
    return when (this) {
        ExpirationStatus.Normal -> Color.Green
        ExpirationStatus.Expiring -> Color.Yellow
        ExpirationStatus.Expired -> Color.Red
    }.copy(alpha = 0.4f)
}
