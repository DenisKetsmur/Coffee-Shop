import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coffeeshop.data.user.ClientViewModel
import com.example.coffeeshop.data.user.EmployeeViewModel
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.navigationmodule.LocalRouter

@Composable
fun EditClientScreen(
    clientId: String,
    viewModel: ClientViewModel = viewModel()
) {
    val clientList by viewModel.items.collectAsState()
    val client = clientList.find { it.id == clientId.toInt() }

    if (client == null) {
        Text(text = "клієнта не знайдено", modifier = Modifier.padding(16.dp))
        return
    }

    EditClientContent(
        client = client,
        onSaveSuccess = {client ->
            viewModel.edit(client)
        }
    )
}

@Composable
fun EditClientContent(
    client: User.Client,
    onSaveSuccess: (User.Client) -> Unit = {}
) {
    val router = LocalRouter.current

    var clientName by remember { mutableStateOf(client.name) }
    var clientSurname by remember { mutableStateOf(client.surname) }
    var clientNumber by remember { mutableStateOf(client.phoneNumber) }
    var clientEmail by remember { mutableStateOf(client.email) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Інформація про клієнта",
                    fontSize = 20.sp,
                )
                CustomOutlinedInputTextField(
                    value = clientName,
                    onValueChange = { clientName = it },
                    label = { Text("Ім'я") },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedInputTextField(
                    value = clientSurname,
                    onValueChange = { clientSurname = it },
                    label = { Text("Прізвише") },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedInputTextField(
                    value = clientNumber,
                    onValueChange = { clientNumber = it },
                    label = { Text("Номер телефону") },
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedInputTextField(
                    value = clientEmail,
                    onValueChange = { clientEmail = it },
                    label = { Text("Пошта") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val editClient = client.copy(
                        name = clientName,
                        surname = clientSurname,
                        phoneNumber = clientNumber,
                        email = clientEmail,
                    )
                    onSaveSuccess(editClient)
                    router.pop()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Зберегти")
            }
        }
    }
}
