package pl.sylwekczmil.ma.domain.mongo.dashboard.contact

class Contact() {
    var firstName = ""
    var lastName = ""
    var phoneNumber = "000-000-000"
    var description = ""
    var address = Address()

    // if user want to link other user to this contact
    var username: String? = null

    constructor(firstName: String, lastName: String, phoneNumber: String, description: String, address: Address) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.description = description
        this.address = address
    }
}

class Address() {
    var city = ""
    var street = ""
    var detailed = ""

    constructor(city: String, street: String, detailed: String) : this() {
        this.city = city
        this.street = street
        this.detailed = detailed
    }
}