package com.koubeisi.controller;

import com.koubeisi.controller.viewobject.ItemVO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.response.CommonReturnType;
import com.koubeisi.service.ItemService;
import com.koubeisi.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/23 14:54
 * @Version 1.2
 **/
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @description 创建商品
     * @path        /item/create
     * @return      创建后的商品信息
     */
    @PostMapping(path = "/create")
    public CommonReturnType createItem(@RequestParam(value = "title") String title,
                                       @RequestParam(value = "description") String description,
                                       @RequestParam(value = "price") BigDecimal price,
                                       @RequestParam(value = "stock") Integer stock,
                                       @RequestParam(value = "imgUrl") String imgUrl) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelFromReturn = itemService.createItem(itemModel);

        ItemVO itemVO = convertItemVOFromItemModel(itemModelFromReturn);

        return CommonReturnType.create(itemVO);
    }

    /**
     * @description 通过id查询单个商品信息
     * @path        /item/get
     * @return      单个商品
     */
    @GetMapping(path = "/get")
    public CommonReturnType getItem(@RequestParam(value = "id") Integer id) throws BusinessException {

        ItemModel itemModel = itemService.getItemModelById(id);
        ItemVO itemVO = convertItemVOFromItemModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

    /**
     * @description 查询所有商品信息
     * @path        /item/list
     * @return      所有商品
     */
    @GetMapping(path = "/list")
    public CommonReturnType listItem() {
        List<ItemModel> itemModelList = itemService.itemModelList();

        Iterator<ItemModel> itemModelIterator = itemModelList.iterator();
        List<ItemVO> itemVOList=new ArrayList<>();
        while (itemModelIterator.hasNext()){
            ItemVO itemVO=convertItemVOFromItemModel(itemModelIterator.next());
            itemVOList.add(itemVO);
        }

        return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertItemVOFromItemModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);

        return itemVO;
    }
}
