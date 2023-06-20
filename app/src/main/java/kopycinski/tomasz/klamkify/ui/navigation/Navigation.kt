package kopycinski.tomasz.klamkify.ui.navigation

import androidx.navigation.NavHostController
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITIES_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_DETAILS_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_FORM_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.CATEGORY_FORM_SCREEN

private object Screens {
    const val ACTIVITY_DETAILS_SCREEN = "activity"
    const val ACTIVITIES_SCREEN = "activities"
    const val ACTIVITY_FORM_SCREEN = "activityForm"
    const val CATEGORY_FORM_SCREEN = "categoryForm"
}

object DestinationArgs {
    const val ACTIVITY_ID_ARG = "activityId"
}

object Destinations {
    const val ACTIVITY_DETAILS_ROUTE = "$ACTIVITY_DETAILS_SCREEN/{$ACTIVITY_ID_ARG}"
    const val ACTIVITIES_ROUTE = ACTIVITIES_SCREEN
    const val ACTIVITY_FORM_ROUTE = "$ACTIVITY_FORM_SCREEN?$ACTIVITY_ID_ARG={$ACTIVITY_ID_ARG}"
    const val CATEGORY_FORM_ROUTE = CATEGORY_FORM_SCREEN
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateToActivityForm(activityId: Long?) {
        navController.navigate(
            ACTIVITY_FORM_SCREEN.let {
                if (activityId != null) "$it?$ACTIVITY_ID_ARG=$activityId" else it
            }
        )
    }

    fun navigateToActivityDetails(activityId: Long) {
        navController.navigate(
            "$ACTIVITY_DETAILS_SCREEN/$activityId"
        )
    }

    fun navigateToCategoryForm() {
        navController.navigate(
            CATEGORY_FORM_SCREEN
        )
    }
}