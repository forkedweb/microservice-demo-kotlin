package com.lynas.reservationclient

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
class ReservationClientApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReservationClientApplication::class.java, *args)
}
