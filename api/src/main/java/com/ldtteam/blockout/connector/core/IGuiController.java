package com.ldtteam.blockout.connector.core;

import com.ldtteam.blockout.connector.core.builder.IGuiKeyBuilder;
import com.ldtteam.blockout.element.IUIElementHost;
import com.ldtteam.blockout.proxy.ProxyHolder;
import com.ldtteam.jvoxelizer.entity.living.player.IPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Consumer;

public interface IGuiController
{
    static IGuiController getInstance()
    {
        return ProxyHolder.getInstance().getGuiController();
    }

    void openUI(@NotNull final IPlayerEntity player, @NotNull final Consumer<IGuiKeyBuilder>... guiKeyBuilderConsumer);

    void openUI(@NotNull final IPlayerEntity player, @NotNull final IGuiKey key);

    void openUI(@NotNull final UUID playerId, @NotNull final Consumer<IGuiKeyBuilder>... guiKeyBuilderConsumer);

    void openUI(@NotNull final UUID playerId, @NotNull final IGuiKey key);

    void closeUI(@NotNull final IPlayerEntity player);

    void closeUI(@NotNull final UUID playerId);

    @Nullable
    IGuiKey getOpenUI(@NotNull final IPlayerEntity player);

    @Nullable
    IGuiKey getOpenUI(@NotNull final UUID player);

    @Nullable
    IUIElementHost getRoot(@NotNull final IGuiKey guiKey);
}
