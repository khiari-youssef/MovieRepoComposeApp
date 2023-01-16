package com.mediaapps.movierepo.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


fun Modifier.shimmerEffect(
    isEnabled : Boolean= true,
    shape : Shape = RoundedCornerShape(0.dp)
) = composed {
    return@composed if (isEnabled){
        val layoutSizeState = remember {
            mutableStateOf(IntSize.Zero)
        }
        val transition = rememberInfiniteTransition()

        val transitionXOffsetState = transition.animateValue(
            initialValue = -2*layoutSizeState.value.width,
            targetValue = 2*layoutSizeState.value.width,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Restart
            ),
            typeConverter = Int.Companion.VectorConverter
        )

        val shimmerBackground = Color(0xFFC2C2C2)
        val shimmerGradientColor = Color(0xFFA5A5A5)
        val shimmerBrush = Brush.linearGradient(
            colors = listOf(
                shimmerBackground,
                shimmerGradientColor,
                shimmerGradientColor,
                shimmerBackground
            ),
            start = Offset(transitionXOffsetState.value.toFloat(),0f),
            end = Offset(transitionXOffsetState.value+layoutSizeState.value.width.toFloat(),layoutSizeState.value.height.toFloat())
        )
         background(
             brush =shimmerBrush,
             shape = shape
         )
       .onGloballyPositioned {
           layoutSizeState.value = it.size
       }
      .drawWithContent {
                 if (isEnabled.not()){
                     drawContent()
                 }
       }
    } else this
}

