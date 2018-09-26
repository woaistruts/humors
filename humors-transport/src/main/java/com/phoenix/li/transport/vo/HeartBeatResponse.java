package com.phoenix.li.transport.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by 落叶 on 2018/9/10.
 */
@Data
@ToString
public class HeartBeatResponse implements java.io.Serializable {

  private static final long serialVersionUID = 6086326620545655461L;

  private String ip;

  private long time = System.currentTimeMillis();

  private String message = "response";
}
