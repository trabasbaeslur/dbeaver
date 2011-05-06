package org.jkiss.dbeaver.ui.properties;

import org.eclipse.core.resources.IResource;

/**
* Property type
*/
public enum PropertyType
{
    STRING(String.class),
    BOOLEAN(Boolean.class),
    INTEGER(Long.class),
    NUMERIC(Double.class),
    RESOURCE(IResource.class);

    private final Class<?> valueType;

    PropertyType(Class<?> valueType)
    {
        this.valueType = valueType;
    }

    public Class<?> getValueType()
    {
        return valueType;
    }
}
