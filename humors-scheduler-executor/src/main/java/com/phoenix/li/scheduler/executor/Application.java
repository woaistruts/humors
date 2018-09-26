package com.phoenix.li.scheduler.executor;

import com.phoenix.li.scheduler.executor.config.TransportConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by 落叶 on 2018/9/16.
 */
public class Application {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(TransportConfig.class);
    context.refresh();
  }
}
