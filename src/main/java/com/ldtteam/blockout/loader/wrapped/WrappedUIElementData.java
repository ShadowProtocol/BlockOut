package com.ldtteam.blockout.loader.wrapped;

import com.google.inject.Injector;
import com.ldtteam.blockout.loader.core.IUIElementData;
import com.ldtteam.blockout.loader.core.IUIElementMetaData;
import com.ldtteam.blockout.loader.core.component.IUIElementDataComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WrappedUIElementData implements IUIElementData
{

    private final IUIElementData<?> wrapped;

    public WrappedUIElementData(final IUIElementData<?> wrapped) {this.wrapped = wrapped;}

    @Override
    public Injector getFactoryInjector()
    {
        return wrapped.getFactoryInjector();
    }

    @NotNull
    @Override
    public IUIElementMetaData getMetaData()
    {
        return wrapped.getMetaData();
    }

    @Nullable
    @Override
    public Optional getComponentWithName(@NotNull final String name)
    {
        return wrapped.getComponentWithName(name);
    }

    @Override
    public IUIElementDataComponent toDataComponent(@NotNull final IUIElementDataComponent toWriteInto)
    {
        return wrapped.toDataComponent(toWriteInto);
    }
}
