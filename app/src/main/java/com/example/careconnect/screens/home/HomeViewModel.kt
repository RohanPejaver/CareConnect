package com.example.careconnect.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.careconnect.core.Constants.TAG
import com.example.careconnect.model.User
import com.example.careconnect.model.UserDataState
import com.example.careconnect.model.VaccinationData
import com.example.careconnect.model.VaccinationDataState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private var _user = MutableStateFlow<User?>(null)
    var user = _user.asStateFlow()

    private val db = Firebase.firestore
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    val response: MutableState<UserDataState> = mutableStateOf(UserDataState.UserEmpty)
    val vaccinationResponse: MutableState<VaccinationDataState> = mutableStateOf(VaccinationDataState.VaccinationDataEmpty)

    init {
        getUser()
    }

    private fun getUser() {
        if (uid != null) {
            db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    _user.value = documentSnapshot.toObject(User::class.java)
                }
        }
    }

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        val tempList = mutableListOf<User>()
        val vacTempList = mutableListOf<VaccinationData>()

        response.value = UserDataState.UserLoading
        vaccinationResponse.value = VaccinationDataState.VaccinationDataLoading
        if (uid != null) {
            db.collection("chats")
                .document(uid)
                .collection("connections")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot) {
                        val user = document.toObject(User::class.java)
                        if (user.userId != uid) {
                            db.collection("vaccinations")
                                .document(user.userId)
                                .get()
                                .addOnSuccessListener { documentSnapshot ->
                                    val vaccinationDetails = documentSnapshot.toObject(VaccinationData::class.java)
                                    if (vaccinationDetails != null) {
                                        vacTempList.add(vaccinationDetails)
                                    }
                                    if(vacTempList.size != 0)
                                    Log.d(TAG, vacTempList[0].dateOfBirth)
                                }
                            tempList.add(user)
                        }
                    }
                    response.value = UserDataState.UserSuccess(tempList)
                    vaccinationResponse.value = VaccinationDataState.VaccinationDataSuccess(vacTempList)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }
}