package presentation.ui.utils.ui.drop_down_menu

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> MyDropDownMenu(
    expanded: Boolean,
    options: List<T>,
    selectedOption: T,
    onSelectOption: (T) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { isExpanded ->
            onExpandedChange(isExpanded)
        })
    {
        TextField(
            modifier = modifier,
            readOnly = true,
            value = selectedOption.toString(),
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    onIconClick = { onExpandedChange(!expanded) }
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {
            options.forEach { newSelectionOption ->
                DropdownMenuItem(
                    onClick = {
                        onSelectOption(newSelectionOption)
                    }
                ) {
                    Text(text = newSelectionOption.toString())
                }
            }
        }
    }
}
