package com.phoenix.li.transport.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 落叶 on 2018/9/10. 序列化反序列化接口
 */
public interface Serialization {

  void serialize(OutputStream out, Object message) throws IOException;

  Object deserialize(InputStream in) throws IOException;
}
