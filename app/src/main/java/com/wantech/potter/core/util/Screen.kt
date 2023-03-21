package com.wantech.potter.core.util

sealed class Screen(val route:String){
    object Home:Screen(route = "Home")
    object Details:Screen(route = "details")
}
