package com.rmxdev.ventapp.core

import android.content.Context
import java.io.File

fun Context.exportJsonToFile(filename: String, content: String): File {
    val file = File(cacheDir, filename)
    file.writeText(content)
    return file
}