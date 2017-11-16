package pl.sylwekczmil.ma.domain.mongo.dashboard.contact

class Contact {
    var firstName = ""
    var lastName = ""
    var phoneNumber = "000-000-000"
    var description = ""

    // if user want to link other user to this contact
    var username: String? = null
}

class Adress {
    var city = ""
    var street = ""
    var detailed = ""
}