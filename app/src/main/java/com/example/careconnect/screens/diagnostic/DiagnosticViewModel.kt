package com.example.careconnect.screens.diagnostic

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careconnect.core.Constants.TAG
import com.example.careconnect.model.ConditionHealthData
import com.example.careconnect.model.GeneralHealthData
import com.example.careconnect.model.LifestyleHealthData
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DiagnosticViewModel @Inject constructor() : ViewModel() {
    private val uid = FirebaseAuth.getInstance().currentUser?.uid

    private var _generalData = MutableStateFlow<GeneralHealthData?>(null)
    private var generalData = _generalData.asStateFlow()

    private var _lifestyleData = MutableStateFlow<LifestyleHealthData?>(null)
    private var lifestyleData = _lifestyleData.asStateFlow()

    private var _conditionData = MutableStateFlow<ConditionHealthData?>(null)
    private var conditionData = _conditionData.asStateFlow()

    var consolidatedData = ""

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = ""
    )

    // MutableStateFlow holds the text generation result. Initially, it's null.
    private val _textGenerationResult = MutableStateFlow<String?>(null)
    // Expose an immutable StateFlow to the UI to observe changes.
    val textGenerationResult = _textGenerationResult.asStateFlow()

    fun addGeneralHealthData(age: String, height: String, weight: String, gender: String){
        val db = FirebaseFirestore.getInstance()

        if(uid != null){
            db.collection("data")
                .document(uid)
                .collection("general")
                .document(uid)
                .set(GeneralHealthData(age, height, weight, gender))
        }
    }

    fun addLifestyleHealthData(diet: String, activity: String, substanceLevel: String, stressLevel: String){
        val db = FirebaseFirestore.getInstance()

        if(uid != null){
            db.collection("data")
                .document(uid)
                .collection("lifestyle")
                .document(uid)
                .set(LifestyleHealthData(diet, activity, substanceLevel, stressLevel))
        }
    }

    fun addConditionHealthData(preexistingCond: String, symptoms: String){
        val db = FirebaseFirestore.getInstance()

        if(uid != null){
            db.collection("data")
                .document(uid)
                .collection("condition")
                .document(uid)
                .set(ConditionHealthData(preexistingCond, symptoms))
        }
    }

    suspend fun fetchAllUserData() {
        val db = FirebaseFirestore.getInstance()

        if (uid != null) {
            try {
                // Using await to wait for the tasks to finish
                val generalDocument = db.collection("data")
                    .document(uid)
                    .collection("general")
                    .document(uid)
                    .get()
                    .await()

                val lifestyleDocument = db.collection("data")
                    .document(uid)
                    .collection("lifestyle")
                    .document(uid)
                    .get()
                    .await()

                val conditionDocument = db.collection("data")
                    .document(uid)
                    .collection("condition")
                    .document(uid)
                    .get()
                    .await()

                // Set the data values
                _generalData.value = generalDocument.toObject(GeneralHealthData::class.java)
                _lifestyleData.value = lifestyleDocument.toObject(LifestyleHealthData::class.java)
                _conditionData.value = conditionDocument.toObject(ConditionHealthData::class.java)

                // Concatenate the data once all data is fetched
                consolidatedData = generalData.value?.age + " " + generalData.value?.height + " " +
                        generalData.value?.weight + " " + generalData.value?.gender + " "
                consolidatedData += lifestyleData.value?.diet + " " +
                        lifestyleData.value?.activity + " " +
                        lifestyleData.value?.substanceLevel + " " +
                        lifestyleData.value?.stressLevel + " "
                consolidatedData += conditionData.value?.preexistingCond + " " +
                        conditionData.value?.symptoms

                Log.d(TAG, consolidatedData)
            } catch (e: Exception) {
                Log.e(TAG, "Error retrieving data", e)
            }
        }
    }

    init {
        interpretData()
    }

    fun interpretData() {
        val prompt = "The user has entered some health data. Here it is $consolidatedData. I want you to create a report regarding the users 1) projected heart health 2) projected lung health 3) any conditions they may be suffering from and 4) general overview of their health. Additionally, no matter what, add a disclaimer stating that this is not a certified health professional and the user must get ensure these values are correct by consulting a licensed medical professional."

        _textGenerationResult.value = "Generating the patient overview..."
        // Launch a coroutine in the IO dispatcher for network or IO operations.
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Call the generative model to generate content based on a prompt.
                val result = generativeModel.generateContent(prompt)
                // Update the StateFlow with the generated text on success.
                _textGenerationResult.value = result.text + "\n\nThese values may be incorrect as this is not a certified health professional. Ensure these values are correct by consulting a licensed medical professional."
            } catch (e: Exception) {
                // Handle exceptions by updating the StateFlow with an error message.
                _textGenerationResult.value = "Error: ${e.message}"
            }
        }
    }
}
