package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

val Icons.Filled.Stop: ImageVector by lazy {
    materialIcon(name = "Filled.Stop") {
        materialPath {
            moveTo(6.0f, 5.0f)
            verticalLineToRelative(14F)
            horizontalLineToRelative(14F)
            verticalLineTo(5F)
            close()
        }
    }
}