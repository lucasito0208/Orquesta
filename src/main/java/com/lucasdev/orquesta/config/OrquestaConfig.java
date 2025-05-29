package com.lucasdev.orquesta.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrquestaConfig {

    @Bean(initMethod = "start", destroyMethod="close")
    public CuratorFramework curatorFramework() {

        return CuratorFrameworkFactory.newClient(
          "localhost:2181",
                new ExponentialBackoffRetry(1000, 3)
        );

    }
}
