package com.example.controller

import com.example.view.MainView
import javafx.animation.Interpolator
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Point2D
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.media.AudioClip
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration
import tornadofx.*


class BubbleController: Controller() {
    private var circe = Circle()
    var mText  = SimpleStringProperty("")
    private var audioClip = AudioClip(MainView::class.java.getResource("/celestial-sound.wav").toExternalForm())


    private val colorList = listOf<Color>(
        Color.DARKBLUE,
        Color.BLUE,
        Color.ANTIQUEWHITE,
        Color.AQUA,
        Color.AZURE,
        Color.BEIGE,
        Color.BISQUE,
        Color.ALICEBLUE,
        Color.AQUAMARINE,
        Color.CORAL,
        Color.BROWN,
        Color.BLUEVIOLET,
        Color.CADETBLUE,
        Color.MOCCASIN,
        Color.DARKKHAKI,
        Color.BLANCHEDALMOND
    )

    private val textList = listOf<String>(
        "Ranodm",
        "get",
        "set",
        "Random text",
    "Texting"
    )


    fun addCircle(it: MouseEvent, root: Node) {
        if(audioClip.isPlaying){
            audioClip.volumeProperty().value = 0.3
            audioClip.panProperty().value = 1.0
        }else{
            audioClip.volumeProperty().value = 0.8
            audioClip.play()
        }


        val mousePoint: Point2D = root.sceneToLocal(it.sceneX,it.sceneY)

        circe = Circle(mousePoint.x,mousePoint.y,14.5, Color.ORANGERED).apply {
            animateFill(Duration.seconds(1.9),randomColor(),Color.TRANSPARENT){}
        }
        timeline {
            keyframe(Duration.seconds(3.0)){
                keyvalue(circe.radiusProperty(),endValue = 200, Interpolator.EASE_BOTH)
                keyvalue(circe.centerYProperty(),endValue = 100, Interpolator.EASE_BOTH)
                keyvalue(circe.centerXProperty(),endValue = 100, Interpolator.EASE_IN)
            }
        }
        root.getChildList()!!.add(circe)
    }




    private fun randomColor(): Color{
        val listSize: Int = colorList.size
        val randomNumber: Int = (0 until listSize).shuffled().last()

        return colorList[randomNumber]
    }

    fun addRandomText(){
        val listSize: Int = textList.size
        val  randomNumber: Int = (0 until listSize).shuffled().last()
        mText.set(textList[randomNumber])
    }
}