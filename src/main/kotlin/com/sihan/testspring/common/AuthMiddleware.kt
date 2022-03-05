package com.sihan.testspring.common

import org.springframework.http.HttpStatus
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthMiddleware : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val resp = response as HttpServletResponse
        val authHeader = req.getHeader("authorization")
        if (authHeader == null) {
            resp.status = HttpStatus.UNAUTHORIZED.value()
            resp.setHeader("X-Security-Key", UUID.randomUUID().toString())
        } else {
            val trimmed = authHeader.split(" ")
            if (trimmed[0] == "Basic" && !trimmed[1].isNullOrBlank()) {
                chain?.doFilter(request, response)
            } else {
                resp.status = HttpStatus.UNAUTHORIZED.value()
                resp.setHeader("X-Security-Key", UUID.randomUUID().toString())
            }
        }
    }
}