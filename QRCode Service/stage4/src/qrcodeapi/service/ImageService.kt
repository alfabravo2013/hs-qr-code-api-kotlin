package qrcodeapi.service

import java.awt.image.BufferedImage

interface ImageService {
    fun createImage(contents: String, size: Int): BufferedImage
}
