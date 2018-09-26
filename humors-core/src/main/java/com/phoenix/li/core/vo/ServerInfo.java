package com.phoenix.li.core.vo;

import lombok.Data;
import lombok.ToString;

/**
 * Created by 落叶 on 2018/9/17.
 */
@Data
@ToString
public class ServerInfo implements java.io.Serializable {

  private static final long serialVersionUID = 8722434741202430096L;

  private String ip;

  private int port;
}
