import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    // ================= STATE FROM VIEWMODEL =================
    val todo by homeScreenViewModel.todo.collectAsState()

    // ================= LOCAL INPUT STATE =================
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var media by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ================= INPUT FIELDS =================

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Todo Title") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Todo Description") },
            minLines = 2,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = media,
            onValueChange = { media = it },
            label = { Text("Media (optional)") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ================= BUTTON =================

        OutlinedButton(
            onClick = {
                homeScreenViewModel.createTodo(
                    title = title.text,
                    description = description.text,
                    media = media.text
                )

                // clear inputs (important UX)
                title = TextFieldValue("")
                description = TextFieldValue("")
                media = TextFieldValue("")
            }
        ) {
            Text("Create Todo")
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider()

        // ================= OUTPUT =================

        Text(
            text = todo.title,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = todo.description
        )

        Text(
            text = todo.media
        )
    }
}