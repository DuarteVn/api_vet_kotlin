package com.duarte.vetapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VetapiApplication

fun main(args: Array<String>) {
	runApplication<VetapiApplication>(*args)
}
