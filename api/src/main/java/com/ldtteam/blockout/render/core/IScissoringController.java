package com.ldtteam.blockout.render.core;

import com.ldtteam.blockout.element.IUIElement;
import com.ldtteam.blockout.util.math.BoundingBox;
import org.jetbrains.annotations.NotNull;

public interface IScissoringController
{
    /**
     * Performs a push with the {@link IUIElement#getAbsoluteBoundingBox()} of the given {@link IUIElement}.
     *
     * @param element The element to focus.
     */
    default void focus(@NotNull final IUIElement element)
    {
        push(element.getAbsoluteBoundingBox());
    }

    /**
     * Puts a new Bounding box on the scissoring stack and starts a session.
     *
     * @param box The new box.
     */
    void push(@NotNull final BoundingBox box);

    /**
     * Ends the current scissoring session.
     */
    void pop();

    /**
     * Gives the possibility to push
     */
    void setDebugDrawingEnabled(boolean enabled);
}
