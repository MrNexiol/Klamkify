package kopycinski.tomasz.domain.usecase

import kopycinski.tomasz.data.local.repository.CategoryRepository
import kopycinski.tomasz.domain.model.Activity
import kopycinski.tomasz.domain.model.Category
import kopycinski.tomasz.domain.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategoriesWithActivitiesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
){
    operator fun invoke(): Flow<Map<Category, List<Activity>>>
    {
        val newMap = mutableMapOf<Category, List<Activity>>()

        return categoryRepository.getCategoriesWithActivities().map { map ->
            map.map {
                newMap.put(it.key.toModel(), it.value.map { activity -> activity.toModel() })
            }
            newMap
        }
    }
}
