package com.example.qrcodegenerator

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newswave.DestinationScreen
import com.example.qrcodegenerator.ui.theme.Constants
import com.example.qrcodegenerator.ui.theme.Constants.Email_ID
import com.example.qrcodegenerator.ui.theme.Constants.Instagram_ID
import com.example.qrcodegenerator.ui.theme.Constants.Location_ID
import com.example.qrcodegenerator.ui.theme.Constants.Phone_ID
import com.example.qrcodegenerator.ui.theme.Constants.SMS_ID
import com.example.qrcodegenerator.ui.theme.Constants.Text_ID
import com.example.qrcodegenerator.ui.theme.Constants.Website_ID
import com.example.qrcodegenerator.ui.theme.Constants.Whatsapp_ID
import com.google.firebase.auth.FirebaseAuth
import java.net.URLEncoder

//@Preview(showSystemUi = true, showBackground = true, heightDp = 724, widthDp = 360)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    lateinit var list: List<GridModal>
    list = ArrayList<GridModal>()
    var selectedItem by remember { mutableStateOf<Int?>(null) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var textHint by remember { mutableStateOf("Enter your text:") }

    list = list + GridModal(Text_ID, "Text", R.drawable.create_text)
    list = list + GridModal(Website_ID, "Website", R.drawable.ic_baseline_add_link_24)
    list = list + GridModal(Whatsapp_ID, "Whatsapp", R.drawable.create_whatsapp)
    list = list + GridModal(Phone_ID, "Phone", R.drawable.create_telephone)
    list = list + GridModal(Instagram_ID, "Instagram", R.drawable.create_instagram)
    list = list + GridModal(Email_ID, "Email", R.drawable.create_email)
    list = list + GridModal(Location_ID, "Location", R.drawable.baseline_location_on_24)
    list = list + GridModal(SMS_ID, "SMS", R.drawable.create_sms)


    if (selectedItem == null && list.isNotEmpty()) {
        selectedItem = list[0].id
    }

    Column(verticalArrangement = Arrangement.Center) {
        TopAppBar(
            title = {
                Text(text = "Create QR Code", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            },
            navigationIcon = {
                IconButton(onClick = {/* Do Something*/ }) {
//                Icon(Icons.Filled.Home, null)
                    Image(
                        painter = painterResource(id = R.drawable.qr_scan),
                        contentDescription = null,
                        Modifier.size(24.dp)
                    )
                }
            },
            actions = {
                Button(
                    onClick = {
                        val authHelper = FirebaseAuth.getInstance()
                        authHelper.signOut()
                        navController.navigate(DestinationScreen.LoginScreenDest.route){
                            popUpTo(route = DestinationScreen.HomeScreenDest.route) {
                                inclusive = true
                            }
                        }



                    }, colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Logout")
                }
            },
        )

        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        when (selectedItem) {

            SMS_ID -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(0.4f),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Text(
                        text = textHint,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(5.dp)
                    )
                    Column(Modifier.padding(20.dp)) {

                        OutlinedTextField(

                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = { Text("Recipient:") },

                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle done action
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp)
                        )
                        OutlinedTextField(

                            value = text2,
                            onValueChange = { newText ->
                                text2 = newText
                            },
                            label = { Text("Message:") },

                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle done action
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp)
                        )
                    }
                }


                // TextField


            }

            Location_ID -> {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(0.4f),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {


                    Column(Modifier.padding(20.dp)) {
                        Text(
                            text = textHint,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                        OutlinedTextField(

                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = { Text("Latitude:") },

                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle done action
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp)
                        )
                        OutlinedTextField(

                            value = text2,
                            onValueChange = { newText ->
                                text2 = newText
                            },
                            label = { Text("Longitude:") },

                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle done action
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp)
                        )
                    }
                }


                // TextField


            }

            Whatsapp_ID -> {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(0.3f),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = textHint,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )

                        OutlinedTextField(

                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },
                            label = { Text("eg. 91XXXXXXXXXX") },


                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Phone
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {

                                    // Handle done action
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 18.dp)
                        )

                        // TextField


                    }
                }


            }


            else -> {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(0.3f),
                    elevation = CardDefaults.cardElevation(4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = textHint,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )

                        // TextField

                        BasicTextField(
                            value = text,
                            onValueChange = { newText ->
                                text = newText
                            },

                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Uri
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    // Handle done action
                                }
                            ),
                            textStyle = TextStyle(fontSize = 16.sp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                    }
                }
            }


        }


        Button(modifier = Modifier
            .padding(horizontal = 10.dp)
            .align(CenterHorizontally),
            onClick = {
                if (text.isNotEmpty()) {
                    isError = false
                    when (selectedItem) {
                        Whatsapp_ID -> {
                            text = "https://wa.me/" + text
                        }

                        Phone_ID -> {
                            text = "tel:" + text
                        }


                        Instagram_ID -> {
                            text = "instagram://user?username=" + text
                        }


                        Email_ID -> {
                            if (text.isEmpty()) {

                            }
                            text = "mailto:" + text
                        }

                        Location_ID -> {
                            text = "geo:" + text + "," + text2
                        }

                        SMS_ID -> {
                            text = "SMSTO:" + text + ":" + text2
                        }


                    }


                    val encodedData = URLEncoder.encode(text, "UTF-8")
                    navController.navigate(
                        route = DestinationScreen.MainScreenDest.getFullRoute(
                            selectedItem!!,
                            encodedData
                        )
                    )
                }
                else{
                    isError = true
                    errorMessage = "Please fill fields."
                }


            }
        ) {
            Text(text = "Create", modifier = Modifier.padding(horizontal = 15.dp))
        }
        LazyVerticalGrid(columns = GridCells.Adaptive(85.dp), contentPadding = PaddingValues(
            start = 8.dp,
            top = 12.dp,
            end = 8.dp,
            bottom = 12.dp,
        ), verticalArrangement = Arrangement.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .weight(0.5f),
            content = {
                items(list.size) {
                    CardDesgine(
                        list[it],
                        isSelected = selectedItem == list[it].id,
                        onItemSelected = { id ->
                            selectedItem = id
                            text = ""
                            Log.d("itemId", id.toString());
                            textHint = getTextHint(id);

                        })
                }
            }
        )
    }


}

