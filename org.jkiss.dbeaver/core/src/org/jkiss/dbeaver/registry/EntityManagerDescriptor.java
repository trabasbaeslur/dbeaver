/*
 * Copyright (c) 2010, Serge Rieder and others. All Rights Reserved.
 */

package org.jkiss.dbeaver.registry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.jkiss.dbeaver.ext.IDatabaseObjectManager;

/**
 * EntityEditorDescriptor
 */
public class EntityManagerDescriptor extends AbstractDescriptor
{
    private String id;
    private String className;
    private String objectType;
    private String name;

    private Class objectClass;
    private Class editorClass;

    public EntityManagerDescriptor(IConfigurationElement config)
    {
        super(config.getContributor());

        this.id = config.getAttribute("id");
        this.className = config.getAttribute("class");
        this.objectType = config.getAttribute("objectType");
        this.name = config.getAttribute("label");
    }

    public String getId()
    {
        return id;
    }

/*
    public String getClassName()
    {
        return className;
    }

    public String getObjectType()
    {
        return objectType;
    }

*/
    public String getName()
    {
        return name;
    }

    public boolean appliesToType(Class objectType)
    {
        return this.getObjectClass() != null && this.getObjectClass().isAssignableFrom(objectType);
    }

    public Class getObjectClass()
    {
        if (objectClass == null) {
            objectClass = getObjectClass(objectType);
        }
        return objectClass;
    }

    public Class getEditorClass()
    {
        if (editorClass == null) {
            editorClass = getObjectClass(className);
        }
        return editorClass;
    }

    public IDatabaseObjectManager<?> createMannager()
    {
        Class clazz = getEditorClass();
        if (clazz == null) {
            return null;
        }
        try {
            return (IDatabaseObjectManager) clazz.newInstance();
        } catch (Exception ex) {
            log.error("Error instantiating entity manager '" + className + "'", ex);
            return null;
        }
    }
}