package com.example.opengl

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Renderer(val context: Context) : GLSurfaceView.Renderer {

    private lateinit var vertexArray: FloatArray
    private lateinit var floatBuffer: FloatBuffer

    init {
        prepareSurface()
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        glClearColor(0f, 0f, 0f, 1f);
        val idVertex = ShaderTool.createShader(context, GL_VERTEX_SHADER, R.raw.vertex_shader)
        val idFragment = ShaderTool.createShader(context, GL_FRAGMENT_SHADER, R.raw.fragment_shader)
        val idProgram = ShaderTool.createProgram(idVertex, idFragment)
        glUseProgram(idProgram)
        bindData(idProgram)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        glClear(GL_COLOR_BUFFER_BIT)
        glLineWidth(5f)
        glDrawArrays(GL_LINES, 0, 6);
    }

    private fun prepareSurface() {
        vertexArray = floatArrayOf(
            // линия 1
            -0.4f, 0.6f, 1.0f, 0.0f, 0.0f,
            0.4f, 0.6f, 0.0f, 1.0f, 0.0f,

            // линия 2
            0.6f, 0.4f, 0.0f, 0.0f, 1.0f,
            0.6f, -0.4f, 1.0f, 1.0f, 1.0f,

            // линия 3
            0.4f, -0.6f, 1.0f, 1.0f, 0.0f,
            -0.4f, -0.6f, 1.0f, 0.0f, 1.0f,
        )
        floatBuffer = ByteBuffer.allocateDirect(vertexArray.size * 4).order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        floatBuffer.put(vertexArray)
    }

    private fun bindData(idProgram: Int) {
        val glPositionLocation = glGetAttribLocation(idProgram, "a_Position")
        floatBuffer.position(0)
        glVertexAttribPointer(glPositionLocation, 2, GL_FLOAT, false, 20, floatBuffer)
        glEnableVertexAttribArray(glPositionLocation)

        val aColorLocation = glGetAttribLocation(idProgram, "a_Color")
        floatBuffer.position(2)
        glVertexAttribPointer(aColorLocation, 3, GL_FLOAT, false, 20, floatBuffer)
        glEnableVertexAttribArray(aColorLocation)



    }
}