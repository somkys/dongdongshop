package com.dongdongshop.vo;

import com.dongdongshop.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO implements Serializable {
    private String sellerID;
    private String sellerName;
    private List<OrderItem> orderItemList;
}
