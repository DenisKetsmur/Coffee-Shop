package com.example.coffeeshop.screens.Manager

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.AppRoute
import com.example.coffeeshop.R
import com.example.coffeeshop.data.Position
import com.example.coffeeshop.screens.componentsMenuScreen.ChipGroup
import com.example.navigationmodule.LocalRouter

@Composable
fun PersonnelScreen() {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val categories = listOf("Працює", "Звільнений")
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    LazyColumn {
        item {
            TextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                shape = RoundedCornerShape(28),
                label = {
                    Text(
                        text = "Search",
                        color = Color.Gray
                    )
                },
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            searchText = ""
                            focusManager.clearFocus()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Description",
                            tint = Color.Unspecified,
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            ChipGroup(
                categories,
                selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )
            CardPersonnel(
                personnel = Personnel(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
            CardPersonnel(
                personnel = Personnel(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
            CardPersonnel(
                personnel = Personnel(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
            CardPersonnel(
                personnel = Personnel(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
            CardPersonnel(
                personnel = Personnel(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
            CardPersonnel(
                personnel = Personnel(
                    photo = painterResource(
                        id = R.mipmap.face_photo
                    )
                )
            )
        }
    }
}







@Composable
fun CardPersonnel(
    personnel: Personnel,
){
    val router = LocalRouter.current
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10)
    ) {
        Row{
            Image(
                painter = personnel.photo,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.weight(2f)
                    .padding(start = 10.dp, top = 5.dp)
            ) {
                Text(
                    text = "${personnel.name} ${personnel.surname}",
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = """
                        |Контакти: ${personnel.phoneNumber}
                        |${personnel.email}
                        |Посада: ${personnel.position}
                        |Зарплата: ${personnel.salary}
                        |Стаж: ${personnel.experience}
                        |Найм: ${personnel.startJob}
                    """.trimMargin(),
                    style = TextStyle(lineHeight = 23.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = personnel.status,
                    fontWeight = FontWeight.Bold,
                )
            }
            Box(
                contentAlignment = Alignment.BottomEnd
            ){
                FloatingActionButton(
                    onClick = {
                        router.launch(AppRoute.Manager.Personal.EditPersonal)
                    },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}



data class Personnel(
    val id:Int = 1,
    val name: String = "name",
    val surname:String = "surname",
    val phoneNumber: String = "+380345345",
    val email:String = "2323423@23232.com",
    val position:Position = Position.MANAGER,
    val salary:String = "34234",
    val experience:String = "2 years",
    val startJob:String = "12/12/12",
    val photo:Painter,
    val status:String = "активний",
)




@Preview(showSystemUi = true)
@Composable
fun PreviewPersonnelScreen(){
    PersonnelScreen()
}