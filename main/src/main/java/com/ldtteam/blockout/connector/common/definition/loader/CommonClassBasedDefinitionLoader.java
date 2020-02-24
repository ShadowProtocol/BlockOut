package com.ldtteam.blockout.connector.common.definition.loader;

import com.ldtteam.blockout.connector.core.IGuiDefinitionLoader;
import org.jetbrains.annotations.NotNull;

public class CommonClassBasedDefinitionLoader implements IGuiDefinitionLoader
{
    private static final long serialVersionUID = 2250422607452719257L;

    @NotNull
    private final String clazzName;

    private CommonClassBasedDefinitionLoader()
    {
        this.clazzName = "";
    }

    public CommonClassBasedDefinitionLoader(final Class<?> clazz) {this.clazzName = clazz.getName();}

    @NotNull
    @Override
    public String getGuiDefinition()
    {
        return String.format("%s.class", clazzName);
    }
}
