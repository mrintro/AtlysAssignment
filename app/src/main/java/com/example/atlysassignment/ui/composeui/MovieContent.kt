package com.example.atlysassignment.ui.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.atlysassignment.R
import com.example.atlysassignment.model.MovieModel
import com.example.atlysassignment.ui.theme.HeadingSmallStyle

@Composable
fun MovieContent(
    item: MovieModel,
    onClick: (itemId: String) -> Unit
) {

    val imageRadius = LocalContext.current.resources.getDimension(R.dimen.corner_radius)

    Box(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onClick(item.id)
            }

    ) {
        Column {
            AsyncImage(
                model = item.posterUrl,
                contentDescription = " ",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(imageRadius))
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = item.title,
                style = HeadingSmallStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoad() {
    MovieContent(item = MovieModel("1", "12", "123123", null)){}

}