package kopycinski.tomasz.klamkify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kopycinski.tomasz.klamkify.ui.screens.categories.CategoriesScreen
import kopycinski.tomasz.klamkify.ui.screens.categorycreate.CategoryCreateScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "categories_screen") {
        composable("categories_screen") {
            CategoriesScreen(
                onCreateClick = { navController.navigate("new_category_screen") }
            )
        }
        composable("new_category_screen") {
            CategoryCreateScreen(
                onSuccessSave = { navController.navigateUp() }
            )
        }
    }
}