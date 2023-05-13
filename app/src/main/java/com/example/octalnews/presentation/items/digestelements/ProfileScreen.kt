package com.example.octalnews.presentation.items.digestelements

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.octalnews.presentation.items.CountrySelectMenu
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel
import com.example.octalnews.presentation.theme.fontInter
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileScreen(auth: FirebaseAuth, context: Activity, newsViewModel: NewsViewModel) {
    val activity = (LocalContext.current as? Activity)
    val keyboardController = LocalSoftwareKeyboardController.current
    var resetPassword by remember {
        mutableStateOf("")
    }

    Spacer(modifier = Modifier.padding(top = 30.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "User Email: ",
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 15.sp
        )

        SelectionContainer {
            Text(
                text = auth.currentUser?.email.toString(),
                color = Color(0xFF303030),
                fontWeight = FontWeight.Bold,
                fontFamily = fontInter,
                fontSize = 13.sp
            )
        }
    }

    Spacer(modifier = Modifier.padding(top = 20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "User ID: ",
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 15.sp
        )

        SelectionContainer {
            Text(
                text = auth.currentUser?.uid.toString(),
                color = Color(0xFF303030),
                fontWeight = FontWeight.Bold,
                fontFamily = fontInter,
                fontSize = 13.sp
            )
        }
    }

    Spacer(modifier = Modifier.padding(top = 20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Change Password: ",
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 15.sp
        )

        TextField(
            modifier = Modifier
                .width(190.dp)
                .height(55.dp),
            value = resetPassword,
            onValueChange = { resetPassword = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    auth.currentUser!!.updatePassword(resetPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(context, "Password is changed!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                focusedLabelColor = Color(0xFF303030),
                focusedIndicatorColor = Color(0xFF303030),
                unfocusedIndicatorColor = Color(0xFF303030),
                unfocusedLabelColor = Color(0xFF303030),
                cursorColor = Color(0xFF303030)
            )
        )

    }

    Spacer(modifier = Modifier.padding(top = 20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Select Country: ",
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 15.sp
        )

        CountrySelectMenu(newsViewModel, contextForViewModel = context)
    }

    Spacer(modifier = Modifier.padding(top = 20.dp))

    Button(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(
            Color(0xFF303030)
        ),
        onClick = {
            auth.signOut()
            activity?.finish()
        }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Quit",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 14.sp
        )
    }

    Spacer(modifier = Modifier.padding(top = 15.dp))

    Button(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color(0xFF303030), RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(
            Color(0xFFFEF0E6)
        ),
        onClick = {
            auth.currentUser!!.delete()
                .addOnCompleteListener {}

            auth.signOut()
            activity?.finish()
        }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Delete Account",
            color = Color(0xFF303030),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = fontInter,
            fontSize = 14.sp
        )
    }
}