package com.hoon.foodrocket.controller;

import com.hoon.foodrocket.aop.UserAuthorityLevel;
import com.hoon.foodrocket.aop.LoginType;
import com.hoon.foodrocket.service.RegionService;
import com.hoon.foodrocket.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * 사장이 본인 가게의 배달 지역을 등록할때 사용합니다.
     * @param regions
     * @return
     */
    @LoginType(level = UserAuthorityLevel.OWNER)
    @PostMapping
    public HttpStatus create(@RequestBody List<Region> regions) {
        regionService.registerRegion(regions);

        return HttpStatus.CREATED;
    }
}
