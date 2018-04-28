package com.minecolonies.blockout.management.server.update;

import com.minecolonies.blockout.core.element.IUIElement;
import com.minecolonies.blockout.core.management.IUIManager;
import com.minecolonies.blockout.core.management.update.IUpdateManager;
import com.minecolonies.blockout.element.root.RootGuiElement;
import com.minecolonies.blockout.management.common.update.ChildUpdateManager;
import com.minecolonies.blockout.util.Log;
import org.jetbrains.annotations.NotNull;

public class ServerUpdateManager implements IUpdateManager
{

    @NotNull
    private final IUIManager manager;
    private boolean dirty = false;

    public ServerUpdateManager(@NotNull final IUIManager manager) {this.manager = manager;}

    @Override
    public void updateElement(@NotNull final IUIElement element)
    {
        if (element instanceof RootGuiElement)
        {
            RootGuiElement rootGuiElement = (RootGuiElement) element;
            ChildUpdateManager childUpdateManager = new ChildUpdateManager(this);
            childUpdateManager.updateElement(rootGuiElement);

            if (dirty)
            {
                manager.getNetworkManager().onElementChanged(element);
                dirty = false;
            }
        }
        else
        {
            Log.getLogger().warn("Somebody tried to update a none root element.");
        }
    }

    @Override
    public void markDirty()
    {
        this.dirty = true;
    }
}
