package com.minjalidze.anonimousvotes.ui.visuals.elements

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.runtime.Composable
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun rememberQrBitmapPainter(
    content: String
): Bitmap {
    val size = 512
    val bits = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size)
    return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
        for (x in 0 until size) {
            for (y in 0 until size) {
                it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
            }
        }
    }
}