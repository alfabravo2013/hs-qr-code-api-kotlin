package qrcodeapi.service

import java.awt.image.BufferedImage

interface ImageService {
    fun createImage(size: Int): BufferedImage
}
