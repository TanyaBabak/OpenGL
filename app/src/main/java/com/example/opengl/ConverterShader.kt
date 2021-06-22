package com.example.opengl

import android.content.Context
import android.content.res.Resources
import android.util.Log
import java.io.IOException
import java.io.InputStream

fun Int.convertShader(context: Context): String {
    val stringBuilder = StringBuilder()
    var inputStream: InputStream? = null
    try {
        inputStream = context.resources.openRawResource(this)
        val reader = inputStream.bufferedReader()
        for (line in reader.readLines()) {
            stringBuilder.append(line)
            stringBuilder.append("\r\n")
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Log.e("Extension", "io Error")
    } catch (e: Resources.NotFoundException) {
        e.printStackTrace()
        Log.e("Extension", "resource Error")
    } finally {
        inputStream?.close()
    }
    return stringBuilder.toString()
}