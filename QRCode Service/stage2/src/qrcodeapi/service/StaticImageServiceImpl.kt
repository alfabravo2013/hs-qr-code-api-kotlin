package qrcodeapi.service

import org.springframework.stereotype.Service
import java.awt.Color
import java.awt.image.BufferedImage

@Service
class StaticImageServiceImpl : ImageService {
    private val defaultColor = Color.WHITE
    private val defaultSize = 250

    override fun createImage(): BufferedImage {
        val image = BufferedImage(
            defaultSize,
            defaultSize,
            BufferedImage.TYPE_INT_RGB
        )

        val graphics2D = image.createGraphics()

        graphics2D.color = defaultColor
        graphics2D.fillRect(0, 0, defaultSize, defaultSize)
        graphics2D.dispose()

        return image
    }
}
