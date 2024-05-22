package dev.codescreen.codescreen_jl7syjim.controller;

//import dev.codescreen.codescreen_jl7syjim.api.PingApi;
//import dev.codescreen.codescreen_jl7syjim.model.Ping;
import dev.codescreen.codescreen_jl7syjim.api.PingApi;
import dev.codescreen.codescreen_jl7syjim.model.Ping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PingController implements PingApi {

    @Override
    public ResponseEntity<?> pingGet() {

        Ping ping = new Ping();
        ping.serverTime(OffsetDateTime.now());

        return new ResponseEntity<Ping>(ping, HttpStatus.OK);
    }
}
