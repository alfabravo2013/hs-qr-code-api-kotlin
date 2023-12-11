package qrcodeapi.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage

@Service
class QrCodeServiceImpl : ImageService {
    private val minSize = 150
    private val maxSize = 350

    override fun createImage(contents: String, size: Int): BufferedImage {
        require(contents.isNotBlank()) {
            "Contents cannot be null or blank"
        }

        require(size in minSize..maxSize) {
            "Image size must be between $minSize and $maxSize pixels"
        }

        val writer = QRCodeWriter()
        try {
            val matrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size)
            return MatrixToImageWriter.toBufferedImage(matrix)
        } catch (e: WriterException) {
            throw RuntimeException(e)
        }
    }
}
