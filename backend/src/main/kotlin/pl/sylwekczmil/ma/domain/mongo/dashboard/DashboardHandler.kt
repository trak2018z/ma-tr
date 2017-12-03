package pl.sylwekczmil.ma.domain.mongo.dashboard

import org.springframework.data.rest.core.annotation.HandleAfterDelete
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import pl.sylwekczmil.ma.infrastructure.security.UserAccessor
import pl.sylwekczmil.ma.shared.OtherUserDataAccessException

@Component
@RepositoryEventHandler
class DashboardHandler(val userAccessor: UserAccessor) {

    @HandleBeforeCreate
    fun handleAdd(dashboard: Dashboard) {
        val user = userAccessor.getUser()
        dashboard.username = user.username
    }

    @HandleBeforeSave
    @HandleAfterDelete
    fun handleActions(dashboard: Dashboard) {
        val user = userAccessor.getUser()
        if (!dashboard.username.equals(user.username)) {
            throw OtherUserDataAccessException()
        }
    }
}