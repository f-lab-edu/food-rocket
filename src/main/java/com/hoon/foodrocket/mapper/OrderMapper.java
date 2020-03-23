package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.CartItem;
import com.hoon.foodrocket.domain.Order;
import com.hoon.foodrocket.domain.OrderHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderHistory> getOrderHistoryList(String loginUserEmail);

    void insertOrder(Order order);

    void insertOrderMenus(@Param("cartItemList") List<CartItem> cartItemList,
                          @Param("orderId") Long orderId);
}
