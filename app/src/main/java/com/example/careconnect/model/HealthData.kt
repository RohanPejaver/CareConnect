package com.example.careconnect.model

data class GeneralHealthData(
    var age: String,
    var weight: String,
    var height: String,
    var gender: String,
) {
    constructor(): this("", "", "", "")
}


data class LifestyleHealthData(
    var diet: String,
    var activity: String,
    var substanceLevel: String,
    var stressLevel: String,
) {
    constructor(): this("", "", "", "")
}

data class ConditionHealthData(
    var preexistingCond: String,
    var symptoms: String,
) {
    constructor(): this("", "")
}

data class InterpretationHealthData(
    var heartLevel: String,
    var lungLevel: String,
    var potentialDiseases: String,
    var finalRemarks: String,
) {
    constructor(): this("", "", "", "")
}

data class VaccinationData(
    val dateOfBirth: String,
    val vaccinationsNeeded: List<String>
){
    constructor(): this("", emptyList())
}

sealed class VaccinationDataState {
    class VaccinationDataSuccess(val data: MutableList<VaccinationData>) : VaccinationDataState()
    class VaccinationDataFailure(val message: String) : VaccinationDataState()
    object VaccinationDataLoading : VaccinationDataState()
    object VaccinationDataEmpty : VaccinationDataState()
}