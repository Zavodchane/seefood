package com.example.seefood

import android.content.Context
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.seefood.screens.favorites.FavoritesScreen
import com.example.seefood.screens.camera.CameraScreen
import com.example.seefood.screens.catalog.CatalogScreen
import com.example.seefood.screens.catalogs_menu.CatalogsMenuScreen
import com.example.seefood.screens.home.HomeScreen
import com.example.seefood.ui.theme.Background
import com.example.seefood.ui.theme.SeefoodTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun SeeFoodApp(){
   SeefoodTheme {
      val appState = rememberAppState()
      Scaffold(
         backgroundColor = Background, // TODO: Добавить цвета в константы
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
   context: Context = context(),
   coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
   remember(scaffoldState, navController, resources, context, coroutineScope) {
      SeeFoodAppState(scaffoldState, navController, resources, context, coroutineScope)
   }


@Composable
@ReadOnlyComposable
fun resources(): Resources {
   LocalConfiguration.current
   return LocalContext.current.resources
}

@Composable
@ReadOnlyComposable
fun context(): Context { return LocalContext.current }

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
      HomeScreen(openScreen = { route -> appState.navigate(route) })
   }

   composable(CAMERA_SCREEN) {
      CameraScreen(appState = appState)
   }

   composable(CATALOG_MENU_SCREEN) {
      CatalogsMenuScreen(openScreen = { route -> appState.navigate(route) })
   }

   composable(SPLASH_SCREEN) {

   }

   composable(RESULT_SCREEN) {

   }

   composable(
      "$CATALOG_SCREEN$CATALOG_SCREEN_ARGS",
      arguments = listOf(navArgument(name = "catalogName"){ type = NavType.StringType })
   ) {
      val catalogName = it.arguments?.getString("catalogName").toString()
      CatalogScreen(catalogName = catalogName)
   }

   composable(FAVORITES_SCREEN) {
      FavoritesScreen()
   }
}