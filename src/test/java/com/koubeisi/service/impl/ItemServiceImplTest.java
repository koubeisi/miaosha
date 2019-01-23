package com.koubeisi.service.impl;

import com.koubeisi.error.BusinessException;
import com.koubeisi.service.ItemService;
import com.koubeisi.service.model.ItemModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    //    @Test
    public void createItem() throws BusinessException {

        ItemModel itemModel = new ItemModel();
        itemModel.setTitle("Iphone6");
        itemModel.setPrice(new BigDecimal(6400));
        itemModel.setImgUrl("http://123.3343.343");
        itemModel.setDescription("以肾换机，你值得拥有");
        itemModel.setStock(1299);

        ItemModel returnItemModel = itemService.createItem(itemModel);

        System.out.println(returnItemModel.toString());
    }

    @Test
    public void itemModelList() {
        List<ItemModel> itemModelList= itemService.itemModelList();

        Iterator<ItemModel> itemModelIterator = itemModelList.iterator();

        while (itemModelIterator.hasNext()){
            System.out.println(itemModelIterator.next().toString());
        }

    }

//    @Test
    public void getItemModelById() throws BusinessException {
        ItemModel itemModel = itemService.getItemModelById(2);
        System.out.println(itemModel.toString());
    }
}