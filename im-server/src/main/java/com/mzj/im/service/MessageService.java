package com.mzj.im.service;

import com.mzj.im.model.po.MessagePO;
import com.mzj.im.util.dic.OperateResult;
import org.springframework.stereotype.Service;

/**
 * Created by OB on 2017/2/9.
 */
@Service
public class MessageService {

    public OperateResult addOneMessage(MessagePO messagePO) {
        return OperateResult.SUCCESS;
    }
}
