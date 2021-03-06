package pl.sylwekczmil.ma.backend.domain.dashboard.contact

class Contact() {
    var firstName = ""
    var lastName = ""
    var phoneNumber = "000-000-000"
    var description = ""
    var address = Address()

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