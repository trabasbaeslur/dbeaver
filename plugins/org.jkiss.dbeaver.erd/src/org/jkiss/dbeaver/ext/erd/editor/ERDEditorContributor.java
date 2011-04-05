/*
 * Copyright (c) 2011, Serge Rieder and others. All Rights Reserved.
 */

package org.jkiss.dbeaver.ext.erd.editor;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.EditorActionBarContributor;
import org.jkiss.dbeaver.ui.editors.entity.EntityEditorContributor;

/**
 * SQL Editor contributor
 */
public class ERDEditorContributor extends EditorActionBarContributor
{
    private ERDEditorPart activeEditorPart;


    public ERDEditorContributor()
    {
        super();

        createActions();
    }

    private void createActions()
    {
    }

    public void dispose()
    {
        setActiveEditor(null);

        super.dispose();
    }

    public void setActiveEditor(IEditorPart targetEditor)
    {
        super.setActiveEditor(targetEditor);

        if (activeEditorPart == targetEditor) {
            return;
        }
        activeEditorPart = (ERDEditorPart)targetEditor;

        if (activeEditorPart != null) {
            // Update editor actions


        }
        EntityEditorContributor.registerSearchActions(activeEditorPart);
    }

    public void init(IActionBars bars)
    {
        super.init(bars);
    }

    public void contributeToMenu(IMenuManager manager)
    {
        super.contributeToMenu(manager);

        IMenuManager editMenu = manager.findMenuUsingPath(IWorkbenchActionConstants.M_EDIT);
        if (editMenu != null) {
            //editMenu.add(new Separator());
            //editMenu.add(new Separator());
            //editMenu.add(executeStatementAction);
            //editMenu.add(executeScriptAction);
        }
    }

    public void contributeToCoolBar(ICoolBarManager manager)
    {
        super.contributeToCoolBar(manager);
    }

    public void contributeToStatusLine(IStatusLineManager statusLineManager)
    {
        super.contributeToStatusLine(statusLineManager);
    }

}
