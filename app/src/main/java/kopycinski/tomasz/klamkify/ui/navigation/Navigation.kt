package kopycinski.tomasz.klamkify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kopycinski.tomasz.klamkify.ui.screens.categorylist.ActivityList
import kopycinski.tomasz.klamkify.ui.screens.activityform.ActivityForm
import kopycinski.tomasz.klamkify.ui.screens.activitydetails.ActivityDetails

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.CategoryList.route) {
        composable(
            route = Screen.CategoryForm.route,
            arguments = listOf(navArgument("category_id") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) {
            ActivityForm(
                onSuccessSave = { navController.navigateUp() },
                activityId = it.arguments?.getLong("category_id") ?: -1L
            )
        }
        composable(
            route = Screen.CategoryDetails.route,
            arguments = listOf(navArgument("category_id") { type = NavType.LongType })
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong("category_id")
            ActivityDetails(
                activityId = id ?: 0L,
                onBackPress = { navController.navigateUp() },
                onDelete = { navController.navigateUp() },
                onEdit = { navController.navigate(Screen.CategoryForm.createRoute(it)) }
            )
        }
        composable(Screen.CategoryList.route) {
            ActivityList(
                onFabClick = { navController.navigate(Screen.CategoryForm.route) },
                onItemClick = { navController.navigate(Screen.CategoryDetails.createRoute(it)) }
            )
        }
    }
}