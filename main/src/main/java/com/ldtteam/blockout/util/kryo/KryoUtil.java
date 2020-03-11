package com.ldtteam.blockout.util.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.ldtteam.blockout.loader.object.ObjectUIElementData;
import org.objenesis.strategy.SerializingInstantiatorStrategy;
import org.objenesis.strategy.StdInstantiatorStrategy;

public final class KryoUtil
{

    private KryoUtil()
    {
        throw new IllegalArgumentException("Utility Class");
    }

    /**
     * Creates a new kryo instance to use.
     */
    public static Kryo createNewKryo()
    {
        final Kryo instance = new Kryo();
        instance.setInstantiatorStrategy(
          new BlockOutInstantiationStrategy(
            new DefaultInstantiatorStrategy(),
            new SerializingInstantiatorStrategy(),
            new StdInstantiatorStrategy()
          )
        );

        instance.setRegistrationRequired(false);
        instance.setClassLoader(KryoUtil.class.getClassLoader());

        instance.register(ObjectUIElementData.class);

        return instance;
    }
}