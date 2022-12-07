package com.example.plugins

import com.example.Contact
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.util.UUID

fun Application.configureRouting(list: MutableList<Contact>) {

    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        route("/contacts") {
            get {
                call.respond(list)
            }

            post {
                val contact = call.receive<Contact>()
                var found = list.find {it.id == contact.id}
                if(found == null)
                {
                    list.add(contact)
                    call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
                }
                else
                {
                    call.respondText("Customer already exists", status = HttpStatusCode.Conflict)
                }
            }
        }
        route("/contacts/{UUID}") {
            get {
                var id = call.parameters["UUID"]?.toInt()
                var contact = list.find{ it.id == id}
                if(contact != null)
                call.respond(contact)
            }

            put {
                val contact = call.receive<Contact>()
                var found = list.find {it.id == contact.id}
                if(found != null)
                {
                    list.remove(found)
                    list.add(contact)
                    call.respondText("Customer updated correctly", status = HttpStatusCode.Accepted)
                }
                else
                {
                    call.respondText("Customer doesn't exist", status = HttpStatusCode.Conflict)
                }
            }

            delete {
                val id = call.parameters["UUID"]?.toInt()
                var found = list.find {it.id == id}
                if(found != null)
                {
                    list.remove(found)
                    call.respondText("Customer removed", status = HttpStatusCode.Accepted)
                }
                else
                {
                    call.respondText("Customer doesn't exist", status = HttpStatusCode.Conflict)
                }
            }
        }
    }
}
