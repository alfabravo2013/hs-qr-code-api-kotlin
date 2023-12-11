package qrcodeapi.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.util.*

@Service
class QrCodeServiceImpl : ImageService {
    private val minSize = 150
    private val maxSize = 350
    private val permittedLevels = listOf("L", "M", "Q", "H")
    private val unsupportedLevelErrorMessage =
        "Permitted error correction levels are ${permittedLevels.joinToString(", ")}"

    override fun createImage(contents: String, size: Int, correction: String): BufferedImage {
        require(contents.isNotBlank()) {
            "Contents cannot be null or blank"
        }

        require(size in minSize..maxSize) {
            "Image size must be between $minSize and $maxSize pixels"
        }

        val writer = QRCodeWriter()
        val correctionLevel = parseErrorCorrectionLevel(correction)
        val hints = mapOf(EncodeHintType.ERROR_CORRECTION to correctionLevel)
        try {
            val matrix = writer.encode(contents, BarcodeFormat.QR_CODE, size, size, hints)
            return MatrixToImageWriter.toBufferedImage(matrix)
        } catch (e: WriterException) {
            throw RuntimeException(e)
        }
    }

    private fun parseErrorCorrectionLevel(level: String): ErrorCorrectionLevel {
        val upperCasedLevel = level.uppercase(Locale.getDefault())
        require(upperCasedLevel in permittedLevels) { unsupportedLevelErrorMessage }

        return ErrorCorrectionLevel.valueOf(upperCasedLevel)
    }
}
