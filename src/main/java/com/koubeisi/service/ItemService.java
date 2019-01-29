package com.koubeisi.service;

import com.koubeisi.error.BusinessException;
import com.koubeisi.service.model.ItemModel;

import java.util.List;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/23 13:52
 * @Version 1.0
 **/
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> itemModelList();

    //商品详情浏览
    ItemModel getItemModelById(Integer id) throws BusinessException;

    //库存扣减
    boolean decreaseStock(Integer itemId, Integer amount);

    //商品销量增加
    Boolean increaseSales(Integer itemId, Integer amount);

}
