package com.dongdongshop.dklog;

import com.dongdongshop.annotation.DKlog;
import org.springframework.stereotype.Service;

@Service
public class DKtest {

    @DKlog
    public String test(Integer i){
        return i+"";
    }

}
