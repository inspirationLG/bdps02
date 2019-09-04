package com.bdps.mservice.eshop.util;

import com.bdps.mservice.eshop.model.BaseModel;
import io.grpc.Status;

public class EntityUtil {
    public static <T extends BaseModel> T fill(T t) {
        if (t == null){
            throw Status.INTERNAL.withDescription("不能为空").asRuntimeException();
        }
        if (t.getDescription() == null){
            t.setDescription("created");
        }
        t.setFlagDeleted(false);
        return t;
    }
}
