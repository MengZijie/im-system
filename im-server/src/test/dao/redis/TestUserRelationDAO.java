package dao.redis;

import com.mzj.im.dao.UserRelationDAO;
import com.mzj.im.model.po.UserRelationPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ob on 17-5-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestUserRelationDAO {
    @Autowired
    private UserRelationDAO userRelationDAO;

    @Test
    public void testSelectFriendList(){
        List<UserRelationPo> pos = userRelationDAO.selectFriendList(0L);
        System.out.println(pos.get(0).toString());
    }
}
