package com.ldtteam.blockout.element.simple;

import com.ldtteam.blockout.binding.dependency.DependencyObjectHelper;
import com.ldtteam.blockout.binding.dependency.IDependencyObject;
import com.ldtteam.blockout.builder.core.builder.IBlockOutGuiConstructionDataBuilder;
import com.ldtteam.blockout.element.IUIElementHost;
import com.ldtteam.blockout.element.core.AbstractSimpleUIElement;
import com.ldtteam.blockout.element.drawable.IDrawableUIElement;
import com.ldtteam.blockout.element.values.Alignment;
import com.ldtteam.blockout.element.values.AxisDistance;
import com.ldtteam.blockout.element.values.Dock;
import com.ldtteam.blockout.management.update.IUpdateManager;
import com.ldtteam.blockout.render.core.IRenderingController;
import com.ldtteam.blockout.util.math.Vector2d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

import static com.ldtteam.blockout.util.Constants.Controls.BlockStateIcon.CONST_BLOCK_STATE;
import static com.ldtteam.blockout.util.Constants.Controls.BlockStateIcon.KEY_BLOCKSTATE;

public class BlockStateIcon extends AbstractSimpleUIElement implements IDrawableUIElement
{
    @NotNull
    public IDependencyObject<IBlockState> blockState;

    public BlockStateIcon(
      @NotNull final String id,
      @Nullable final IUIElementHost parent,
      @NotNull final IDependencyObject<IIdentifier> styleId,
      @NotNull final IDependencyObject<EnumSet<Alignment>> alignments,
      @NotNull final IDependencyObject<Dock> dock,
      @NotNull final IDependencyObject<AxisDistance> margin,
      @NotNull final IDependencyObject<Vector2d> elementSize,
      @NotNull final IDependencyObject<Object> dataContext,
      @NotNull final IDependencyObject<Boolean> visible,
      @NotNull final IDependencyObject<Boolean> enabled,
      @NotNull final IDependencyObject<IBlockState> blockState)
    {
        super(KEY_BLOCKSTATE, styleId, id, parent, alignments, dock, margin, elementSize, dataContext, visible, enabled);
        this.blockState = blockState;
    }

    @Override
    public void update(@NotNull final IUpdateManager updateManager)
    {
        super.update(updateManager);

        if (blockState.hasChanged(getDataContext()))
        {
            updateManager.markDirty();
        }
    }

    @Override
    public void drawBackground(@NotNull final IRenderingController controller)
    {
        IOpenGl.pushMatrix();

        controller.drawBlockState(getBlockState(), 0, 0);

        IOpenGl.popMatrix();
    }

    @NotNull
    public IBlockState getBlockState()
    {
        return blockState.get(this);
    }

    public void setBlockState(@NotNull final IBlockState icon)
    {
        this.blockState.set(this, icon);
    }

    @Override
    public void drawForeground(@NotNull final IRenderingController controller)
    {
        //Noop
    }

    public static class BlockStateIconConstructionDataBuilder extends SimpleControlConstructionDataBuilder<BlockStateIconConstructionDataBuilder, BlockStateIcon>
    {

        public BlockStateIconConstructionDataBuilder(
          final String controlId,
          final IBlockOutGuiConstructionDataBuilder data)
        {
            super(controlId, data, BlockStateIcon.class);
        }

        @NotNull
        public BlockStateIconConstructionDataBuilder withDependentBlockState(@NotNull final IDependencyObject<IBlockState> blockState)
        {
            return withDependency("blockState", blockState);
        }

        @NotNull
        public BlockStateIconConstructionDataBuilder withBlockState(@NotNull final IBlockState blockState)
        {
            return withDependency("blockState", DependencyObjectHelper.createFromValue(blockState));
        }
    }

    public static class Factory extends AbstractSimpleUIElementFactory<BlockStateIcon>
    {
        public Factory()
        {
            super(BlockStateIcon.class, KEY_BLOCKSTATE, (elementData, engine, id, parent, styleId, alignments, dock, margin, elementSize, dataContext, visible, enabled) -> {
                final IDependencyObject<IBlockState> blockState = elementData.getFromRawDataWithDefault(CONST_BLOCK_STATE, engine, IBlockState.defaultState());

                final BlockStateIcon element = new BlockStateIcon(
                  id,
                  parent,
                  styleId,
                  alignments,
                  dock,
                  margin,
                  elementSize,
                  dataContext,
                  visible,
                  enabled,
                  blockState
                );

                return element;
            }, (element, builder) -> builder.addComponent(CONST_BLOCK_STATE, element.getBlockState()));
        }
    }
}
