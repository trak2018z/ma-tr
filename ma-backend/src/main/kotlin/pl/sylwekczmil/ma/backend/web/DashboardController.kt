package pl.sylwekczmil.ma.backend.web

import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.sylwekczmil.ma.backend.domain.dashboard.DashboardRepository
import pl.sylwekczmil.ma.backend.domain.dashboard.file.FileMetadata
import pl.sylwekczmil.ma.backend.domain.dashboard.file.FileService
import javax.servlet.http.HttpServletResponse

@RestController
@BasePathAwareController
class DashboardController(val fileService: FileService, val dashboardRepository: DashboardRepository) {

    @GetMapping("/files/{fileUrl}/{fileName}")
    fun getFile(@PathVariable fileUrl: String, @PathVariable fileName: String, response: HttpServletResponse) {
        fileService.get(fileUrl, fileName, response)
    }

    @DeleteMapping("/dashboards/{dashboardId}/files/{fileUrl}")
    fun deleteFile(@PathVariable dashboardId: String, @PathVariable fileUrl: String) {
        fileService.delete(dashboardId, fileUrl)
    }

    @PostMapping("/dashboards/{dashboardId}/files")
    fun handleFileUpload(
            @RequestParam("file") file: MultipartFile,
            @PathVariable dashboardId: String): FileMetadata {
        return fileService.add(dashboardId, file)
    }
}