package kopycinski.tomasz.klamkify.ui.navigation

import androidx.navigation.NavHostController
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.CATEGORY_ID_ARG
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_LIST_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_DETAILS_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_FORM_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_TIMER_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.CATEGORY_FORM_SCREEN

private object Screens {
    const val ACTIVITY_DETAILS_SCREEN = "activity"
    const val ACTIVITY_LIST_SCREEN = "activityList"
    const val ACTIVITY_FORM_SCREEN = "activityForm"
    const val ACTIVITY_TIMER_SCREEN = "activityTimer"
    const val CATEGORY_FORM_SCREEN = "categoryForm"
}

object DestinationArgs {
    const val ACTIVITY_ID_ARG = "activityId"
    const val CATEGORY_ID_ARG = "categoryId"
}

object Destinations {
    const val ACTIVITY_DETAILS_ROUTE = "$ACTIVITY_DETAILS_SCREEN/{$ACTIVITY_ID_ARG}"
    const val ACTIVITY_LIST_ROUTE = ACTIVITY_LIST_SCREEN
    const val ACTIVITY_FORM_ROUTE = "$ACTIVITY_FORM_SCREEN?$ACTIVITY_ID_ARG={$ACTIVITY_ID_ARG}"
    const val ACTIVITY_TIMER_ROUTE = "$ACTIVITY_TIMER_SCREEN/{$ACTIVITY_ID_ARG}"
    const val CATEGORY_FORM_ROUTE = "$CATEGORY_FORM_SCREEN?$CATEGORY_ID_ARG={$CATEGORY_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToActivityDetails(activityId: Long) {
        navController.navigate(
            "$ACTIVITY_DETAILS_SCREEN/$activityId"
        )
    }

    fun navigateToActivityForm(activityId: Long? = null) {
        navController.navigate(
            ACTIVITY_FORM_SCREEN.let {
                if (activityId != null) "$it?$ACTIVITY_ID_ARG=$activityId" else it
            }
        )
    }

    fun navigateToActivityTimer(activityId: Long) {
        navController.navigate(
            "$ACTIVITY_TIMER_SCREEN/$activityId"
        )
    }

    fun navigateToCategoryForm(categoryId: Long? = null) {
        navController.navigate(
            CATEGORY_FORM_SCREEN.let {
                if (categoryId != null) "$it?$CATEGORY_ID_ARG=$categoryId" else it
            }
        )
    }
}