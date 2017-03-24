package dao.redis;

import com.mzj.im.dao.redis.RedisUserDAO;
import com.mzj.im.dao.redis.WebSocketDAO;
import com.mzj.im.model.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
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

    @Autowired
    private WebSocketDAO webSocketDAO;


    @Test
    public void testSet(){
        UserVO user = new UserVO();
        user.setId(1);
        user.setUsername("ob");
        user.setPassword("ob");
        user.setEmail("ob");
        user.setIsDelete('N');
        userDAO.set(String.valueOf(user.getId()), user);
    }

    @Test
    public void testGet() {
        UserVO user = userDAO.get(String.valueOf(1));
        System.out.println(user.toJSON().toString());
    }

    @Test
    public void testWebSocket(){
        WebSocketSessionDecorator socketSession = new WebSocketSessionDecorator(
                new WebSocketSession() {
                    @Override
                    public String getId() {
                        return null;
                    }

                    @Override
                    public URI getUri() {
                        return null;
                    }

                    @Override
                    public HttpHeaders getHandshakeHeaders() {
                        return null;
                    }

                    @Override
                    public Map<String, Object> getAttributes() {
                        return null;
                    }

                    @Override
                    public Principal getPrincipal() {
                        return null;
                    }

                    @Override
                    public InetSocketAddress getLocalAddress() {
                        return null;
                    }

                    @Override
                    public InetSocketAddress getRemoteAddress() {
                        return null;
                    }

                    @Override
                    public String getAcceptedProtocol() {
                        return null;
                    }

                    @Override
                    public void setTextMessageSizeLimit(int messageSizeLimit) {

                    }

                    @Override
                    public int getTextMessageSizeLimit() {
                        return 0;
                    }

                    @Override
                    public void setBinaryMessageSizeLimit(int messageSizeLimit) {

                    }

                    @Override
                    public int getBinaryMessageSizeLimit() {
                        return 0;
                    }

                    @Override
                    public List<WebSocketExtension> getExtensions() {
                        return null;
                    }

                    @Override
                    public void sendMessage(WebSocketMessage<?> message) throws IOException {

                    }

                    @Override
                    public boolean isOpen() {
                        return false;
                    }

                    @Override
                    public void close() throws IOException {

                    }

                    @Override
                    public void close(CloseStatus status) throws IOException {

                    }
                }
        );
        webSocketDAO.set("1", socketSession);
        webSocketDAO.get("1");
    }
}
