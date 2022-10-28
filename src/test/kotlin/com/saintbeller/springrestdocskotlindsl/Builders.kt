package com.saintbeller.springrestdocskotlindsl

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation

class ParameterBuilder {
    private val fields = mutableListOf<ParameterDescriptor>()

    infix fun String.means(description: Any) {
        fields.add(RequestDocumentation.parameterWithName(this).description(description))
    }

    fun build() = fields.toList()
}

class HeaderBuilder {
    private val headers = mutableListOf<HeaderDescriptor>()

    infix fun String.means(value: Any) {
        headers.add(HeaderDocumentation.headerWithName(this).description(value))
    }

    fun build() = headers.toList()
}

class FieldsBuilder {
    private val fields = mutableListOf<FieldDescriptor>()

    infix fun String.type(type: JsonFieldType): Field {
        val field = Field(PayloadDocumentation.fieldWithPath(this).type(type))
        fields.add(field.descriptor)
        return field
    }

    fun build() = fields.toList()
}
