package qrcodeapi.service

import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.image.BufferedImage

@Service
class StaticImageServiceImpl : ImageService {
    private val defaultColor = Color.WHITE
    private val minSize = 150
    private val maxSize = 350

    override fun createImage(size: Int): BufferedImage {
        require(size in minSize..maxSize) {
            "Image size must be between $minSize and $maxSize pixels"
        }

        val image = BufferedImage(size, size, BufferedImage.TYPE_INT_RGB)

        val graphics2D = image.createGraphics()

        graphics2D.color = defaultColor
        graphics2D.fillRect(0, 0, size, size)
        graphics2D.dispose()

        return image
    }
}
