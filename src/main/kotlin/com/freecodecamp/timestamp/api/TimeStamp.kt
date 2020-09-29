package com.freecodecamp.timestamp.api

import org.springframework.http.ResponseEntity
import javafx.util.Duration.millis
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.Calendar
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.LocalDate







@RestController
@RequestMapping("/api/timestamp")
class TimeStamp {
    var DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss")
            .withZone(ZoneId.of("UTC"))

    @GetMapping("")
    fun getTimeStampNow(): ResponseEntity<Reply> {
        val currentTime = Instant.now()

        return ResponseEntity.ok(
                Reply(currentTime.toEpochMilli(), DATE_TIME_FORMATTER.format(currentTime) + " GMT")
        )
    }

    @GetMapping("/{value}")
    fun getTimeStampDate(@PathVariable("value") value: String): ResponseEntity<Reply> {
        val date = LocalDate.parse(value)
        val currentTime = date.atStartOfDay(ZoneId.of("UTC")).toInstant()

        return ResponseEntity.ok(
                Reply(currentTime.toEpochMilli(), DATE_TIME_FORMATTER.format(currentTime) + " GMT")
        )
    }
}