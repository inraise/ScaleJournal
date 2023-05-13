package com.example.octalnews.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.octalnews.presentation.items.loginTextField
import com.example.octalnews.presentation.theme.fontInter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

@Composable
fun LoginScreen(
    navController: NavController,
    context: Activity,
    auth: FirebaseAuth
) {
    var loginEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var valid by remember { mutableStateOf(false) }

    if ("@" in loginEmail && password.length >= 8)
        valid = true

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

        Spacer(modifier = Modifier.padding(top = 55.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Email ID:",
            color = Color(0xFF303030),
            fontWeight = FontWeight.W500,
            fontSize = 17.sp,
            fontFamily = fontInter,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))

        loginEmail = loginTextField(loginEmail, "info@octal.com")

        Spacer(modifier = Modifier.padding(top = 20.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Password:",
            color = Color(0xFF303030),
            fontWeight = FontWeight.W500,
            fontSize = 17.sp,
            fontFamily = fontInter,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))

        password = loginTextField(password, "Enter your password")

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (loginEmail.isNotEmpty() && Pattern.matches(
                            "^(?=.{1,64}@)[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)" +
                                    "*@[^-][a-zA-Z0-9_-]+(\\.[a-zA-Z0-9]+)*(\\.[a-z]{2,3})$",
                            loginEmail
                        )
                    ) {
                        auth
                            .sendPasswordResetEmail(loginEmail)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast
                                        .makeText(
                                            context,
                                            "We've sent an email to you",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                    } else {
                        Toast
                            .makeText(
                                context,
                                "Email is empty or not suitable",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                },
            text = "Reset password",
            color = Color(0xFF303030),
            fontWeight = FontWeight.W700,
            fontSize = 15.sp,
            fontFamily = fontInter,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.padding(top = 30.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(15.dp))
                .border(0.5.dp, Color(0xFF303030), RoundedCornerShape(15.dp)),
            onClick = {
                if (valid) {
                    auth.signInWithEmailAndPassword(
                        loginEmail.replace(" ", ""),
                        password.replace(" ", "")
                    )
                        .addOnCompleteListener(context) { task ->
                            if (task.isSuccessful) {
                                navController.navigate(Screens.MainScreen.route) {
                                    popUpTo(Screens.MainScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                auth.createUserWithEmailAndPassword(
                                    loginEmail.replace(" ", ""),
                                    password.replace(" ", "")
                                )
                                    .addOnCompleteListener(context) { task2 ->
                                        if (task2.isSuccessful) {
                                            navController.navigate(Screens.AuthScreen.route) {
                                                popUpTo(Screens.AuthScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Valid Error",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        }
                } else {
                    Toast.makeText(context, "Valid Error", Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFEF0E6))
        ) {
            Text(
                text = "Login now",
                color = Color(0xFF303030),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = fontInter,
            )
        }
    }
}