package com.saintbeller.springrestdocskotlindsl

import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.headers.ResponseHeadersSnippet
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestParametersSnippet
import org.springframework.restdocs.snippet.Snippet
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.test.web.reactive.server.ExchangeResult

fun <T : ExchangeResult> WebTestClientRestDocumentation.document(
    identifier: String,
    vararg snippet: Snippet
) = WebTestClientRestDocumentation.document<T>(
    identifier,
    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
    Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
    *snippet
)

fun pathVariables(builder: ParameterBuilder.() -> Unit): PathParametersSnippet = RequestDocumentation.pathParameters(
    ParameterBuilder().apply(builder).build()
)

fun params(builder: ParameterBuilder.() -> Unit): RequestParametersSnippet = RequestDocumentation.requestParameters(
    ParameterBuilder().apply(builder).build()
)

fun requestHeaders(builder: HeaderBuilder.() -> Unit): RequestHeadersSnippet = HeaderDocumentation.requestHeaders(
    HeaderBuilder().apply(builder).build()
)

fun responseHeaders(builder: HeaderBuilder.() -> Unit): ResponseHeadersSnippet = HeaderDocumentation.responseHeaders(
    HeaderBuilder().apply(builder).build()
)

class Field(val descriptor: FieldDescriptor) {

    infix fun means(value: String): Field = Field(descriptor.description(value))

    infix fun attributes(block: Field.() -> Unit): Field {
        block()
        return this
    }

    infix fun optional(isOptional: Boolean): Field {
        if (isOptional) descriptor.optional()
        return this
    }
}

fun requestBody(builder: FieldsBuilder.() -> Unit): RequestFieldsSnippet = PayloadDocumentation.requestFields(
    FieldsBuilder().apply(builder).build()
)

fun responseBody(builder: FieldsBuilder.() -> Unit): ResponseFieldsSnippet = PayloadDocumentation.responseFields(
    FieldsBuilder().apply(builder).build()
)
