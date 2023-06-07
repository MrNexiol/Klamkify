package kopycinski.tomasz.klamkify.ui.navigation

import androidx.navigation.NavHostController
import kopycinski.tomasz.klamkify.ui.navigation.DestinationArgs.ACTIVITY_ID_ARG
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITIES_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ACTIVITY_DETAILS_SCREEN
import kopycinski.tomasz.klamkify.ui.navigation.Screens.ADD_EDIT_ACTIVITY_SCREEN

private object Screens {
    const val ACTIVITY_DETAILS_SCREEN = "activity"
    const val ACTIVITIES_SCREEN = "activities"
    const val ADD_EDIT_ACTIVITY_SCREEN = "addEditActivity"
}

object DestinationArgs {
    const val ACTIVITY_ID_ARG = "activityId"
}

object Destinations {
    const val ACTIVITY_DETAILS_ROUTE = "$ACTIVITY_DETAILS_SCREEN/{$ACTIVITY_ID_ARG}"
    const val ACTIVITIES_ROUTE = ACTIVITIES_SCREEN
    const val ADD_EDIT_ACTIVITY_ROUTE = "$ADD_EDIT_ACTIVITY_SCREEN?$ACTIVITY_ID_ARG={$ACTIVITY_ID_ARG}"
}

class NavigationActions(private val navController: NavHostController) {
    fun navigateToAddEditActivity(activityId: Long?) {
        navController.navigate(
            ADD_EDIT_ACTIVITY_SCREEN.let {
                if (activityId != null) "$it?$ACTIVITY_ID_ARG=$activityId" else it
            }
        )
    }

    fun navigateToActivityDetails(activityId: Long) {
        navController.navigate(
            "$ACTIVITY_DETAILS_SCREEN/$activityId"
        )
    }
}