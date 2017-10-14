package com.lynas.reservationclient

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.hateoas.Resources
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import kotlin.streams.toList


@EnableFeignClients
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
class ReservationClientApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReservationClientApplication::class.java, *args)
}


@FeignClient("reservation-service")
interface ReservationReader {
    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/reservations")
    fun read(): Resources<Reservation>
    
}

class Reservation(var reservationName: String? = null)

@RestController
@RequestMapping("/reservations")
class ReservationApiAdapterRestController(val reservationReader: ReservationReader) {

    @GetMapping("/names")
    fun names(): List<String?> {
        return reservationReader.read().content.stream().map { it.reservationName }.toList()
    }


}