package com.example.coffeeshop.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.breens.beetablescompose.utils.extractMembers
import com.example.coffeeshop.screens.Administrator.Product

@Composable
fun <T> MyTable2(
    title: List<String>,
    content: List<T>,
    columnWeights: Array<Float> = arrayOf(1f, 1f, 1f, 1f, 1f, 1f),
    renderRow: @Composable (T) -> List<String>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        HorizontalDivider()
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                title.forEachIndexed { index, titleText ->
                    Text(
                        text = titleText,
                        modifier = Modifier.weight(columnWeights[index]),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            HorizontalDivider()
            Column(modifier = Modifier.fillMaxWidth()) {
                content.forEach { item ->
                    val rowData = renderRow(item)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        rowData.forEachIndexed { index, cellText ->
                            Text(
                                text = cellText,
                                modifier = Modifier.weight(columnWeights[index]),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            columnWeights.forEachIndexed { index, weight ->
                if (index < columnWeights.size - 1) {
                    VerticalDivider(
                        modifier = Modifier
                            .weight(weight)
                            .fillMaxHeight(),
                        color = Color.Gray
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(),
                color = Color.Gray
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
inline fun <reified T : Any> MyTable(
    title: List<String>,
    titleFontWeight: TextUnit = 16.sp,
    data: List<T>,
    dataFontWeight: TextUnit = 12.sp,
    columnWeights: Array<Float>,
    lazyColumnOff:Boolean = false
){
    if(lazyColumnOff){
        Column {
            Row(
                modifier = Modifier
                    .border(width = 1.dp, color = Color.Gray),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                title.forEachIndexed { index, title ->
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = titleFontWeight,
                        modifier = Modifier.weight(columnWeights[index]),
                    )

                    VerticalDivider(
                        thickness = 2.dp,
                        color = Color.Gray,
                    )
                }
            }
            data.forEachIndexed { rowIndex, data ->
                val rowData = extractMembers(data).map {
                    it.second
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .border(width = 1.dp, color = Color.Gray),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    rowData.forEachIndexed { index, title ->
                        Text(
                            text = title,
                            modifier = Modifier.weight(columnWeights[index]),
                            textAlign = TextAlign.Center,
                            fontSize = dataFontWeight
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = Color.Gray,
                        )
                    }
                }
            }
        }
    } else{
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            stickyHeader{
                Row(
                    modifier = Modifier
                        .border(width = 1.dp, color = Color.Gray),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    title.forEachIndexed { index, title ->
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = titleFontWeight,
                            modifier = Modifier.weight(columnWeights[index]),
                        )

                        VerticalDivider(
                            thickness = 2.dp,
                            color = Color.Gray,
                        )
                    }
                }
            }
            item{
                data.forEachIndexed { rowIndex, data ->
                    val rowData = extractMembers(data).map {
                        it.second
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .border(width = 1.dp, color = Color.Gray),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        rowData.forEachIndexed { index, title ->
                            Text(
                                text = title,
                                modifier = Modifier.weight(columnWeights[index]),
                                textAlign = TextAlign.Center,
                                fontSize = dataFontWeight
                            )
                            VerticalDivider(
                                thickness = 2.dp,
                                color = Color.Gray,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 1500)
@Composable
private fun PreviewMyTable() {
    MyTable(
        title = headerTitles,
        data = productList1,
        columnWeights = arrayOf(1f, 3f, 2f, 1f, 2f, 2f),
//        renderRow = { product ->
//            listOf(
//                product.id.toString(),
//                product.name,
//                product.category,
//                product.unit,
//                product.count.toString()
//            )
//        }
    )
}

val headerTitles = listOf("№", "Назва", "Категорія", "Од. вим.", "Кількість")
val productList1 = listOf(
    Product(
        id = 1,
        name = "Кава арабіка",
        category = "Сировина",
        unit = "кг",
        count = 10.0
    ),
    Product(
        id = 2,
        name = "Кава робуста",
        category = "Сировина",
        unit = "кг",
        count = 8.0
    ),
    Product(
        id = 3,
        name = "Молоко коров’яче",
        category = "Сировина",
        unit = "л",
        count = 25.0
    ),
    Product(
        id = 4,
        name = "Шоколад чорний",
        category = "Сировина",
        unit = "кг",
        count = 5.0
    ),
    Product(
        id = 5,
        name = "Цукор тростниковий",
        category = "Сировина",
        unit = "кг",
        count = 7.0
    ),
    Product(
        id = 6,
        name = "Мука пшенична",
        category = "Сировина",
        unit = "кг",
        count = 15.0
    ),
    Product(
        id = 7,
        name = "Яйця курячі",
        category = "Сировина",
        unit = "десятки",
        count = 3.0
    ),
    Product(
        id = 8,
        name = "Маргарин",
        category = "Сировина",
        unit = "кг",
        count = 4.0
    ),
    Product(
        id = 9,
        name = "Фрукти (яблука)",
        category = "Сировина",
        unit = "кг",
        count = 20.0
    ),
    Product(
        id = 10,
        name = "Дріжджі сухі",
        category = "Сировина",
        unit = "кг",
        count = 1.0
    ),
    Product(
        id = 1,
        name = "Кава арабіка",
        category = "Сировина",
        unit = "кг",
        count = 10.0
    ),
    Product(
        id = 2,
        name = "Кава робуста",
        category = "Сировина",
        unit = "кг",
        count = 8.0
    ),
    Product(
        id = 3,
        name = "Молоко коров’яче",
        category = "Сировина",
        unit = "л",
        count = 25.0
    ),
    Product(
        id = 4,
        name = "Шоколад чорний",
        category = "Сировина",
        unit = "кг",
        count = 5.0
    ),
    Product(
        id = 5,
        name = "Цукор тростниковий",
        category = "Сировина",
        unit = "кг",
        count = 7.0
    ),
    Product(
        id = 6,
        name = "Мука пшенична",
        category = "Сировина",
        unit = "кг",
        count = 15.0
    ),
    Product(
        id = 7,
        name = "Яйця курячі",
        category = "Сировина",
        unit = "десятки",
        count = 3.0
    ),
    Product(
        id = 8,
        name = "Маргарин",
        category = "Сировина",
        unit = "кг",
        count = 4.0
    ),
    Product(
        id = 9,
        name = "Фрукти (яблука)",
        category = "Сировина",
        unit = "кг",
        count = 20.0
    ),
    Product(
        id = 10,
        name = "Дріжджі сухі",
        category = "Сировина",
        unit = "кг",
        count = 1.0
    )
)