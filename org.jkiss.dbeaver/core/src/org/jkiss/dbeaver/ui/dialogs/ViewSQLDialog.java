/*
 * Copyright (c) 2010, Serge Rieder and others. All Rights Reserved.
 */

package org.jkiss.dbeaver.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.*;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.ui.UIUtils;
import org.jkiss.dbeaver.ui.editors.StringEditorInput;
import org.jkiss.dbeaver.ui.editors.SubEditorSite;
import org.jkiss.dbeaver.ui.editors.sql.SQLEditorBase;

public class ViewSQLDialog extends Dialog {

    private IEditorSite subSite;
    private DBPDataSource dataSource;
    private String title;
    private String text;
    private SQLEditorBase sqlViewer;
    private Image image;

    public ViewSQLDialog(final IEditorSite parentSite, DBPDataSource dataSource, String title, String text)
    {
        super(parentSite.getShell());
        this.dataSource = dataSource;
        this.title = title;
        this.text = text;

        this.subSite = new SubEditorSite(parentSite);
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    protected boolean isResizable() {
    	return true;
    }

    protected Control createDialogArea(Composite parent)
    {
        getShell().setText(title);
        if (image != null) {
            getShell().setImage(image);
        }

        Composite composite = (Composite) super.createDialogArea(parent);
        Composite editorPH = new Composite(composite, SWT.BORDER);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 500;
        gd.heightHint = 400;
        editorPH.setLayoutData(gd);
        editorPH.setLayout(new FillLayout());

        sqlViewer = new SQLEditorBase() {
            public DBPDataSource getDataSource()
            {
                return dataSource;
            }
        };
        try {
            sqlViewer.init(subSite, new StringEditorInput(title, text, true));
        } catch (PartInitException e) {
            UIUtils.showErrorDialog(getShell(), title, null, e);
        }
        sqlViewer.createPartControl(editorPH);
        sqlViewer.reloadSyntaxRules();

        composite.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e)
            {
                if (sqlViewer != null) {
                    sqlViewer.dispose();
                    sqlViewer = null;
                }
            }
        });
        return parent;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
    }

}