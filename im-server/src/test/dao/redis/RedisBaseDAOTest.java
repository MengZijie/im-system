package dao.redis;

import com.mzj.im.dao.redis.RedisBaseDAO;
import com.mzj.im.model.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ob on 17-3-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class RedisBaseDAOTest {

    private RedisBaseDAO<String, UserVO> baseDAO = new RedisBaseDAO<>();

    @Test
    public void testAdd(){
        UserVO user = new UserVO();
        user.setId(2);
        user.setUsername("mzj");
        user.setPassword("mzj");
        user.setEmail("mzj");
        baseDAO.set(String.valueOf(user.getId()), user);
    }
}
