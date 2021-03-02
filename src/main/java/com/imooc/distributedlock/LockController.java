package com.imooc.distributedlock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: bizy
 * @date: 2021/03/02 20:09
 */
@RestController
@Slf4j
public class LockController {

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/lock")
    public void lock(){
        RLock lock = redissonClient.getLock("lock");
        try {
            boolean b = lock.tryLock();
            if (b) {
                log.info("开始下单......");
                Thread.sleep(3000);
                lock.unlock();
            } else {
                log.info("很遗憾......");
            }
//            lock.lock();
//            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
//            log.info("解锁了.....");
        }
    }
}
