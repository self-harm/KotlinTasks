package rationals

import java.lang.IllegalArgumentException
import java.math.BigInteger

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}

operator fun Pair<Rational, Rational>.contains(rational: Rational): Boolean = rational >= first && rational <= second

infix fun Long.divBy(x: Long): Any {
    if (x == 0L) throw IllegalArgumentException()
    return Rational(this.toBigInteger(), x.toBigInteger())
}

infix fun Int.divBy(x: Int): Rational {
    if (x == 0) throw IllegalArgumentException()
    return Rational(this.toBigInteger(), x.toBigInteger())
}

infix fun BigInteger.divBy(x: BigInteger): Rational {
    if (x == BigInteger.ZERO) throw IllegalArgumentException()
    return Rational(this, x)
}

fun String.toRational(): Rational {
    val arrOfStrings = this.split("/")

    if (arrOfStrings.size == 1) return Rational(arrOfStrings[0].toBigInteger(), 1.toBigInteger())

    var n = arrOfStrings[0].toBigInteger()
    var d = arrOfStrings[1].toBigInteger()

    if (n < BigInteger.ZERO && d < BigInteger.ZERO) {
        n = n.abs()
        d = d.abs()
    } else if (d < BigInteger.ZERO) {
        n = n.negate()
        d = d.abs()
    }

    return Rational(n, d)
}

class Rational(var n: BigInteger, var d: BigInteger) {

    operator fun plus(x: Rational): Rational {
        return Rational((n.times(x.d)).plus(x.n.times(d)), d.times(x.d))
    }

    operator fun minus(x: Rational): Rational {
        return Rational((n.times(x.d)).minus(x.n.times(d)), d.times(x.d))
    }

    operator fun times(x: Rational): Rational {
        return Rational((n.times(x.n)), d.times(x.d))
    }

    operator fun div(x: Rational): Rational {
        return Rational((n.times(x.d)), d.times(x.n))
    }

    operator fun unaryMinus(): Rational {
        return Rational(n.negate(), d)
    }

    operator fun compareTo(x: Rational): Int {
        return (n.times(x.d).compareTo(x.n.times(d)))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rational

        if (n.times(other.d) != other.n.times(d)) return false

        return true
    }

    operator fun rangeTo(rational: Rational): Pair<Rational, Rational> {
        return Pair(this, rational)
    }


    override fun toString(): String {
        var gcd = n.gcd(d)

        if (n % d == BigInteger.ZERO) return "${n / d}"
        return "${n / gcd}/${d / gcd}"
    }

}
