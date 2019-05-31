package com.bootdo.testDemo;

import com.bootdo.prepareLessons.service.LessionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private LessionsService lessionsService;

    @Test
    public void test() {
       /*  redisTemplate.opsForValue().set("a", "b");
        System.out.println(redisTemplate.opsForValue().get("a"));*/

        /*String dos = lessionsService.selectCatalog(1);
        System.out.println("输出结果:"+dos);*/

        int i = lessionsService.selectCatalogString("语文");
        System.out.println("输出结果:+++++++++++++++++++++++++++++++++>>>>>>>>>>>"+i);
    }

}