fun getTextHint(id: Int): String {

    when (id) {

        Text_ID -> {
            return "Enter your text: "
        }

        Website_ID -> {
            return "Enter your Website URL: "
        }

        Whatsapp_ID -> {
            return "Enter your Whatsapp Number: "

        }

        Phone_ID -> {
            return "Enter your Number: "

        }

        Instagram_ID -> {
            return "Enter Instagram Username: "

        }

        Email_ID -> {
            return "Enter your Email: "

        }


        Location_ID -> {
            return "Enter Location: "

        }

        SMS_ID -> {
            return "Enter Your SMS: "

        }

    }

    return "Text"

}


data class GridModal(
    val id: Int,
    val title: String,
    val img: Int
)


@Composable
fun CardDesgine(gridModal: GridModal, isSelected: Boolean, onItemSelected: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = {
                onItemSelected(gridModal.id)
            }),

        colors = CardDefaults.cardColors(containerColor = Color.White),

        border = BorderStroke(
            2.dp,
            color = if (isSelected) Color.Blue else Color.Transparent

        )

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,

            ) {

            Image(
                painter = painterResource(id = gridModal.img),
                contentDescription = "img",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(5.dp)
            )

            Text(
                text = gridModal.title,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(PaddingValues(0.dp, 5.dp))
            )
        }
    }


}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview
//@Composable
//fun ForPreview() {
//}


// Function to generate a Toast
private fun mToast(context: Context) {
    Toast.makeText(context, "Please Enter value", Toast.LENGTH_LONG).show()
}
