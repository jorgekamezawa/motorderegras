package br.com.motorderegras.domain

import java.math.BigDecimal

enum class OperatorEnum {
    EQUAL {
        override fun run(v1: Any, v2: Any) =
            if (v1 is String && v2 is String) v1.toString().equals(v2.toString(), ignoreCase = true)
            else v1 == v2
    },
    NOT_EQUAL {
        override fun run(v1: Any, v2: Any) =
            if (v1 is String && v2 is String) !v1.toString().equals(v2.toString(), ignoreCase = true)
            else v1 != v2
    },
    IN {
        override fun run(v1: Any, v2: Any) = v2.toString().contains(v1.toString(), ignoreCase = true)
    },
    NOT_IN {
        override fun run(v1: Any, v2: Any) = !v2.toString().contains(v1.toString(), ignoreCase = true)
    },
    GREATER_THAN_EQUAL {
        override fun run(v1: Any, v2: Any) =
            when (v1) {
                is BigDecimal -> v1 >= if (v2 is BigDecimal) v2 else BigDecimal(v2.toString())
                is Int -> v1 >= if (v2 is Int) v2 else v2.toString().toInt()
                is Long -> v1 >= if (v2 is Long) v2 else v2.toString().toLong()
                is Double -> v1 >= if (v2 is Double) v2 else v2.toString().toDouble()
                else -> throw RuntimeException("GREATER_THAN_EQUAL: v1 = $v1 is not a Number type")
            }
    },
    LESS_THAN_EQUAL {
        override fun run(v1: Any, v2: Any) =
            when (v1) {
                is BigDecimal -> v1 <= if (v2 is BigDecimal) v2 else BigDecimal(v2.toString())
                is Int -> v1 <= if (v2 is Int) v2 else v2.toString().toInt()
                is Long -> v1 <= if (v2 is Long) v2 else v2.toString().toLong()
                is Double -> v1 <= if (v2 is Double) v2 else v2.toString().toDouble()
                else -> throw RuntimeException("LESS_THAN_EQUAL: v1 = $v1 is not a Number type")
            }
    },
    GREATER_THAN {
        override fun run(v1: Any, v2: Any) =
            when (v1) {
                is BigDecimal -> v1 > if (v2 is BigDecimal) v2 else BigDecimal(v2.toString())
                is Int -> v1 > if (v2 is Int) v2 else v2.toString().toInt()
                is Long -> v1 > if (v2 is Long) v2 else v2.toString().toLong()
                is Double -> v1 > if (v2 is Double) v2 else v2.toString().toDouble()
                else -> throw RuntimeException("GREATER_THAN: v1 = $v1 is not a Number type")
            }
    },
    LESS_THAN {
        override fun run(v1: Any, v2: Any) =
            when (v1) {
                is BigDecimal -> v1 < if (v2 is BigDecimal) v2 else BigDecimal(v2.toString())
                is Int -> v1 < if (v2 is Int) v2 else v2.toString().toInt()
                is Long -> v1 < if (v2 is Long) v2 else v2.toString().toLong()
                is Double -> v1 < if (v2 is Double) v2 else v2.toString().toDouble()
                else -> throw RuntimeException("LESS_THAN: v1 = $v1 is not a Number type")
            }
    };
    
    abstract fun run(v1: Any, v2: Any): Boolean
}