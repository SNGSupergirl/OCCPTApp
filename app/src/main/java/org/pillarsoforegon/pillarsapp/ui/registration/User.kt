package org.pillarsoforegon.pillarsapp.ui.registration

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val county: String = "",
    val country: String = "",
    val phone: String = "",
    val phoneProvider: String = "",
    val textAlerts: String = "",
    val email: String = "",
    val emailView: String = "",
    val emailList: String = "",
    val pillarsMember: String = "",
    val parent: String = "",
    val registeredProvider: String = "",
    val certifiedProvider: String = "",
    val certifiedCenter: String = "",
    val unlicensedProvider: String = "",
    val username: String = "",
    val officer: Boolean = false,
    val trainer: Boolean = false
)
