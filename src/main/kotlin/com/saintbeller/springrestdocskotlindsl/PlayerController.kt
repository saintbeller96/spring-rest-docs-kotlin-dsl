package com.saintbeller.springrestdocskotlindsl

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/players")
class PlayerController {

    @GetMapping("/{playerId}")
    suspend fun getPlayer(@PathVariable playerId: Long): Player {
        return Player(playerId, "kim", Product(1, "1달 무제한 스트리밍 이용권", "1달 무제한 스트리밍 이용권"))
    }

    @GetMapping("")
    suspend fun getPlayers(): List<Player> {
        return listOf(

        )
    }

    @PostMapping("")
    suspend fun enrollPlayer(@RequestBody playerRequest: PlayerEnrollRequest) {

    }
}

data class Player(
    val playerId: Long,
    val name: String,
    val product: Product?,
    val roles: List<Grade> = listOf(Grade.BRONZE)
)

data class Product(
    val prodId: Long,
    val name: String,
    val description: String
)

enum class Grade {
    BRONZE, SILVER, GOLD, DIAMOND
}

data class PlayerEnrollRequest(
    val name: String,
)
