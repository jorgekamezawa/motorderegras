package br.com.motorderegras.domain

enum class JoinEnum {
    AND {
        override fun run(v1: Boolean, v2: Boolean) = v1.and(v2)
    },
    OR {
        override fun run(v1: Boolean, v2: Boolean) = v1.or(v2)
    };

    abstract fun run(v1: Boolean, v2: Boolean): Boolean
}