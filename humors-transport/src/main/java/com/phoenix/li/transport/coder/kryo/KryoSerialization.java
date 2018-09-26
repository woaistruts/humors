package com.phoenix.li.transport.coder.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.phoenix.li.transport.coder.Serialization;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by lilongfei on 16/3/29.
 */
public class KryoSerialization implements Serialization {

  private final KryoFactory kryoFactory;

  public KryoSerialization(final KryoFactory kryoFactory) {
    this.kryoFactory = kryoFactory;
  }

  @Override
  public void serialize(final OutputStream out, final Object message) throws IOException {
    Kryo kryo = kryoFactory.getKryo();
    Output output = new Output(out);
    kryo.writeClassAndObject(output, message);
    output.close();
    kryoFactory.returnKryo(kryo);
  }

  @Override
  public Object deserialize(final InputStream in) throws IOException {
    Kryo kryo = kryoFactory.getKryo();
    Input input = new Input(in);
    Object result = kryo.readClassAndObject(input);
    input.close();
    kryoFactory.returnKryo(kryo);
    return result;
  }
}
