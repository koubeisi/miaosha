package com.koubeisi.service.impl;

import com.koubeisi.dao.OrderDOMapper;
import com.koubeisi.dao.SequenceDOMapper;
import com.koubeisi.dataobject.OrderDO;
import com.koubeisi.dataobject.SequenceDO;
import com.koubeisi.error.BusinessException;
import com.koubeisi.error.EnumBussinessError;
import com.koubeisi.service.ItemService;
import com.koubeisi.service.OrderService;
import com.koubeisi.service.UserService;
import com.koubeisi.service.model.ItemModel;
import com.koubeisi.service.model.OrderModel;
import com.koubeisi.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Description
 * @Author koubeisi
 * @Date 2019/1/24 16:40
 * @Version 1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;


    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {

        //1.校验下单  1）用户是否合法 2）商品是否存在 3）数量是否正确
        //1）用户是否合法
        UserModel userModel = userService.getUserModelById(userId);
        if (userModel == null) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        //2)商品是否存在
        ItemModel itemModel = itemService.getItemModelById(itemId);
        if (itemModel == null) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        //3）数量是否正确
        if (amount <= 0 || amount > 99) {
            throw new BusinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR, "数量信息不存在");

        }

        //2.落单减库存 OR 支付减库存
        boolean result =itemService.decreaseStock(itemId,amount);
        if (!result){
            throw new BusinessException(EnumBussinessError.STOCK_NOT_ENOUGH);
        }
        //3.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setUserId(userId);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(amount)));
        orderModel.setId(generatorOrderId());

        orderDOMapper.insertSelective(convertOrderDOFromOrderModel(orderModel));
        //更新销量
        itemService.increaseSales(itemId,amount);

        //4.返回前端
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected String generatorOrderId(){

        StringBuffer stringBuffer = new StringBuffer();

        //订单号有16位
        //1.前八位有时间信息
        LocalDate localDate=LocalDate.now();
        DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String nowDate =localDate.format(dataTimeFormatter);

        stringBuffer.append(nowDate);

        //2.中间六位为自增序列，遗留问题：sequence没有最大值
        //1)获取序列
        Integer sequence=0;
        SequenceDO sequenceDO=sequenceDOMapper.getSequenceByName("order_info");
        sequence=sequenceDO.getCurrentVal();
        //2）更新序列
        sequenceDO.setCurrentVal(sequenceDO.getCurrentVal()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        //3）接入序列
        String sequenceStr =String.valueOf(sequence);
        for (int i=0;i<6-sequenceStr.length();i++){
            stringBuffer.append("0");
        }
        stringBuffer.append(sequenceStr);

        //3.最后两位为分库分表位,以用户id后两位，暂时写死
        stringBuffer.append("00");

        return stringBuffer.toString();
    }

    private OrderDO convertOrderDOFromOrderModel(OrderModel orderModel){

        if (orderModel == null){
            return null;
        }

        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());

        return orderDO;

    }

}
