package com.example.opengl

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var glSurfaceView: GLSurfaceView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView(this)
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(Renderer(this))
        setContentView(glSurfaceView)
    }

    override fun onPause() {
        super.onPause()
            glSurfaceView.onPause()
    }

    override fun onResume() {
        super.onResume()
            glSurfaceView.onResume()
    }
}