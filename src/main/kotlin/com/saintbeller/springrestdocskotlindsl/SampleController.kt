package com.saintbeller.springrestdocskotlindsl

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {

    @GetMapping("/samples")
    suspend fun getSamples(): List<Sample> {
        return listOf(
            Sample(1, "첫번째"),
            Sample(2, "두번째")
        )
    }

    @GetMapping("/samples/{id}")
    suspend fun getSample(@PathVariable id: Long): Sample {
        return Sample(id, "${id}번째")
    }
}

data class Sample(
    val id: Long,
    val message: String
)
