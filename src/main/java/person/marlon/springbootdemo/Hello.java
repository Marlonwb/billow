package person.marlon.springbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import person.marlon.springbootdemo.common.cache.Foo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@RestController
@EnableAutoConfiguration
@RequestMapping("/**")
public class Hello {

	private Logger logger = LoggerFactory.getLogger(Hello.class);

    @RequestMapping("/aaa")
    String home() {
        return "Hello World!";
    }

    @Resource
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @PostConstruct
    void init(){
		Foo foo  = new Foo();
		foo.setKey("aaa");
		foo.setValue("aaa");

		Foo foo1 = new Foo();
		foo1.setKey("bbb");
		foo1.setValue("bbb");

		redisCacheTemplate.opsForValue().set(foo.getKey(),foo,3600,TimeUnit.SECONDS);
		redisCacheTemplate.opsForValue().set(foo1.getKey(),foo1,3600,TimeUnit.SECONDS);
		Foo resultFoo = (Foo)redisCacheTemplate.opsForValue().get(foo.getKey());
		Foo resultFoo1 = (Foo)redisCacheTemplate.opsForValue().get(foo1.getKey());
		logger.warn("resultFoo = " + resultFoo.getValue());
		logger.warn("resultFoo1 = " + resultFoo1.getValue());
    }
}
