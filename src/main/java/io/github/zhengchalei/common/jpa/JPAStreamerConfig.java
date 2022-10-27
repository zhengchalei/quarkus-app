package io.github.zhengchalei.common.jpa;

import com.speedment.jpastreamer.application.JPAStreamer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class JPAStreamerConfig {


    @Singleton
    public JPAStreamer jpaStreamer(EntityManagerFactory entityManagerFactory) {
        return JPAStreamer.of(entityManagerFactory);
    }

}
