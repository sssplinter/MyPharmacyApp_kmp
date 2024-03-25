package screens.utils.ui.tag

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagView(
    text: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.border(
            1.dp,
            MaterialTheme.colors.primary,
            RoundedCornerShape(50)
        ),
        shape = RoundedCornerShape(50),
        color = Color.White
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = 3.dp, horizontal = 5.dp),
            style = MaterialTheme.typography.body2.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.W600
            )
        )
    }
}