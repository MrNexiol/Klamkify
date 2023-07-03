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
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.CATEGORY_ID_ARG
import kopycinski.tomasz.klamkify.ui.screens.activities.ActivityListScreen
import kopycinski.tomasz.klamkify.ui.screens.activityform.ActivityForm
import kopycinski.tomasz.klamkify.ui.screens.activitydetails.ActivityDetails
import kopycinski.tomasz.klamkify.ui.screens.activitytimer.ActivityTimerScreen
import kopycinski.tomasz.klamkify.ui.screens.categoryform.CategoryFormScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.ACTIVITY_LIST_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
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
                onDelete = { navController.navigateUp() },
                onEdit = { navActions.navigateToActivityForm(backStack.arguments?.getLong(ACTIVITY_ID_ARG)!!) }
            )
        }
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
                navigateBack = { navController.popBackStack() },
                onAddCategory = { navActions.navigateToCategoryForm() }
            )
        }
        composable(
            route = Destinations.ACTIVITY_LIST_ROUTE
        ) {
            ActivityListScreen(
                onFabClick = { navActions.navigateToActivityForm() },
                onActivityClick = { navActions.navigateToActivityTimer(it) },
                onActivityLongClick = { navActions.navigateToActivityForm(it) },
                onCategoryLongClick = { navActions.navigateToCategoryForm(it) }
            )
        }
        composable(
            route = Destinations.ACTIVITY_TIMER_ROUTE,
            arguments = listOf(
                navArgument(ACTIVITY_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            ActivityTimerScreen(
                onDetailsClick = { navActions.navigateToActivityDetails(it) }
            )
        }
        composable(
            route = Destinations.CATEGORY_FORM_ROUTE,
            arguments = listOf(
                navArgument(CATEGORY_ID_ARG) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            CategoryFormScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}