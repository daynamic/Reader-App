package com.akshat.readerapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.akshat.readerapp.components.FABContent
import com.akshat.readerapp.components.ListCard
import com.akshat.readerapp.components.ReaderAppBar
import com.akshat.readerapp.components.TitleSection
import com.akshat.readerapp.model.MBook
import com.akshat.readerapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

@Composable
fun ReaderHomeScreen(navController: NavHostController) {
    Scaffold(topBar = {
        ReaderAppBar(title = "Reader App", navController = navController)
    }, floatingActionButton = {
        FABContent {
            navController.navigate(ReaderScreens.SearchScreen.name)
        }
    }) {
        Surface(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
        ) {
            HomeContent(navController)

        }

    }

}

@Composable
fun HomeContent(navController: NavHostController) {

    val listOfBooks = listOf(
        MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = " Again", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = "Hello ", authors = "The world us", notes = null),
        MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null)
    )

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()) email.split("@")?.get(0) else "N/A"

    Column(
        Modifier.padding(2.dp), verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + "activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colorScheme.secondaryContainer)
                Text(
                    text = currentUserName,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                HorizontalDivider()
            }
        }

        ReadingRightNowArea(
            books = listOf(), navController = navController
        )

        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks, navController)

    }

}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavHostController) {

    HorizontalScrollableComponent(listOfBooks) {

    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .horizontalScroll(scrollState)
    ) {
        for (book in listOfBooks) {
            ListCard(book) {
                onCardPressed(it)
            }
        }

    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController) {
    ListCard()
}

