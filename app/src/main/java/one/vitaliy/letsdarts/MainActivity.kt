package one.vitaliy.letsdarts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import one.vitaliy.letsdarts.ui.theme.LetsDartsTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

private val minDate = LocalDateTime.now().minusYears(18).toInstant(ZoneOffset.UTC).toEpochMilli()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LetsDartsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val isDatePickerVisible = remember { mutableStateOf(false) }
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )

        Button(onClick = { isDatePickerVisible.value = !isDatePickerVisible.value }) {
            Text(text = "Show calendar")
        }
        if (isDatePickerVisible.value) {
            DartsDatePicker(onDismiss = { isDatePickerVisible.value = false })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DartsDatePicker(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    datePickerState: DatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = minDate,
        yearRange = IntRange(1900, LocalDate.now().minusYears(18).year),
    ),
) {
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text("Confirm")
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
            dateValidator = { timestamp ->
                timestamp < minDate
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LetsDartsTheme {
        Greeting("Android")
    }
}
