package pl.sylwekczmil.ma.domain.mongo.dashboard

import org.junit.Test
import pl.sylwekczmil.ma.infrastructure.security.UserAccessorTestImpl
import pl.sylwekczmil.ma.shared.OtherUserDataAccessException

class DashboardHandlerTest {

    val dashboardHandler: DashboardHandler = DashboardHandler(UserAccessorTestImpl())

    @Test(expected = OtherUserDataAccessException::class)
    fun `expect OtherUserDataAccessException when other user want to change dashboard`() {
        val dashboard = Dashboard()
        dashboard.username = "other"
        dashboardHandler.handleActions(dashboard)
    }

}