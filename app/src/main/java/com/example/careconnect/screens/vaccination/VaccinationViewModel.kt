package com.example.careconnect.screens.vaccination

import androidx.lifecycle.ViewModel
import com.example.careconnect.model.VaccinationData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class VaccinationViewModel @Inject constructor() : ViewModel() {
    private val db = Firebase.firestore
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    fun addVaccinationData(vaccination: VaccinationData){
        if(uid != null) {
            db.collection("vaccination")
                .document(uid)
                .set(vaccination)
        }
    }

    fun getVaccinationsForAge(ageInMonths: Int): List<String> {
        return when {
            ageInMonths <= 2 -> listOf("Hepatitis B")
            ageInMonths <= 4 -> listOf("DTaP", "Hib", "Polio")
            ageInMonths <= 12 -> listOf("MMR", "Varicella")
            ageInMonths <= 60 -> listOf("Influenza", "Pneumococcal")
            ageInMonths <= 120 -> listOf("Tetanus", "Hepatitis A")
            else -> listOf("Influenza", "COVID-19")
        }
    }

}