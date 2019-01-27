/***
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.tony.cloud.service.user.api;

import test.tony.cloud.user.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


/**
 * User API服务
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@RestController
@RequestMapping("/users")
@RefreshScope
public class UserEndpoint {
    protected Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

    @Value("${neo.product}")
    private String hello;

    @Value("${server.port:2200}")
    private int serverPort = 2200;

    @RequestMapping("/hello")
    public String from() {
        return this.hello;
    }

    @RequestMapping(value = "/{loginName}", method = RequestMethod.GET)
    public User detail(@PathVariable String loginName) {
        String memos = "I come form " + this.serverPort;
        return new User(loginName, loginName, "/avatar/default.png", memos);
    }
}
