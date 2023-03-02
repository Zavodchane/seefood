package com.example.seefood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.seefood.ui.theme.SeefoodTheme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {

                DefaultPreview(name = "misha", job = "Android Developer")
                DefaultPreview(name = "masha", job = "Artist")

            }
        }
    }
}


@Composable
fun DefaultPreview(name: String, job: String) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 300.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp).size(80.dp)
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = if (name == "misha") R.drawable.misha else R.drawable.masha),
                contentDescription = if (name == "misha") "Misha" else "Masha",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = name)
                Text(text = job)
            }
        }
    }
}