package com.sihan.testspring.common

import org.springframework.http.HttpHeaders
import java.util.UUID

object ResponseExtensions {
    fun commonHeaders():HttpHeaders{
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json")
        headers.add("X-Security-Key",UUID.randomUUID().toString())
        return headers
    }

    fun createdHeaders(location:String):HttpHeaders{
        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json")
        headers.add("X-Security-Key",UUID.randomUUID().toString())
        headers.add(HttpHeaders.LOCATION,location)
        return headers
    }
}