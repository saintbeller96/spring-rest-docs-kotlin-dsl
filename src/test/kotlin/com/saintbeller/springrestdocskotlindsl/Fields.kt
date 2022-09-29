package com.saintbeller.springrestdocskotlindsl

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.ResponseFieldsSnippet

fun responseBody(vararg field: Field): ResponseFieldsSnippet {
    return PayloadDocumentation.responseFields(field.map { it.descriptor })
}

data class Field(
    val descriptor: FieldDescriptor
) {
    infix fun means(value: String): Field {
        return Field(descriptor.description(value))
    }

    infix fun attributes(block: Field.() -> Unit): Field {
        block()
        return this
    }

    infix fun optional(isOptional: Boolean): Field {
        if(isOptional) descriptor.optional()
        return this
    }
}

infix fun String.type(type: JsonFieldType): Field {
    return Field(PayloadDocumentation.fieldWithPath(this).type(type))
}
