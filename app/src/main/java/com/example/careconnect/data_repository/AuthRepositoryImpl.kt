package com.example.careconnect.data_repository

import android.util.Log
import com.example.careconnect.core.Constants.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import com.example.careconnect.domain.AuthRepository
import com.example.careconnect.domain.Response.Success
import com.example.careconnect.domain.Response.Failure
import com.example.careconnect.model.Message
import com.example.careconnect.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    private val db = Firebase.firestore

    override val currentUser get() = auth.currentUser

    override suspend fun firebaseSignUpWithEmailAndPassword(
        email: String, password: String, username : String, role: String
    ) = try {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).await()
        addUserData(username, email, role)
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun firebaseSignInWithEmailAndPassword(
        email: String, password: String
    ) = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun reloadFirebaseUser() = try {
        auth.currentUser?.reload()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun sendPasswordResetEmail(email: String) = try {
        auth.sendPasswordResetEmail(email).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun signOut() = auth.signOut()

    override suspend fun revokeAccess() = try {
        auth.currentUser?.delete()?.await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), auth.currentUser == null)

    private fun addUserData(email: String, username: String, role: String) {
        val uid = auth.currentUser?.uid
        val user = User(email, username, role, userId = uid.toString(), connectionId = uid.toString().substring(6,12))

        if (uid != null) {
            db.collection("users")
                .document(uid)
                .set(user)
                .addOnSuccessListener {
                    Log.d(TAG, "SUCCESS")
                }.addOnFailureListener { e ->
                    Log.d(TAG, "$e FAil")
                }

            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(uid)
                .set(user)
                .addOnSuccessListener {
                    Log.d(TAG, "SUCCESS")
                }.addOnFailureListener { e ->
                    Log.d(TAG, "$e FAil")
                }

            db.collection("chats")
                .document(uid)
                .collection("connections")
                .document(uid)
                .collection("messages")
                .document("example")
                .set(Message("This is your first message"))
        } else {
            Log.d(TAG, "FAIL")
        }
    }
}