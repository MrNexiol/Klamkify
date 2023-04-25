package kopycinski.tomasz.klamkify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kopycinski.tomasz.klamkify.ui.screens.categorylist.CategoryList
import kopycinski.tomasz.klamkify.ui.screens.categoryform.CategoryForm
import kopycinski.tomasz.klamkify.ui.screens.categorydetails.CategoryDetails

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "categories_screen") {
        composable(
            "new_category_screen?category_id={category_id}",
            arguments = listOf(navArgument("category_id") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) {
            CategoryForm(
                onSuccessSave = { navController.navigateUp() },
                categoryId = it.arguments?.getLong("category_id") ?: -1L
            )
        }
        composable(
            "sessions_screen/{category_id}",
            arguments = listOf(navArgument("category_id") { type = NavType.LongType })
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong("category_id")
            CategoryDetails(
                categoryId = id ?: 0L,
                onBackPress = { navController.navigateUp() },
                onDelete = { navController.navigateUp() },
                onEdit = { navController.navigate("new_category_screen?category_id=$it") }
            )
        }
        composable("categories_screen") {
            CategoryList(
                onFabClick = { navController.navigate("new_category_screen") },
                onItemClick = { navController.navigate("sessions_screen/$it") }
            )
        }
    }
}