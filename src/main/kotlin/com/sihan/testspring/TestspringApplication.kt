package com.sihan.testspring

import com.sihan.testspring.common.AuthMiddleware
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import javax.servlet.Filter


@SpringBootApplication
class TestspringApplication{
	@Bean
	fun authFilter(): FilterRegistrationBean<Filter> {
		val registration = FilterRegistrationBean<Filter>()
		registration.filter = AuthMiddleware()
		registration.addUrlPatterns("/api/v1/person/*")
		registration.order = 1
		return registration
	}
}

fun main(args: Array<String>) {
	runApplication<TestspringApplication>(*args)
}
