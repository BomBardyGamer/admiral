@file:Suppress("unused")

package me.bardy.admiral

import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder

@DslMarker
annotation class CommandDSL

@CommandDSL
inline fun <S> literal(
    argument: String,
    builder: LiteralArgumentBuilder<S>.() -> Unit = {}
): LiteralArgumentBuilder<S> = LiteralArgumentBuilder.literal<S>(argument).apply(builder)

@CommandDSL
inline fun <S, T> required(
    argument: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<S, T>.() -> Unit = {}
): RequiredArgumentBuilder<S, T> = RequiredArgumentBuilder.argument<S, T>(argument, type).apply(builder)

@CommandDSL
fun <S> LiteralArgumentBuilder<S>.literal(
    argument: String,
    builder: LiteralArgumentBuilder<S>.() -> Unit = {}
): LiteralArgumentBuilder<S> = then(LiteralArgumentBuilder.literal<S>(argument).apply(builder))

@CommandDSL
fun <S, T> LiteralArgumentBuilder<S>.required(
    argument: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<S, T>.() -> Unit = {}
): LiteralArgumentBuilder<S> = then(RequiredArgumentBuilder.argument<S, T>(argument, type).apply(builder))

@CommandDSL
fun <S, T> RequiredArgumentBuilder<S, T>.required(
    argument: String,
    type: ArgumentType<T>,
    builder: RequiredArgumentBuilder<S, T>.() -> Unit = {}
): RequiredArgumentBuilder<S, T> = then(RequiredArgumentBuilder.argument<S, T>(argument, type).apply(builder))

@CommandDSL
fun <S, T> RequiredArgumentBuilder<S, T>.literal(
    argument: String,
    builder: LiteralArgumentBuilder<S>.() -> Unit = {}
): RequiredArgumentBuilder<S, T> = then(LiteralArgumentBuilder.literal<S>(argument).apply(builder))

@CommandDSL
val ArgumentBuilder<*, *>.integer
    get() = integer()

@CommandDSL
fun ArgumentBuilder<*, *>.integer(
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE
): IntegerArgumentType = IntegerArgumentType.integer(min, max)

@CommandDSL
val ArgumentBuilder<*, *>.long
    get() = long()

@CommandDSL
fun ArgumentBuilder<*, *>.long(
    min: Long = Long.MIN_VALUE,
    max: Long = Long.MAX_VALUE
): LongArgumentType = LongArgumentType.longArg(min, max)

@CommandDSL
val ArgumentBuilder<*, *>.float
    get() = float()

@CommandDSL
fun ArgumentBuilder<*, *>.float(
    min: Float = Float.MIN_VALUE,
    max: Float = Float.MAX_VALUE
): FloatArgumentType = FloatArgumentType.floatArg(min, max)

@CommandDSL
val ArgumentBuilder<*, *>.double
    get() = double()

@CommandDSL
fun ArgumentBuilder<*, *>.double(
    min: Double = Double.MIN_VALUE,
    max: Double = Double.MAX_VALUE
): DoubleArgumentType = DoubleArgumentType.doubleArg(min, max)

@CommandDSL
val ArgumentBuilder<*, *>.boolean: BoolArgumentType
    get() = BoolArgumentType.bool()

@CommandDSL
val ArgumentBuilder<*, *>.word: StringArgumentType
    get() = StringArgumentType.word()

@CommandDSL
val ArgumentBuilder<*, *>.string: StringArgumentType
    get() = StringArgumentType.string()

@CommandDSL
val ArgumentBuilder<*, *>.greedyString: StringArgumentType
    get() = StringArgumentType.greedyString()