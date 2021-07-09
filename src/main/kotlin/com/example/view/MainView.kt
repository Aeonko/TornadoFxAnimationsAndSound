package com.example.view

import com.example.Styles
import com.example.controller.BubbleController
import javafx.animation.Interpolator
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.MouseDragEvent
import javafx.util.Duration
import tornadofx.*

class MainView : View("Bubbles & Ripples!!!") {
    private val bubbleController: BubbleController by inject()
    private var myLabel: Label by singleAssign()


    override val root = borderpane {
        setPrefSize(1000.0, 500.0)

        center {
            label {
                myLabel = this
                style{
                    fontSize = 21.px
                }
                bind(bubbleController.mText)
            }
        }
        bottom {
            label ("Click Anywhere..."){
                alignment = Pos.BOTTOM_CENTER
                paddingAll = 19.0
            }.apply {
                style{
                    opacity = 0.3
                    fontSize = 25.px
                }
            }
        }
        label(title) {
            addClass(Styles.heading)
        }
    }.apply {
        style{
            backgroundColor += c("#E0EEEE")
        }

        addEventFilter(MouseDragEvent.MOUSE_CLICKED){
            println("Clicked")
            bubbleController.addCircle(it, this)
            bubbleController.addRandomText()

            timeline {
                keyframe(duration = Duration.seconds(0.3)){
                    keyvalue(myLabel.styleProperty(),endValue = "-fx-font-size: 25", Interpolator.EASE_BOTH)
                }
                keyframe(duration = Duration.seconds(1.0)){
                    keyvalue(myLabel.translateXProperty(), (this@apply.width / 2)*0.5, Interpolator.EASE_BOTH)
                    keyvalue(myLabel.opacityProperty(),endValue = 0.4, Interpolator.EASE_BOTH)
                }
                isAutoReverse = true
                cycleCount = 2
            }
        }
    }
}
