package com.example.beerfull.utils

sealed class UIComponent {
    data class Toast(val message: String): UIComponent()

}