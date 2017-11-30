package pl.sylwekczmil.ma.web

import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.sylwekczmil.ma.domain.mongo.dashboard.file.FileMetadata
import pl.sylwekczmil.ma.domain.mongo.dashboard.file.FileService
import javax.servlet.http.HttpServletResponse

@RestController
@BasePathAwareController
class DashboardController(val fileService: FileService) {

    @GetMapping("files/{fileId}")
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