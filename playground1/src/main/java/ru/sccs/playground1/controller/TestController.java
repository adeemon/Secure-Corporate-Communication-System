package ru.sccs.playground1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TestController implements TestAPI {
    @Override
    public ResponseEntity<String> getTest() {
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        log.fatal("FATAL");
        return ResponseEntity.ok("test");
    }
}
