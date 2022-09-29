package com.saintbeller.springrestdocskotlindsl

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.JsonFieldType.*
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.payload.ResponseFieldsSnippet
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
            .consumeWith(
                document(
                    "get a sample",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id").description("아이디")
                    ),
                    responseBody(
                        "id" type NUMBER means "아이디",
                        "message" type STRING means "메시지",
                        "something" type OBJECT means "뭘까" optional true,
                    )
                )
            )
    }
}

/***
 .andDocument {
    identifier("get a sample")
    pathVariables {
        "id" means "아이디"
    }
    responseBody {
        "id" type NUMBER means "아이디"
        "message" type STRING means "메시지" optional
        withPrefix("data[].") {

        }
    }
 }
 */
