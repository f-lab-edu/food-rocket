package com.hoon.foodrocket.mapper;

import com.hoon.foodrocket.domain.CartItem;
import com.hoon.foodrocket.domain.order.Order;
import com.hoon.foodrocket.domain.order.OrderDetail;
import com.hoon.foodrocket.domain.order.OrderHistory;
import com.hoon.foodrocket.domain.order.OrderStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderHistory> getOrderHistoryList(Long cursorId, String loginUserEmail);

    OrderDetail getOrderDetail(Long id, String loginUserEmail);

    void insertOrder(Order order);

    void insertOrderMenus(@Param("cartItemList") List<CartItem> cartItemList,
                          @Param("orderId") Long orderId);

    void updateOrderStatus(OrderStatus orderStatus, Long id);
}
