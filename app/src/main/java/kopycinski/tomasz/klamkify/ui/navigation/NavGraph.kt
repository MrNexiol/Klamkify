package kopycinski.tomasz.klamkify.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kopycinski.tomasz.klamkify.ui.screens.activities.ActivitiesScreen
import kopycinski.tomasz.klamkify.ui.screens.activityform.ActivityForm
import kopycinski.tomasz.klamkify.ui.screens.activitydetails.ActivityDetails
import kopycinski.tomasz.klamkify.ui.screens.categoryform.CategoryFormScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.ACTIVITIES_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Destinations.ACTIVITY_FORM_ROUTE,
            arguments = listOf(
                navArgument(ACTIVITY_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            ActivityForm(
                onSuccessSave = { navController.popBackStack() },
                onAddCategory = { navActions.navigateToCategoryForm() }
            )
        }
        composable(
            route = Destinations.ACTIVITY_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(ACTIVITY_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStack ->
            ActivityDetails(
                activityId = backStack.arguments?.getLong(ACTIVITY_ID_ARG)!!,
                onBackPress = { navController.navigateUp() },
                onDelete = { navController.navigateUp() },
                onEdit = { navActions.navigateToActivityForm(it) }
            )
        }
        composable(
            route = Destinations.ACTIVITIES_ROUTE
        ) {
            ActivitiesScreen(
                onFabClick = { navActions.navigateToActivityForm() },
                onActivityClick = { navActions.navigateToActivityDetails(it) },
                onActivityLongClick = { navActions.navigateToActivityForm(it) }
            )
        }
        composable(
            route = Destinations.CATEGORY_FORM_ROUTE
        ) {
            CategoryFormScreen()
        }
    }
}