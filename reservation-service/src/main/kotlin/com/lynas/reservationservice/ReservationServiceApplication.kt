package com.lynas.reservationservice

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@SpringBootApplication
class ReservationServiceApplication{

    @Bean
    fun init(repository: ReservationRepository) = CommandLineRunner{
        listOf("sazzad","rajjak","mohesh")
                .forEach {
                    repository.save(Reservation(reservationName = it))
                }
        repository.findAll().forEach { println(it.reservationName) }
    }
}

@RestController
@RefreshScope
class MessageRestController(@Value("\${message}") var value:String){

    @GetMapping("/message")
    fun read() = this.value

}

fun main(args: Array<String>) {
    SpringApplication.run(ReservationServiceApplication::class.java, *args)
}


@RepositoryRestResource
interface ReservationRepository : JpaRepository<Reservation,Long>


@Entity
class Reservation(
        @Id @GeneratedValue var id: Long? = null,
        var reservationName: String? = null
)