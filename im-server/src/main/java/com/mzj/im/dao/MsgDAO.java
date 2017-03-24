package com.mzj.im.dao;

import com.mzj.im.model.po.MsgPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by ob on 17-3-23.
 * link to Msg_mapper.xml and TABLE t_msg
 */
@Repository
public interface MsgDAO {
    List<MsgPO> selectPostMsgByUserId(long postUserId);
    List<MsgPO> selectGetMsgByUserId(long getUserId);
    int insertMsgSet(Set<MsgPO> msgSet);
}
