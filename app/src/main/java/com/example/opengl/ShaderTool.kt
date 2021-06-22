package com.example.opengl

import android.content.Context
import android.opengl.GLES20.*

class ShaderTool {
    companion object {
        fun createShader(context: Context, type: Int, idResource: Int): Int {
            val idShader = glCreateShader(type)
            glShaderSource(idShader, idResource.convertShader(context))
            glCompileShader(idShader)
            val statusArray = intArrayOf(1)
            glGetShaderiv(idShader, GL_COMPILE_STATUS, statusArray, 0)
            if (statusArray[0] == 0) {
                glDeleteShader(idShader)
                return 0
            } else
                return idShader
        }

        fun createProgram(idVertex: Int, idFragment: Int): Int {
            val idProgram = glCreateProgram()
            if (idProgram == 0) {
                return 0
            }
            glAttachShader(idProgram, idVertex)
            glAttachShader(idProgram, idFragment)
            glLinkProgram(idProgram)
            val statusArray = intArrayOf(1)
            glGetProgramiv(idProgram, GL_COMPILE_STATUS, statusArray, 0)
            if (statusArray[0] == 0) {
                glDeleteProgram(idProgram)
                return 0
            } else
                return idProgram

        }

    }
}