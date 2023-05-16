package kopycinski.tomasz.klamkify.ui.navigation

sealed class Screen(val route: String) {
    object CategoryDetails : Screen("category_details_screen/{category_id}") {
        fun createRoute(categoryId: Long): String = "category_details_screen/$categoryId"
    }
    object CategoryForm : Screen("category_form_screen?category_id={category_id}") {
        fun createRoute(categoryId: Long): String = "category_form_screen?category_id=$categoryId"
    }
    object CategoryList : Screen("category_list_screen")
}