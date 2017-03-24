package com.mzj.im.biz;

import com.mzj.im.dao.MsgDAO;
import com.mzj.im.model.po.MsgPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ob on 17-3-23.
 */
@Component
public class MsgBiz {

    @Autowired
    private MsgDAO msgDAO;

    public List<MsgPO> getAllMsgByUserId(long userId){
        List<MsgPO> msglist = msgDAO.selectPostMsgByUserId(userId);
        msglist.addAll(msgDAO.selectGetMsgByUserId(userId));
        Collections.sort(msglist);
        return msglist;
    }
}
