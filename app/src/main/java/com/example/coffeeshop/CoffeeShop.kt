package com.example.coffeeshop

import android.annotation.SuppressLint
import android.view.View
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.data.user.ManagerUser
import com.example.coffeeshop.scaffold.AppFloatingActionButton
import com.example.coffeeshop.scaffold.AppNavigationBar
import com.example.coffeeshop.scaffold.AppToolBar
import com.example.coffeeshop.screens.manager.components.RowInfo
import com.example.navigationmodule.rememberNavigation

@Composable
fun CoffeeShop(
    //viewModel: ThemeViewModel,
    darkTheme:Boolean,
    onThemeUpdate:()->Unit,
){
    val navigation = rememberNavigation(AppRoute.StartUI.GeneralPageScreen)
    val router = navigation.router
    val navigationState = navigation.navigationState

    Scaffold(
        topBar = {
            if(navigationState.currentRoute != AppRoute.StartUI.GeneralPageScreen){
                AppToolBar(
                    navigationState = navigationState,
                    router = router,
                    darkTheme = darkTheme,
                    onThemeUpdate = onThemeUpdate,
                    //viewModel = viewModel,
                )
            }
        },
        floatingActionButton = {
            if(navigationState.currentRoute in listOf(
                    AppRoute.Menu.Menu,
                    AppRoute.Manager.Personal.RevisionPersonal,
                    AppRoute.Administrator.Purchase.RevisionPurchase) &&
                ManagerUser.currentUser != null
            ) {
                AppFloatingActionButton(
                    navigationState = navigationState,
                    router = router,
                )
            }
        },
        bottomBar = {
            if(navigationState.currentRoute != AppRoute.Administrator.Purchase.ShoppingCart){
                AppNavigationBar(
                    navigationState = navigationState,
                    router = router,
                )
            }
        },
    ) { paddingValues ->
//        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
//            AppToolBar(
//                navigationState = navigationState,
//                router = router,
//                darkTheme = darkTheme,
//                onThemeUpdate = onThemeUpdate,
//                //viewModel = viewModel,
//            )
//
//            ThemeSwitcher(
//                darkTheme = darkTheme,
//                size =  50.dp,
//                padding = 5.dp,
//                onClick = {onThemeUpdate.invoke()}
//            )
//        }
        AppNavigationHost(
            navigation = navigation,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun ThemeSwitcher(
    darkTheme: Boolean,
    size: Dp = 150.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 10.dp,
    borderWidth:Dp = 1.dp,
    parentShapes: RoundedCornerShape = CircleShape,
    toggleShape: RoundedCornerShape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: ()-> Unit,
){
    val offset by animateDpAsState(
        targetValue = if(darkTheme) 0.dp else size,
        animationSpec = animationSpec,
    )

    Box(
        modifier = Modifier
            .width(size * 2)
            .height(size)
            .clip(shape = parentShapes)
            .clickable { onClick.invoke() }
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ){
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(padding)
                .clip(toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ){}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    shape = parentShapes,
                )
        ){
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(R.drawable.dark_mode),
                    contentDescription = "Theme Icon",
                    tint = Color.Unspecified,
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(R.drawable.light_1),
                    contentDescription = "Theme Icon",
                    tint = Color.Unspecified,
                )
            }
        }
    }

}