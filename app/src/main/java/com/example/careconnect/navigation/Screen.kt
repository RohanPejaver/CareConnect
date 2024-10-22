package com.example.careconnect.navigation

sealed class Screen (val route : String) {
    object Choose : Screen(route = "choose_screen")
    object SignIn : Screen(route = "sign_in_screen")
    object SignUp : Screen(route = "sign_up_screen")
    object PatientHome : Screen(route = "patient_home_screen")
    object DoctorHome : Screen(route = "doctor_home_screen")
    object Settings : Screen(route = "settings_screen")
    object Profile : Screen(route = "profile_screen")
    object Help : Screen(route = "help_screen")
    object Support : Screen(route = "support_screen")
    object Library : Screen(route = "library_screen")
    object Chat : Screen(route = "chat_screen")
    object Forgot : Screen(route = "forgot_password_screen")
    object Role : Screen(route = "role_selection_screen")
    object Search : Screen(route = "search_screen")
    object DiagnosticOne : Screen(route = "diagnostic_one_screen")
    object DiagnosticTwo : Screen(route = "diagnostic_two_screen")
    object DiagnosticThree : Screen(route = "diagnostic_three_screen")
    object Result : Screen(route = "result_screen")
    object Vaccination : Screen(route = "vaccination_screen")
    object Mental : Screen(route = "mental_screen")
}
