package com.jetpack.stagestepprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetpack.stagestepprogress.ui.theme.StageStepProgressTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentStep = remember { mutableStateOf(2) }
            StageStepProgressTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Stage Step Progress",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                StepProgressBar(
                                    modifier = Modifier.fillMaxWidth(),
                                    numberOfSteps = 5,
                                    currentStep = currentStep.value
                                )

                                Spacer(modifier = Modifier.height(50.dp))

                                Button(
                                    onClick = { currentStep.value++ }
                                ) {
                                    Text(text = "Click Me!")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Step(
    modifier: Modifier = Modifier,
    isComplete: Boolean,
    isCurrent: Boolean
) {
    val color = if (isComplete || isCurrent) Color.Red else Color.LightGray
    val innerCircleColor = if (isComplete) Color.Red else Color.LightGray

    Box(
        modifier = modifier
    ) {
        Divider(
            modifier = Modifier.align(
                Alignment.CenterStart
            ),
            color = color,
            thickness = 5.dp
        )

        //Stage step circle
        Canvas(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterEnd)
                .border(
                    shape = CircleShape,
                    width = 5.dp,
                    color = color
                ),
            onDraw = {
                drawCircle(
                    color = innerCircleColor
                )
            }
        )
    }
}

@Composable
fun StepProgressBar(
    modifier: Modifier = Modifier,
    numberOfSteps: Int,
    currentStep: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0..numberOfSteps) {
            Step(
                isComplete = step < currentStep,
                isCurrent = step == currentStep,
                modifier = Modifier.weight(1f)
            )
        }
    }
}













