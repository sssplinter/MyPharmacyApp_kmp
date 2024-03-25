package screens.utils.ui.date_picker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogButtons
import com.vanpra.composematerialdialogs.MaterialDialogProperties
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.datetime.LocalDate

@Composable
fun ExpirationDateTextView(
    currentDate: LocalDate,
    onConfirmDate: (LocalDate) -> Unit,
) {
    var expirationDate by remember { mutableStateOf(currentDate) }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            defaultDateTimeDialogButtons(onPositiveButtonClick = {
                onConfirmDate(
                    expirationDate
                )
            })
        },
        properties = MaterialDialogProperties(
            windowSize = DpSize(400.dp, 300.dp),
            isWindowDialog = false,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        datepicker(initialDate = expirationDate) {
            expirationDate = it
        }
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = currentDate.toString(),
        onValueChange = { },
        label = { Text("Expiration date") },
        trailingIcon = {
            IconButton(
                onClick = { dialogState.show() },
                modifier = Modifier.size(32.dp).padding(start = 12.dp)
            ) {
                Icon(
                    Icons.Default.Edit,
                    ""
                )
            }
        }
    )
}

@Composable
private fun MaterialDialogButtons.defaultDateTimeDialogButtons(onPositiveButtonClick: () -> Unit) {
    positiveButton("Ok") { onPositiveButtonClick() }
    negativeButton("Cancel")
}
