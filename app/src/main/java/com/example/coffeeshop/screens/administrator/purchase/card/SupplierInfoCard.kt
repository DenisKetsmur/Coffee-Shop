package com.example.coffeeshop.screens.administrator.purchase.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.data.supplier.Supplier
import com.example.coffeeshop.screens.cardForScreens.CustomOutlinedInputTextField
import kotlin.math.sin

@Composable
fun SupplierInfoEdit(
    supplier: Supplier,
    onSupplierUpdated:(Supplier) -> Unit,
){
    var supplierName by remember { mutableStateOf(supplier.nameCompany) }
    var phoneNumber by remember { mutableStateOf(supplier.phoneNumber) }
    var email by remember { mutableStateOf(supplier.email) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Інформація про постачальника",
                fontSize = 20.sp,
            )
            CustomOutlinedInputTextField(
                value = supplierName,
                onValueChange = {
                    supplierName = it
                    onSupplierUpdated(supplier.copy(nameCompany = it))
                },
                label = { Text("Назва компанії") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            CustomOutlinedInputTextField(
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                    onSupplierUpdated(supplier.copy(phoneNumber = it))
                },
                label = { Text("Номер телефону") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            )
            CustomOutlinedInputTextField(
                value = email,
                onValueChange = {
                    email = it
                    onSupplierUpdated(supplier.copy(email = it))
                },
                label = { Text("Пошта") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
        }
    }
}