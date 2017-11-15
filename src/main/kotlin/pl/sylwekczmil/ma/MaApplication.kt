package pl.sylwekczmil.ma

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MaApplication

fun main(args: Array<String>) {
    SpringApplication.run(MaApplication::class.java, *args)
}
