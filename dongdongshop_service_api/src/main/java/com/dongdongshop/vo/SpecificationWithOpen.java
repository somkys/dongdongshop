package com.dongdongshop.vo;

import com.dongdongshop.entity.SpecificationOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationWithOpen implements Serializable {
    List<SpecificationOption> specificationOptions;
    private Long id;
    private String specName;
}
