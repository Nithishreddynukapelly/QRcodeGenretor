package com.example.qrcodegenerator

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newswave.DestinationScreen
import com.example.qrcodegenerator.ui.theme.QRCodeGeneratorTheme
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, id: String?, text: String?) {
    var qrText by remember { mutableStateOf("Your QR Text") }
    var qrCode by remember { mutableStateOf<Bitmap?>(null) }

    if (text != null) {
        val qrCodeStr = URLDecoder.decode(text, "UTF-8")
        qrCode = generateQRCode(qrCodeStr)
    }

    TopAppBar(
        title = {
            Text(text = "QR Code", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()

            }) {
                Icon(Icons.Filled.ArrowBack, null)

            }
        },

        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "QR Code Generator",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )


        qrCode?.let { qrBitmap ->
            Image(
                bitmap = qrBitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

private fun generateQRCode(text: String): Bitmap? {
    val hints = mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
    val qrCodeWriter = QRCodeWriter()

    return try {
        val bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300, hints)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.Black.hashCode() else Color.White.hashCode()
                )
            }
        }

        bmp
    } catch (e: Exception) {
        null
    }
}

