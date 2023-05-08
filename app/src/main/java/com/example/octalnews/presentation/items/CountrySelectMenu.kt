package com.example.octalnews.presentation.items

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.octalnews.data.ReadData
import com.example.octalnews.data.SaveData
import com.example.octalnews.presentation.screen.viewmodels.NewsViewModel
import com.example.octalnews.presentation.theme.fontInter
import com.example.octalnews.presentation.theme.fontStr

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountrySelectMenu(newsViewModel: NewsViewModel, contextForViewModel: Activity) {
    val context = LocalContext.current
    val countryList = arrayOf(
        "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu",
        "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in",
        "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz",
        "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th",
        "tr", "tw", "ua", "us", "ve", "za"
    )

    val langList = arrayOf(
        "en", "ar", "de", "en", "en", "en", "es", "en", "de", "zh", "es", "cu",
        "en", "de", "en", "fr", "en", "en", "zh", "en", "zh", "en", "he", "en",
        "it", "zh", "en", "en", "en", "en", "es", "en", "en", "nl", "no", "en",
        "en", "en", "pt", "en", "en", "ru", "en", "sv", "en", "en", "en", "en",
        "en", "en", "ru", "en", "en", "en"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(countryList[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = ReadData("country", contextForViewModel),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = TextFieldDefaults.textFieldColors(
                    focusedLabelColor = Color(0xFF303030),
                    focusedIndicatorColor = Color(0xFF303030),
                    unfocusedIndicatorColor = Color(0xFF303030),
                    unfocusedLabelColor = Color(0xFF303030)
                )
                //modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color(0xFFFFF5EE))
                    .border(1.dp, Color(0xFF303030), RoundedCornerShape(5.dp))
            ) {
                countryList.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier.background(Color(0xFFFFF5EE)),
                        content = {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = item,
                                    fontFamily = fontInter,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Spacer(
                                    modifier = Modifier
                                        .padding(top = 7.dp)
                                        .height(1.dp)
                                        .fillMaxWidth()
                                        .background(Color(0x4D303030))
                                )
                            }
                        },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()

                            SaveData(item, "country", contextForViewModel)
                            SaveData(
                                langList[findIndex(countryList, item)],
                                "lang",
                                contextForViewModel
                            )

                            newsViewModel.getNews("", contextForViewModel)
                        }
                    )
                }
            }
        }
    }
}

fun findIndex(arr: Array<String>, item: String): Int {
    return arr.indexOf(item)
}