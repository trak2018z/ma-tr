package pl.sylwekczmil.ma.backend.domain.dashboard.file

import net.coobird.thumbnailator.Thumbnailator
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsTemplate
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import pl.sylwekczmil.ma.backend.domain.dashboard.DashboardRepository
import java.awt.image.RenderedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletResponse


@Service
class FileService(val gridFsTemplate: GridFsTemplate,
                  val dashboardRepository: DashboardRepository) {

    fun add(dashboardId: String, file: MultipartFile): FileMetadata {
        val dashboard = dashboardRepository.findOne(dashboardId)
        val fileMetadata = FileMetadata(file.originalFilename, file.contentType)
        val image = ImageIO.read(file.inputStream)
        if (image != null) {
            fileMetadata.type = FileType.IMAGE
            val thumbnail = Thumbnailator.createThumbnail(image, 100, 100)
            fileMetadata.thumbNail = imgToBase64String(thumbnail, "png")
        }
        gridFsTemplate.store(file.inputStream, file.originalFilename, file.contentType, fileMetadata)
        dashboard.files.add(fileMetadata)
        dashboardRepository.save(dashboard)
        return fileMetadata
    }

    fun get(fileId: String, response: HttpServletResponse) {
        val file = gridFsTemplate.findOne(Query.query(Criteria.where("metadata._id").`is`(fileId)))
        file.writeTo(response.outputStream)
        response.contentType = file.contentType
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.filename + "\"")
    }

    fun imgToBase64String(img: RenderedImage, formatName: String): String {
        val os = ByteArrayOutputStream()
        ImageIO.write(img, formatName, os)
        return Base64.getEncoder().encodeToString(os.toByteArray())
    }

}