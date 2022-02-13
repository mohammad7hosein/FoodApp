package com.smh.foodapp.presentation.ui.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer() {

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val translateAnime = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Restart,
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnime.value, y = translateAnime.value)
    )

    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(brush: Brush) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .size(200.dp)
                    .background(brush)
            )
            Column(
                modifier = Modifier

                    .size(200.dp)
                    .weight(1f)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth()
                        .background(brush)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Spacer(
                    modifier = Modifier
                        .height(60.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth()
                        .background(brush)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(brush)
                    )
                    Spacer(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(brush)
                    )
                }
            }
        }
    }

}