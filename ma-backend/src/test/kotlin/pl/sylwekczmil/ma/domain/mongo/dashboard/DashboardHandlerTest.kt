package pl.sylwekczmil.ma.domain.mongo.dashboard

import org.junit.Test
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.Dashboard
import pl.sylwekczmil.ma.backend.domain.mongo.dashboard.DashboardHandler
import pl.sylwekczmil.ma.backend.infrastructure.security.UserAccessorTestImpl
import pl.sylwekczmil.ma.backend.shared.OtherUserDataAccessException

class DashboardHandlerTest {

    val dashboardHandler: DashboardHandler = DashboardHandler(UserAccessorTestImpl())

    @Test(expected = OtherUserDataAccessException::class)
    fun `expect OtherUserDataAccessException when other user want to change dashboard`() {
        val dashboard = Dashboard()
        dashboard.username = "other"
        dashboardHandler.handleActions(dashboard)
    }

}