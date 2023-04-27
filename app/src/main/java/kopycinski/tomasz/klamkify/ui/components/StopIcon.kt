package kopycinski.tomasz.klamkify.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

@Suppress("UnusedReceiverParameter")
val Icons.Filled.Stop: ImageVector
    get() {
        if (_stop != null) {
            return _stop!!
        }
        _stop = materialIcon(name = "Filled.Stop") {
            materialPath {
                moveTo(6.0f, 5.0f)
                verticalLineToRelative(14F)
                horizontalLineToRelative(14F)
                verticalLineTo(5F)
                close()
            }
        }
        return _stop!!
    }

private var _stop: ImageVector? = null