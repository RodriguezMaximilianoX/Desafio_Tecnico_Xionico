package com.rmxdev.ventapp.core

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

fun Context.shareFile(file: File, mimeType: String, packageName: String? = null) {
    val uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = mimeType
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        packageName?.let { setPackage(it) }
    }
    startActivity(Intent.createChooser(intent, "Compartir archivo"))
}