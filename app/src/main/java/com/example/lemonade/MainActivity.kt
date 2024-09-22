package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        } }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() { var step by remember { mutableStateOf(1) } // reemebr the steps taken, this is step 1

    var squeezes by remember { mutableStateOf(0) } // 0 squeezes at this point

    Scaffold( // scaffolding that displays the title
        topBar = {
            CenterAlignedTopAppBar(
                title = {

                    Text(
                        text = "Lemonade App"
                    ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(

                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) }
    ) { innerPadding -> Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.tertiaryContainer), color = MaterialTheme.colorScheme.background
    ) { when (step) { // rememebrs what step
        1 -> { LemonTextAndImage(
            textLabelResourceId = R.string.lemon_select,

            drawableResourceId = R.drawable.lemon_tree,
            contentDescriptionResourceId = R.string.lemon_tree_content_description,
            onImageClick = {
                step = 2
                squeezes = (2..4).random() // squeezes between 2 and 4
            }
        ) }
        2 -> { LemonTextAndImage(
            textLabelResourceId = R.string.lemon_squeeze,
            drawableResourceId = R.drawable.lemon_squeeze,

            contentDescriptionResourceId = R.string.lemon_content_description,
            onImageClick = {
                squeezes-- // removes the squeezes
                if (squeezes == 0) {

                    step = 3 // goes to step 3 when all the random squeezes deplete
                } }
        ) }
        3 -> { LemonTextAndImage( // drink lemonade!

            textLabelResourceId = R.string.lemon_drink,
            drawableResourceId = R.drawable.lemon_drink,
            contentDescriptionResourceId = R.string.lemonade_content_description,
            onImageClick = {
                step = 4
            }
        ) }
        4 -> { LemonTextAndImage( // restart the process

            textLabelResourceId = R.string.lemon_empty_glass,
            drawableResourceId = R.drawable.lemon_restart,
            contentDescriptionResourceId = R.string.empty_glass_content_description,
            onImageClick = {
                step = 1
            } ) }
    } }
    }
}

@Composable
fun LemonTextAndImage( //formatting for the images
    textLabelResourceId: Int, //paramaters


    drawableResourceId: Int,
    contentDescriptionResourceId: Int,

    onImageClick: () -> Unit,
    modifier: Modifier = Modifier ) {

    Box( modifier = modifier ) { Column( // position child componments
        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center,

        modifier = Modifier.fillMaxSize()
    ) { Button(

        onClick = onImageClick,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) { Image(

        painter = painterResource(drawableResourceId),
        contentDescription = stringResource(contentDescriptionResourceId),
        modifier = Modifier

            .width(dimensionResource(R.dimen.button_image_width))
            .height(dimensionResource(R.dimen.button_image_height))
            .padding(dimensionResource(R.dimen.button_interior_padding)) ) }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
        Text(
            text = stringResource(textLabelResourceId),
            style = MaterialTheme.typography.bodyLarge )
    } }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp() } }
