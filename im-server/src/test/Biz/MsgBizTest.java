package Biz;

import com.mzj.im.biz.MsgBiz;
import com.mzj.im.model.po.MsgPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ob on 17-3-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class MsgBizTest {
    @Autowired
    private MsgBiz msgBiz;

    @Test
    public void testGetAllMsgByUserId() {
        List<MsgPO> msgList = msgBiz.getAllMsgByUserId(1);
        System.out.print("test over");
    }
}
