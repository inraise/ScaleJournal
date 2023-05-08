package com.example.octalnews.presentation.screen.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.octalnews.R

class ColorImageViewModel : ViewModel() {
    private var _hamburgerIcon by mutableStateOf(0)
    private var _searchIcon by mutableStateOf(0)
    private var _logoIcon by mutableStateOf(0)
    private var _backIcon by mutableStateOf(0)
    private var _searchIconW by mutableStateOf(0)

    val hamburgerIcon get() = _hamburgerIcon
    val searchIcon get() = _searchIcon
    val searchIconW get() = _searchIconW
    val logoIcon get() = _logoIcon
    val backIcon get() = _backIcon

    fun getIcon(color: Color) {
        if (color == Color(0xFFFFFFFF)) {
            _hamburgerIcon = R.drawable.blackhum
            _searchIcon = R.drawable.blacksearch
            _backIcon = R.drawable.back
            _searchIconW = R.drawable.whitesearch
            _logoIcon = R.drawable.logowhite
        } else {
            _hamburgerIcon = R.drawable.whitehum
            _searchIcon = R.drawable.whitesearch
            _searchIconW = R.drawable.blacksearch
            _backIcon = R.drawable.back2
            _logoIcon = R.drawable.logoblack
        }
    }
}