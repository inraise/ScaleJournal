package com.example.octalnews.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.octalnews.R
import com.example.octalnews.domain.model.Screens
import com.example.octalnews.presentation.theme.fontInter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun AuthUserEmailScreen(
    navController: NavController,
    context: Activity,
    auth: FirebaseAuth
) {
    val text by remember {
        mutableStateOf(
            "To continue, you need to send " +
                    "you an e-mail message for verification."
        )
    }
    val user: FirebaseUser? = auth.currentUser

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setNavigationBarColor(
            color = Color(0xFFFEF0E6),
            darkIcons = true
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5EE))
            .padding(25.dp)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.width(200.dp),
            painter = painterResource(id = R.drawable.iconnew),
            contentDescription = null
        )

        Spacer(modifier = Modifier.padding(top = 5.dp))

        Text(
            text = "Login into your account",
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = fontInter
        )

        Spacer(modifier = Modifier.padding(top = 80.dp))

        // old

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            color = Color(0xFF303030),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontFamily = fontInter,
            textAlign = TextAlign.Start
        )

        Button(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(15.dp)),
            onClick = {
                user?.sendEmailVerification()?.addOnCompleteListener(
                    context
                ) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Verification email sent to " + user.email,
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(Screens.LoginScreen.route) {
                            popUpTo(Screens.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Failed to send verification email." + task.result,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFEF0E6))
        ) {
            Text(
                text = "Verify email",
                color = Color(0xFF303030),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = fontInter,
            )
        }
    }
}