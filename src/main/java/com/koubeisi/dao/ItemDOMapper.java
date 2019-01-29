package com.koubeisi.dao;

import com.koubeisi.dataobject.ItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer id);

    List<ItemDO> selectAllItem();

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);

    int increaseSales(@Param(value = "id") Integer id, @Param(value = "amount") Integer amount);

}