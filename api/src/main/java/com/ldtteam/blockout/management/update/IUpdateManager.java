package com.ldtteam.blockout.management.update;

import com.ldtteam.blockout.element.IUIElement;
import org.jetbrains.annotations.NotNull;

public interface IUpdateManager
{

    /**
     * Updates the given element.
     *
     * @param element The element.
     */
    void updateElement(@NotNull final IUIElement element);

    /**
     * Mark this update inventory dirty.
     */
    void markDirty();
}
