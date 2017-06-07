package dao.redis;

import com.mzj.im.dao.redis.RedisUserDAO;
import com.mzj.im.model.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.handler.WebSocketSessionDecorator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by ob on 17-3-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class RedisUserDAOTest {

    @Autowired
    private RedisUserDAO userDAO;


    @Test
    public void testSet(){
        UserVO user = new UserVO();
        user.setId(3);
        user.setUsername("ob");
        user.setPassword("ob");
        user.setEmail("ob");
        user.setIsDelete('N');
        userDAO.put(user);
        System.out.print(user.toString());
        user = userDAO.get(user.getId());
        System.out.print(user.toString());
    }

    @Test
    public void testGet() {
        UserVO user = userDAO.get(String.valueOf(1));
        System.out.println(user.toJSON().toString());
    }
}
