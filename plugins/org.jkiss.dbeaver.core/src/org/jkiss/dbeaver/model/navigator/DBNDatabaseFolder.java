/*
 * Copyright (C) 2010-2012 Serge Rieder
 * serge@jkiss.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.jkiss.dbeaver.model.navigator;

import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.meta.Property;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSFolder;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSWrapper;
import org.jkiss.dbeaver.registry.tree.DBXTreeFolder;
import org.jkiss.dbeaver.registry.tree.DBXTreeNode;
import org.jkiss.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DBNDatabaseFolder
 */
public class DBNDatabaseFolder extends DBNDatabaseNode implements DBNContainer, DBSFolder
{
    private DBXTreeFolder meta;

    DBNDatabaseFolder(DBNNode parent, DBXTreeFolder meta)
    {
        super(parent);
        this.meta = meta;
        DBNModel.getInstance().addNode(this);
    }

    @Override
    protected void dispose(boolean reflect)
    {
        DBNModel.getInstance().removeNode(this, reflect);
        super.dispose(reflect);
    }

    @Override
    public DBXTreeFolder getMeta()
    {
        return meta;
    }

    @Override
    protected void reloadObject(DBRProgressMonitor monitor, DBSObject object) {
        // do nothing
    }

    @Override
    public DBSObject getObject()
    {
        return this;
    }

    @Override
    public Object getValueObject()
    {
        return getParentNode() instanceof DBNDatabaseNode ? ((DBNDatabaseNode)getParentNode()).getValueObject() : null;
    }

    @Override
    public String getChildrenType()
    {
        final List<DBXTreeNode> metaChildren = meta.getChildren(this);
        if (CommonUtils.isEmpty(metaChildren)) {
            return "?";
        } else {
            return metaChildren.get(0).getChildrenType(getDataSource());
        }
    }

    @Override
    @Property(viewable = true)
    public String getName()
    {
        return meta.getChildrenType(getDataSource());
    }

    @Override
    public String getDescription()
    {
        return meta.getDescription();
    }

    @Override
    public DBSObject getParentObject()
    {
        return getParentNode() instanceof DBSWrapper ? ((DBSWrapper)getParentNode()).getObject() : null;
    }

    @Override
    public DBPDataSource getDataSource()
    {
        return getParentObject() == null ? null : getParentObject().getDataSource();
    }

    @Override
    public boolean isPersisted()
    {
        return getParentNode() != null && getParentNode().isPersisted();
    }

    @Override
    public Class<? extends DBSObject> getChildrenClass()
    {
        String itemsType = CommonUtils.toString(meta.getType());
        if (CommonUtils.isEmpty(itemsType)) {
            return null;
        }
        Class<DBSObject> aClass = meta.getSource().getObjectClass(itemsType, DBSObject.class);
        if (aClass == null) {
            log.error("Items class '" + itemsType + "' not found");
            return null;
        }
        if (!DBSObject.class.isAssignableFrom(aClass)) {
            log.error("Class '" + aClass.getName() + "' doesn't extend DBSObject");
            return null;
        }
        return aClass ;
    }

    @Override
    public Collection<DBSObject> getChildrenObjects(DBRProgressMonitor monitor) throws DBException
    {
        List<DBNDatabaseNode> children = getChildren(monitor);
        List<DBSObject> childObjects = new ArrayList<DBSObject>();
        if (!CommonUtils.isEmpty(children)) {
            for (DBNDatabaseNode child : children) {
                childObjects.add(child.getObject());
            }
        }
        return childObjects;
    }

}
