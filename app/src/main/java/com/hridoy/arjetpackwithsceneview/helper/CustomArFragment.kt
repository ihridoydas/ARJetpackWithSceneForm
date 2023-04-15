package com.hridoy.arjetpackwithsceneview.helper

import com.google.ar.sceneform.ux.ArFragment


class CustomArFragment : ArFragment() {

    // this for remove fullscreen when click in dialog
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        /*no op */
    }
}