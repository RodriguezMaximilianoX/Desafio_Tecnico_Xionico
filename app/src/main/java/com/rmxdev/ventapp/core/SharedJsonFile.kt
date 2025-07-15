package com.rmxdev.ventapp.core

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

fun Context.shareJsonFile(filename: String, content: String, title: String) {
    val file = File(cacheDir, filename)
    file.writeText(content)

    val uri = FileProvider.getUriForFile(
        this,
        "$packageName.fileprovider",
        file
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "application/json"
        putExtra(Intent.EXTRA_STREAM, uri)
        putExtra(Intent.EXTRA_TITLE, title)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    startActivity(Intent.createChooser(intent, "Compartir $title"))
}