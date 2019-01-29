package com.koubeisi.service.impl;

import com.koubeisi.dao.ItemDOMapper;
import com.koubeisi.dao.ItemStockDOMapper;
import com.koubeisi.dataobject.ItemDO;
import com.koubeisi.dataobject.ItemStockDO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.service.ItemService;
import com.koubeisi.service.model.ItemModel;
import com.koubeisi.validate.ValidatorResult;
import com.koubeisi.validate.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/23 13:56
 * @Version 1.0
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;


    private ItemDO convertItemDOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());

        return itemDO;
    }

    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setStock(itemModel.getStock());
        itemStockDO.setItemId(itemModel.getId());
        return itemStockDO;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {

        //校验入参

        if (itemModel == null) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidatorResult validatorResult = validatorUtil.validate(itemModel);
        if (validatorResult.isHasError()) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR, validatorResult.getErrorMsg());
        }

        //转换ItemModel -> ItemDO
        ItemDO itemDO = convertItemDOFromItemModel(itemModel);
        //ItemDO写入数据库
        itemDOMapper.insertSelective(itemDO);

        //转换ItemModel -> ItemStockDO
        itemModel.setId(itemDO.getId());
        ItemStockDO itemStockDO = convertItemStockDOFromItemModel(itemModel);
        //ItemStockDO写入数据库
        itemStockDOMapper.insertSelective(itemStockDO);


        //返回创建完成的对象

        return itemModel;
    }

    @Override
    public List<ItemModel> itemModelList() {

        List<ItemDO> itemDOList = itemDOMapper.selectAllItem();

        Iterator<ItemDO> itemDOIterator = itemDOList.iterator();

        List<ItemModel> itemModelList = new ArrayList<>();
        while (itemDOIterator.hasNext()) {
            ItemDO itemDO = itemDOIterator.next();
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = convertModelFromDataObject(itemDO, itemStockDO);
            itemModelList.add(itemModel);
        }

        return itemModelList;
    }

    @Override
    public ItemModel getItemModelById(Integer id) throws BusinessException {

        if (id == null) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR);
        }

        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {
            return null;
        }

        //操作获得库存数量
        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        ItemModel itemModel = convertModelFromDataObject(itemDO, itemStockDO);

        return itemModel;
    }

    @Override
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) {

        int affactedRow = itemStockDOMapper.decreaseStock(itemId, amount);
        if (affactedRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean increaseSales(Integer itemId, Integer amount) {
        int affactedRow = itemDOMapper.increaseSales(itemId, amount);
        if (affactedRow > 0) {
            return true;
        }
        return false;
    }

    private ItemModel convertModelFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {

        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());

        return itemModel;
    }
}
