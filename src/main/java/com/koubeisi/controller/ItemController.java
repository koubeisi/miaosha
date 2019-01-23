package com.koubeisi.controller;

import com.koubeisi.controller.viewobject.ItemVO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.response.CommonReturnType;
import com.koubeisi.service.ItemService;
import com.koubeisi.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/23 14:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

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

        ItemModel itemModelFromReturn=itemService.createItem(itemModel);

        ItemVO itemVO = convertItemVOFromItemModel(itemModelFromReturn);

        return CommonReturnType.create(itemVO);
    }

    private ItemVO convertItemVOFromItemModel(ItemModel itemModel){
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);

        return itemVO;
    }
}
