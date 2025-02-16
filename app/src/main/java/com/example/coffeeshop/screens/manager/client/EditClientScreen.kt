import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.data.filled.client
import com.example.coffeeshop.data.filled.sampleSupplier
import com.example.coffeeshop.data.product.RawMaterial
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.data.user.User
import com.example.coffeeshop.screens.administrator.purchase.ProductInputItem
import com.example.coffeeshop.screens.administrator.purchase.SupplierInfoInout1
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import com.example.navigationmodule.LocalRouter

@Composable
fun EditClientScreen() {
    EditClientContent(
        client = client
    )
}

@Composable
fun EditClientContent(
    client: User.Client,
    //onSaveSuccess: () -> Unit = {}
) {
    val router = LocalRouter.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        //.wrapContentHeight(),
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
            ClientInfoInout(client)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    //onSaveSuccess()
                    router.pop()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Зберегти")
            }
        }
    }
}

@Composable
fun ClientInfoInout(
    client: User.Client,
){
    var clientName by remember { mutableStateOf(client.name) }
    var clientSurname by remember { mutableStateOf(client.surname) }
    var clientNumber by remember { mutableStateOf(client.phoneNumber) }
    var clientEmail by remember { mutableStateOf(client.email) }

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
}
