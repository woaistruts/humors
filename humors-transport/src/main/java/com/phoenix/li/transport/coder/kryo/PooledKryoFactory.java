package com.phoenix.li.transport.coder.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import de.javakaffee.kryoserializers.*;
import java.util.Arrays;
import java.util.EnumMap;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by lilongfei on 16/3/29.
 */
public class PooledKryoFactory extends BasePooledObjectFactory<Kryo> {

  @Override
  public Kryo create() throws Exception {
    return createKryo();
  }

  @Override
  public PooledObject<Kryo> wrap(Kryo kryo) {
    return new DefaultPooledObject<Kryo>(kryo);
  }

  private Kryo createKryo() {
    Kryo kryo = new KryoReflectionFactorySupport() {

      @Override
      public Serializer<?> getDefaultSerializer(@SuppressWarnings("rawtypes") final Class clazz) {
        if (EnumMap.class.isAssignableFrom(clazz)) {
          return new EnumMapSerializer();
        }
        if (SubListSerializers.ArrayListSubListSerializer.canSerialize(clazz)
            || SubListSerializers.JavaUtilSubListSerializer.canSerialize(clazz)) {
          return SubListSerializers.createFor(clazz);
        }
        return super.getDefaultSerializer(clazz);
      }
    };
    kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
    UnmodifiableCollectionsSerializer.registerSerializers(kryo);
    return kryo;
  }
}
