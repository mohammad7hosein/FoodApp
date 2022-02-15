package com.smh.foodapp.presentation.ui.RecipeDetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import coil.compose.rememberImagePainter

@ExperimentalMotionApi
@Composable
fun MotionLayoutHeader(
    isDarkTheme : Boolean,
    title: String,
    image: String,
    progress: Float,
    scrollableBody: @Composable () -> Unit
) {
    val startColor = if (isDarkTheme) "#FFFFFF" else "#202020"
    val endColor = if (isDarkTheme) "#202020" else "#FFFFFF"

    MotionLayout(
        start = startConstraintSet(),
        end = endConstraintSet(),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val painter = rememberImagePainter(
            data = image,
            builder = { crossfade(300) }
        )
        Image(
            painter = painter,
            contentDescription = "poster",
            modifier = Modifier
                .layoutId("poster")
                .background(MaterialTheme.colors.background),
            contentScale = ContentScale.FillWidth,
            alpha = 1f - progress
        )
        Text(
            text = title,
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight(),
            color = MaterialTheme.colors.onBackground,
            fontSize = 24.sp,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
        )
        Box(
            Modifier
                .layoutId("content")
        ) {
            scrollableBody()
        }
    }
}

@Composable
private fun JsonConstraintSetStart(color: String) = ConstraintSet(
    """ {
	poster: { 
		width: "spread",
        height: 300,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['poster', 'bottom', 16],
		start: ['parent', 'start', 16],
		custom: {
			textColor: "$color", 
			textSize: 28
		}
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['title', 'bottom', 16],
	}
} """
)

@Composable
private fun JsonConstraintSetEnd(color: String) = ConstraintSet(
    """ {
	poster: { 
		width: "spread",
		height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0], 
		bottom: ['poster', 'bottom', 0],
		custom: {
			textColor: "$color",
			textSize: 20
        }
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['poster', 'bottom', 0],
	}
                  
} """
)

// Constraint Sets defined by using Kotlin DSL option
private fun startConstraintSet() = ConstraintSet {
    val poster = createRefFor("poster")
    val title = createRefFor("title")
    val content = createRefFor("content")

    constrain(poster) {
        width = Dimension.fillToConstraints
        height = Dimension.value(300.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start, 12.dp)
        end.linkTo(parent.end, 12.dp)
        top.linkTo(poster.bottom, 16.dp)
    }

    constrain(content) {
        width = Dimension.fillToConstraints
        top.linkTo(title.bottom, 8.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

private fun endConstraintSet() = ConstraintSet {
    val poster = createRefFor("poster")
    val title = createRefFor("title")
    val content = createRefFor("content")

    constrain(poster) {
        width = Dimension.fillToConstraints
        height = Dimension.value(56.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(parent.top)
    }

    constrain(title) {
        start.linkTo(parent.start)
        top.linkTo(parent.top, 8.dp)
        end.linkTo(parent.end)
        bottom.linkTo(poster.bottom)
    }

    constrain(content) {
        width = Dimension.fillToConstraints
        top.linkTo(poster.bottom, 8.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}