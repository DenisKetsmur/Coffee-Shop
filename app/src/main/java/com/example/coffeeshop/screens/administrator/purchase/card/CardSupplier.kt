package com.example.coffeeshop.screens.administrator.purchase.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.coffeeshop.R
import com.example.coffeeshop.data.supplier.SupplierViewModel
import com.example.coffeeshop.data.supplier.suppliers
import com.example.coffeeshop.ui.theme.CoffeeAppTheme

@Composable
fun CardSupplier(
    supplierId: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    viewModel: SupplierViewModel = viewModel()
){
    val supplierList by viewModel.items.collectAsState()
    val supplier = supplierList.find { it.id == supplierId.toInt() }

    if (supplier == null){
        Text("supplier not found")
        return
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Box(
                modifier = Modifier.height(150.dp),
            ){
                Image(
                     painter = painterResource(supplier.imageUri),
                    contentDescription = stringResource(R.string.supplier_image),
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.FillHeight
                )
            }
            Column(
                modifier = Modifier
                    .weight(2.5f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = supplier.nameCompany,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Продукти для замовлень: ${supplier.getSupplierRawMaterials()}",
                    style = TextStyle(lineHeight = 23.sp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardSupplier(){
    CoffeeAppTheme {
        Surface {
            LazyColumn {
                items(6){
                    CardSupplier(
                        supplierId = suppliers[1].id.toString()
                    )
                }
            }
        }
    }
}