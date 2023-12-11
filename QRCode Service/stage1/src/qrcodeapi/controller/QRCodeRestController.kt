package qrcodeapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QRCodeRestController {

    @GetMapping(path = ["/api/health"])
    fun ping(): ResponseEntity<Void> = ResponseEntity.ok().build()

    @GetMapping(path = ["/api/qrcode"])
    fun getImage(): ResponseEntity<Void> = ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
}
