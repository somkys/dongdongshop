package com.dongdongshop.vo;

import com.dongdongshop.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO implements Serializable {
    private String sellerID;
    private String sellerName;
    private LocalDateTime createTime;
    private String tradeNum;
    private List<OrderItem> orderItemList;
}
