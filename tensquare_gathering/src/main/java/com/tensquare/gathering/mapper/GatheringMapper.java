package com.tensquare.gathering.mapper;

import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.pojo.GatheringExample;
import com.tensquare.gathering.pojo.GatheringWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GatheringMapper {
    long countByExample(GatheringExample example);

    int deleteByExample(GatheringExample example);

    int deleteByPrimaryKey(String id);

    int insert(GatheringWithBLOBs record);

    int insertSelective(GatheringWithBLOBs record);

    List<GatheringWithBLOBs> selectByExampleWithBLOBs(GatheringExample example);

    List<Gathering> selectByExample(GatheringExample example);

    GatheringWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GatheringWithBLOBs record, @Param("example") GatheringExample example);

    int updateByExampleWithBLOBs(@Param("record") GatheringWithBLOBs record, @Param("example") GatheringExample example);

    int updateByExample(@Param("record") Gathering record, @Param("example") GatheringExample example);

    int updateByPrimaryKeySelective(GatheringWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GatheringWithBLOBs record);

    int updateByPrimaryKey(Gathering record);
}