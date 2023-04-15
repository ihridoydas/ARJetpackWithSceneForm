package com.hridoy.arjetpackwithsceneview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.hridoy.arjetpackwithsceneview.databinding.LayoutArCoreBinding
import com.hridoy.arjetpackwithsceneview.ui.theme.ARJetpackWithSceneViewTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isSplash = true
        installSplashScreen().apply {
            setKeepOnScreenCondition { isSplash }
        }
        setContent {
            ARJetpackWithSceneViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    isSplash = false
                    ARCameraScene()
                }
            }
        }
    }
}

@Composable
fun ARCameraScene() {
    AndroidViewBinding(
        modifier = Modifier,
        factory = LayoutArCoreBinding::inflate,
        update = {
            val arFragment = fragmentContainerView.getFragment<ArFragment>()
            arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
                    arFragment.arSceneView.scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
                        addChild(TransformableNode(arFragment.transformationSystem).apply {
//                            renderable = model
//                            actionTapArPlaneListener(renderableInstance.animate(true))
                        })
                    })
            }
        }
    )
}



