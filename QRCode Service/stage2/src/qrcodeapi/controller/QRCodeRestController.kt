package qrcodeapi.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import qrcodeapi.service.ImageService
import java.awt.image.BufferedImage

@RestController
class QRCodeRestController(private val imageService: ImageService) {
    private val defaultImageType = MediaType.IMAGE_PNG

    @GetMapping(path = ["/api/health"])
    fun ping(): ResponseEntity<Void> = ResponseEntity.ok().build()

    @GetMapping(path = ["/api/qrcode"])
    fun getImage(): ResponseEntity<BufferedImage> {
        val bufferedImage = imageService.createImage()
        return ResponseEntity
            .ok()
            .contentType(defaultImageType)
            .body(bufferedImage)
    }
}
