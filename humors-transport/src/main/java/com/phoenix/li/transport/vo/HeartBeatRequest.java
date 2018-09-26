package com.phoenix.li.transport.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by 落叶 on 2018/9/10.
 */
@Data
@ToString
public class HeartBeatRequest implements java.io.Serializable {

  private static final long serialVersionUID = -2675240368023614809L;

  private final String message = "request";

  private long timestamp = System.currentTimeMillis();
}
