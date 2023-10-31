package com.example.qrcodegenerator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrcodegenerator.ui.theme.QRCodeGeneratorTheme


data class NewsArticle(val title: String, val source: String, val time: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAppMainScreen() {



}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Scaffold(topBar: () -> Unit) {
    var searchText by
    remember { mutableStateOf("") }
    var articles by remember { mutableStateOf(dummyArticles) }
    TopAppBar(
        title = {
            BasicTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    // You can filter articles based on the search text here.
                    // articles = filterArticles(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Perform search action here.
                    }
                ),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    )
}

@Composable
fun Body() {

}

@Composable
fun NewsArticleItem(article: NewsArticle) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click here */ }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = article.title, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.source, color = colorResource(id = R.color.purple_200))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = article.time)
        }
    }
}


@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "QR Code Generator ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun NewsAppMainScreenPreview() {
    QRCodeGeneratorTheme() {
//        NewsAppMainScreen()
        MainScreen( )
    }
}

val dummyArticles = listOf(
    NewsArticle("Headline 1", "Source 1", "2 hours ago"),
    NewsArticle("Headline 2", "Source 2", "4 hours ago"),
    NewsArticle("Headline 3", "Source 3", "6 hours ago"),
    NewsArticle("Headline 4", "Source 4","8 hours ago"))