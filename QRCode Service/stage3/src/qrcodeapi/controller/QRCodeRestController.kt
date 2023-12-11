package qrcodeapi.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import qrcodeapi.service.ImageService

@RestController
class QRCodeRestController(private val imageService: ImageService) {
    private val permittedMediaTypes = listOf("png", "jpeg", "gif")
    private val unsupportedTypeErrorMessage: String = run {
        val imageTypesList = permittedMediaTypes
            .slice(0 until permittedMediaTypes.size - 1)
            .joinToString(", ") + " and ${permittedMediaTypes.last()}"
        "Only $imageTypesList image types are supported"
    }

    @GetMapping(path = ["/api/health"])
    fun ping(): ResponseEntity<Void> = ResponseEntity.ok().build()

    @GetMapping(path = ["/api/qrcode"])
    fun getImage(
        @RequestParam size: Int,
        @RequestParam type: String
    ): ResponseEntity<Any> {
        try {
            val bufferedImage = imageService.createImage(size)
            val mediaType = parseMediaType(type)
            return ResponseEntity
                .ok()
                .contentType(mediaType)
                .body(bufferedImage)
        } catch (e: RuntimeException) {
            return ResponseEntity
                .badRequest()
                .body(ApiErrorResponse(e.message))
        }
    }

    private fun parseMediaType(imageType: String): MediaType {
        require(imageType in permittedMediaTypes) {
            unsupportedTypeErrorMessage
        }
        return MediaType.parseMediaType("image/$imageType")
    }
}
