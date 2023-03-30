package com.example.seefood

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seefood.screens.home.HomeScreen
import com.example.seefood.ui.theme.SeefoodTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun SeeFoodApp(){
   SeefoodTheme {
      val appState = rememberAppState()
      Scaffold(
         backgroundColor = Color(0xFF0C0C0C), // TODO: Добавить цвета в константы
         topBar = { SeeFoodTopBar() }
      ) { innerPadding ->
         NavHost(
            navController = appState.navController,
            startDestination = HOME_SCREEN,
            modifier = Modifier.padding(innerPadding)
         ){
            seeFoodGraph(appState)
         }
      }
   }
}

@Composable
fun rememberAppState(
   scaffoldState: ScaffoldState = rememberScaffoldState(),
   navController: NavHostController = rememberNavController(),
   resources: Resources = resources(),
   coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
   remember(scaffoldState, navController, resources, coroutineScope) {
      SeeFoodAppState(scaffoldState, navController, resources, coroutineScope)
   }


@Composable
@ReadOnlyComposable
fun resources(): Resources {
   LocalConfiguration.current
   return LocalContext.current.resources
}

@Composable
fun SeeFoodTopBar(){
   TopAppBar(
      modifier = Modifier
         .shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(
               bottomStart = 5.dp,
               bottomEnd = 5.dp
            )
         )
         .clip(
            RoundedCornerShape(
               bottomStart = 5.dp,
               bottomEnd = 5.dp
            )
         ),
      backgroundColor = Color.Black, // TODO: Добавить цвета как константы
   ) {
      Text(
         modifier = Modifier.fillMaxWidth(),
         text = "SEEFOOD",
         style = TextStyle( // TODO: Сделать все стили текста как константы
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
         )
      )
   }
}

fun NavGraphBuilder.seeFoodGraph(appState: SeeFoodAppState){
   composable(HOME_SCREEN) {
      HomeScreen(openScreen = { route -> appState.navigate(route)})
   }

   composable(CAMERA_SCREEN) {

   }

   composable(CATALOG_MENU_SCREEN) {

   }

   composable(SPLASH_SCREEN) {

   }

   composable(RESULT_SCREEN) {

   }

   composable(FAVORITES_SCREEN) {

   }
}