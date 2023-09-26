package ru.sccs.playground1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "yo", description = "yo meths")
public interface TestAPI {

    @Operation(summary = "asda", description = "desc")
    @GetMapping("/test")
    ResponseEntity<String> getTest();
}
