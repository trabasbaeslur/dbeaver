/*
 * Copyright (C) 2010-2012 Serge Rieder serge@jkiss.org
 * Copyright (C) 2012 Eugene Fradkin eugene.fradkin@gmail.com
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
package org.jkiss.dbeaver.ui.preferences;

/**
 * PrefConstants
 */
public final class PrefConstants
{
    public static final String TEXT_EDIT_UNDO_LEVEL = "text.edit.undo.level"; //$NON-NLS-1$

    public static final String RS_EDIT_MAX_TEXT_SIZE = "resultset.edit.maxtextsize"; //$NON-NLS-1$
    public static final String RS_EDIT_LONG_AS_LOB = "resultset.edit.longaslob"; //$NON-NLS-1$
    public static final String RS_COMMIT_ON_EDIT_APPLY = "resultset.commit.oneditapply"; //$NON-NLS-1$
    public static final String RS_COMMIT_ON_CONTENT_APPLY = "resultset.commit.oncontentapply"; //$NON-NLS-1$

    public static final String CONFIRM_EXIT = "exit"; //$NON-NLS-1$
    public static final String CONFIRM_ORDER_RESULTSET = "order_resultset"; //$NON-NLS-1$
    public static final String CONFIRM_RS_EDIT_CLOSE = "close_resultset_edit"; //$NON-NLS-1$
    public static final String CONFIRM_TXN_DISCONNECT = "disconnect_txn"; //$NON-NLS-1$
    public static final String CONFIRM_ENTITY_EDIT_CLOSE = "close_entity_edit"; //$NON-NLS-1$
    public static final String CONFIRM_ENTITY_DELETE = "entity_delete"; //$NON-NLS-1$
    public static final String CONFIRM_ENTITY_REJECT = "entity_reject"; //$NON-NLS-1$
    public static final String CONFIRM_ENTITY_REVERT = "entity_revert"; //$NON-NLS-1$
    //public static final String CONFIRM_ENTITY_RENAME = "entity_rename"; //$NON-NLS-1$
    public static final String CONFIRM_EDITOR_CLOSE = "close_editor_edit"; //$NON-NLS-1$
    public static final String CONFIRM_DRIVER_DOWNLOAD = "driver_download"; //$NON-NLS-1$
    public static final String CONFIRM_VERSION_CHECK = "version_check"; //$NON-NLS-1$
    public static final String CONFIRM_KEEP_STATEMENT_OPEN = "keep_statement_open"; //$NON-NLS-1$

    // Hex editor font identifiers
    public static final String HEX_FONT_NAME = "hex.font.name"; //$NON-NLS-1$
    public static final String HEX_FONT_SIZE = "hex.font.size"; //$NON-NLS-1$
    public static final String HEX_FONT_STYLE = "hex.font.style"; //$NON-NLS-1$

    // General UI
    public static final String UI_AUTO_UPDATE_CHECK = "ui.auto.update.check"; //$NON-NLS-1$
    public static final String UI_UPDATE_CHECK_TIME = "ui.auto.update.check.time"; //$NON-NLS-1$
    public static final String UI_DRIVERS_HOME = "ui.drivers.home"; //$NON-NLS-1$
    public static final String UI_PROXY_HOST = "ui.proxy.host"; //$NON-NLS-1$
    public static final String UI_PROXY_PORT = "ui.proxy.port"; //$NON-NLS-1$
    public static final String UI_PROXY_USER = "ui.proxy.user"; //$NON-NLS-1$
    public static final String UI_PROXY_PASSWORD = "ui.proxy.password"; //$NON-NLS-1$
    public static final String UI_DRIVERS_SOURCES = "ui.drivers.sources"; //$NON-NLS-1$
}
