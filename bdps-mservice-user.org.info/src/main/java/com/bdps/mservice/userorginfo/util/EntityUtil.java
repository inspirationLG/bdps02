package com.bdps.mservice.userorginfo.util;

import com.bdps.mservice.userorginfo.model.BaseModel;
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
