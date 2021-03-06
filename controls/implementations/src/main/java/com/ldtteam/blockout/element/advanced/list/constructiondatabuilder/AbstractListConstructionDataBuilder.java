package com.ldtteam.blockout.element.advanced.list.constructiondatabuilder;

import com.ldtteam.blockout.binding.dependency.DependencyObjectHelper;
import com.ldtteam.blockout.binding.dependency.IDependencyObject;
import com.ldtteam.blockout.builder.core.IBlockOutGuiConstructionData;
import com.ldtteam.blockout.builder.core.builder.IBlockOutGuiConstructionDataBuilder;
import com.ldtteam.blockout.element.core.AbstractChildrenContainingUIElement;
import com.ldtteam.blockout.proxy.IProxy;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

public abstract class AbstractListConstructionDataBuilder<B extends AbstractListConstructionDataBuilder<B, U>, U extends AbstractChildrenContainingUIElement>
  extends AbstractChildrenContainingUIElement.SimpleControlConstructionDataBuilder<B, U>
{
    public AbstractListConstructionDataBuilder(final String controlId, final IBlockOutGuiConstructionDataBuilder data, final Class<U> controlClass)
    {
        super(controlId,
          data,
          controlClass);
    }

    @NotNull
    public B withDependentTemplateResource(@NotNull final IDependencyObject<ResourceLocation> iconResource)
    {
        return withDependency("templateResource", iconResource);
    }

    @NotNull
    public B withTemplateResource(@NotNull final ResourceLocation iconResource)
    {
        return withDependency("templateResource", DependencyObjectHelper.createFromValue(iconResource));
    }

    @NotNull
    public B withDependentTemplateConstructionData(@NotNull final IDependencyObject<IBlockOutGuiConstructionData> iconConstructionData)
    {
        return withDependency("templateConstructionData", iconConstructionData);
    }

    @SuppressWarnings("unchecked")
    @NotNull
    public final B withTemplateConstructionData(@NotNull final Consumer<IBlockOutGuiConstructionDataBuilder>... callbacks)
    {
        final IBlockOutGuiConstructionDataBuilder builder = IProxy.getInstance().getBuilderManager().getConstructionData();

        Arrays.stream(callbacks).forEach(c -> c.accept(builder));

        return withTemplateConstructionData(builder.build());
    }

    @NotNull
    public B withTemplateConstructionData(@NotNull final IBlockOutGuiConstructionData iconConstructionData)
    {
        return withDependency("templateConstructionData", DependencyObjectHelper.createFromValue(iconConstructionData));
    }

    @NotNull
    public B withDependentSource(@NotNull final IDependencyObject<Object> source)
    {
        return withDependency("source", source);
    }

    @NotNull
    public B withSource(@NotNull final Object source)
    {
        return withDependency("source", DependencyObjectHelper.createFromValue(source));
    }
}
