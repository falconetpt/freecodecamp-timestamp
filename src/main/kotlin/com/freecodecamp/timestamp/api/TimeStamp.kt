package com.freecodecamp.timestamp.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RestController
@RequestMapping("/api/timestamp")
class TimeStamp {
    var DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss")
            .withZone(ZoneId.of("UTC"))

    @GetMapping("")
    fun getTimeStampNow(): ResponseEntity<Any> {
        return try {
            val currentTime = Instant.now()
            val result = ReplySuccess(currentTime.toEpochMilli(), DATE_TIME_FORMATTER.format(currentTime) + " GMT")
            println("value: $value result: ${result.toString()}")
            ResponseEntity.ok(
                    result
            )
        } catch (e: Exception) {
            ResponseEntity.ok(ReplyError())
        }

    }

    @GetMapping("/{value}")
    fun getTimeStampDate(@PathVariable("value") value: String): ResponseEntity<Any> {
        return try {
            val currentTime = if (value.matches("[0-9]+".toRegex()))
                Instant.ofEpochMilli(value.toLong())
            else
                LocalDate.parse(value).atStartOfDay(ZoneId.of("UTC")).toInstant()

            val result = ReplySuccess(currentTime.toEpochMilli(), DATE_TIME_FORMATTER.format(currentTime) + " GMT")
            println("value: $value result: ${result.toString()}")

            return ResponseEntity.ok(
                    result
            )
        } catch (e: Exception) {
            ResponseEntity.ok(ReplyError())
        }

    }
}