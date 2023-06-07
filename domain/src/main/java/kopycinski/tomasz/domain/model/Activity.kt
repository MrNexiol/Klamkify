package kopycinski.tomasz.domain.model

import kopycinski.tomasz.data.local.entity.Activity as ActivityEntity

data class Activity(
    val name: String,
    val archived: Boolean,
    val activityId: Long
)

fun ActivityEntity.toModel(): Activity =
    Activity(
        name = this.name,
        archived = this.archived,
        activityId = this.activityId
    )
