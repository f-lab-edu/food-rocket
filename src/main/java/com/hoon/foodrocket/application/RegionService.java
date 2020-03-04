package com.hoon.foodrocket.application;

import com.hoon.foodrocket.domain.Region;
import com.hoon.foodrocket.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionMapper regionMapper;

    public List<Region> getRegions() {
        return regionMapper.getRegions();
    }

    public void registerRegion(List<Region> regions) {
        regionMapper.insertRegion(regions);
    }

}
