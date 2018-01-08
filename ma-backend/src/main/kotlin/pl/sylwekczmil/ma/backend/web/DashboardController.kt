package pl.sylwekczmil.ma.backend.web

import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.sylwekczmil.ma.backend.domain.dashboard.Dashboard
import pl.sylwekczmil.ma.backend.domain.dashboard.DashboardRepository
import pl.sylwekczmil.ma.backend.domain.dashboard.file.FileMetadata
import pl.sylwekczmil.ma.backend.domain.dashboard.file.FileService
import javax.servlet.http.HttpServletResponse

@RestController
@BasePathAwareController
class DashboardController(val fileService: FileService, val dashboardRepository: DashboardRepository) {


    @GetMapping("/dashboards/findByUsername/{username}")
    fun getDashboard(@PathVariable username: String): Dashboard {
        var dashboard = dashboardRepository.findByUsername(username)
        if (dashboard == null) {
            dashboard = dashboardRepository.save(Dashboard(username))
        }
        return dashboard
    }

    @GetMapping("/files/{fileId}")
    fun getFile(@PathVariable fileId: String, response: HttpServletResponse) {
        fileService.get(fileId, response)
    }

    @PostMapping("/dashboards/{dashboardId}/files")
    fun handleFileUpload(
            @RequestParam("file") file: MultipartFile,
            @PathVariable dashboardId: String): FileMetadata {
        return fileService.add(dashboardId, file)
    }
}