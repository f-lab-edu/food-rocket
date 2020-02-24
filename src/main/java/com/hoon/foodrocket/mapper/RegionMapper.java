package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper {
    List<Region> getRegions();

    void insertRegion(List<Region> regions);
}
