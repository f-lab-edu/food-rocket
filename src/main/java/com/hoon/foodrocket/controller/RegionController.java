package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.application.RegionService;
import com.hoon.foodrocket.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<Region> list() {
        return regionService.getRegions();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Region resource) {
        regionService.registerRegion(resource);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
