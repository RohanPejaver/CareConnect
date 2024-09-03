package com.example.careconnect.navigation

sealed class Screen (val route : String) {

    object Choose : Screen(route = "choose_screen")
    object SignIn : Screen(route = "sign_in_screen")
    object Signup : Screen(route = "signup_screen")
    object Home : Screen(route = "home_screen")
    object Settings : Screen(route = "settings_screen")
    object Profile : Screen(route = "profile_screen")
    object Help : Screen(route = "help_screen")
    object Support : Screen(route = "support_screen")
    object Library : Screen(route = "library_screen")
    object Chat : Screen(route = "chat_screen")
    object Verify : Screen(route = "verify_email_screen")
    object Forgot : Screen(route = "forgot_password_screen")

}
