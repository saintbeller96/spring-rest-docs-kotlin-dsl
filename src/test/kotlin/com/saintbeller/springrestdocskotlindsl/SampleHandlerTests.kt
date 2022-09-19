package com.saintbeller.springrestdocskotlindsl

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest
@AutoConfigureRestDocs
class SampleHandlerTests @Autowired constructor(
    private val webTestClient: WebTestClient
) {

    @Test
    fun test() {
        webTestClient.get()
            .uri("/samples/{id}", 1)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .andDocument("get a sample")
    }
}

fun WebTestClient.BodyContentSpec.andDocument(identifier: String) {
    this.consumeWith(
        document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                parameterWithName("id").description("아이디")
            ),
            responseFields(
                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지").optional()
            )
        )
    )
}
