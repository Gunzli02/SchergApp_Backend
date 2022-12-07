package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val fakeContacts = mutableListOf<Contact>(Contact(1,"Johannes", "Will nach Hause", "https://media.istockphoto.com/id/1172427455/de/foto/sch%C3%B6ner-sonnenuntergang-%C3%BCber-dem-tropischen-meer.jpg?s=612x612&w=0&k=20&c=aqgfvqp8YjJ7-y2k3ifs_FO7wFDZ2fPpu1XHRqqeMZ0="), Contact(2,"Dennis", "Will nicht mehr", "https://media.istockphoto.com/id/1172427455/de/foto/sch%C3%B6ner-sonnenuntergang-%C3%BCber-dem-tropischen-meer.jpg?s=612x612&w=0&k=20&c=aqgfvqp8YjJ7-y2k3ifs_FO7wFDZ2fPpu1XHRqqeMZ0="))
    //configureSecurity()
    configureSerialization()
    configureRouting(fakeContacts)
}
