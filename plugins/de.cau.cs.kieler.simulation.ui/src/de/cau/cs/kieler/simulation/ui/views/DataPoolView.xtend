/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.simulation.ui.views

import de.cau.cs.kieler.simulation.core.DataPool
import de.cau.cs.kieler.simulation.core.Model
import de.cau.cs.kieler.simulation.core.Variable
import java.util.List
import org.eclipse.jface.action.Action
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Table
import org.eclipse.ui.IWorkbenchPart
import org.eclipse.ui.part.ViewPart

/**
 * @author aas
 *
 */
class DataPoolView extends ViewPart {
    
    public static var DataPoolView instance
    
    var TableViewer viewer
    
    var TableViewerColumn variableColumn
    var TableViewerColumn valueColumn
    var TableViewerColumn historyColumn
    var TableViewerColumn inputColumn
    var TableViewerColumn outputColumn
    
    /**
     * @see IWorkbenchPart#createPartControl(Composite)
     */
     override createPartControl(Composite parent) {
        // Remember the instance
        instance = this
         
        // Create viewer.
        viewer = createDataPoolTable(parent);

        // Create menu and toolbars.
        createMenu();
        createToolbar();
    }
    
    override setFocus() {
    }
    
    public def void setDataPool(DataPool pool) {
        if(pool == null) {
            viewer.input = null
        } else {
            val List<Object> inputs = newArrayList()
            for(m : pool.models) {
                inputs += m
                for(v : m.variables) {
                    inputs += v
                }
            }
            viewer.input = inputs
        }
    }
    
    /**
     * Create menu.
     */
    private def void createMenu() {
        val mgr = getViewSite().getActionBars().getMenuManager();
        mgr.add(new ToggleColumnVisibleAction(historyColumn));
        mgr.add(new ToggleColumnVisibleAction(inputColumn));
        mgr.add(new ToggleColumnVisibleAction(outputColumn));
    }
    
    /**
     * Create toolbar.
     */
    private def void createToolbar() {
        val mgr = getViewSite().getActionBars().getToolBarManager();
        mgr.add(new Action("Reset Value"){
            override run(){
                val variable = viewer.structuredSelection.firstElement as Variable
                if(variable != null) {
                    variable.userValue = null
                    viewer.update(variable, null)
                } 
            }
        });
    }
    
    private def TableViewer createDataPoolTable(Composite parent) {
        val table = new Table(parent, SWT.BORDER.bitwiseOr(SWT.FULL_SELECTION))
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        // Create viewer
        val viewer = new TableViewer(table)
        
        // Create columns
        variableColumn = createTableColumn(viewer, "Variable", 120, true)
        variableColumn.labelProvider = new DataPoolViewerColumn() {
            override String getText(Object element) {
                if(element instanceof Variable) {
                    if(element.isDirty)
                        return element.name+"*"
                    else
                        return element.name
                } else if(element instanceof Model) { 
                    return "-----" + element.name + "-----"
                }
            }
        };
        valueColumn = createTableColumn(viewer, "Value", 80, true)
        valueColumn.labelProvider = new DataPoolViewerColumn() {
            override String getText(Object element) {
                 if(element instanceof Variable) {
                    if(element.isDirty)
                        return "* "+element.userValue?.toString
                    else
                        return element.value?.toString
                }
                return ""
            }
        };
        historyColumn = createTableColumn(viewer, "History", 80, true)
        historyColumn.labelProvider = new DataPoolViewerColumn() {
            override String getText(Object element) {
                var txt = ""
                if(element instanceof Variable) {
                    val history = element.history
                    var size = history.size()
                    val max = 6
                    if(size > max) {
                        txt += "..."
                    }
                    for(var i = size - Math.min(size, max); i < size-1; i++) {
                        val v = history.get(i)
                        txt += v.value
                        if(i < history.size()-2)
                            txt += ", "
                    }
                }                    
                return txt
            }
        };
        inputColumn = createTableColumn(viewer, "Is Input", 80, false)
        inputColumn.labelProvider = new DataPoolViewerColumn() {
            override String getText(Object element) {
                if(element instanceof Variable)
                    return String.valueOf(element.isInput)
                return ""
            }
        };
        outputColumn = createTableColumn(viewer, "Is Output", 80, false)
        outputColumn.labelProvider = new DataPoolViewerColumn() {
            override String getText(Object element) {
                if(element instanceof Variable)
                    return String.valueOf(element.isOutput)
                return ""
            }
        };

        // Create content
        viewer.setContentProvider(ArrayContentProvider.instance);
        viewer.input = newArrayList()
        
        // Make cells editable
        valueColumn.editingSupport = new ValueColumnEditingSupport(viewer)
        
        return viewer
    }
    
    /**
     * Creates a column for a table viewer with the given title and width.
     * 
     * @param viewer The TableViewer this column is added to
     * @param title The title for this column
     * @param width The width of this column
     * @return the created column.
     */
    private static def TableViewerColumn createTableColumn(TableViewer viewer, String title, int width, boolean visible) {
        val viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
        val column = viewerColumn.getColumn()
        column.setText(title);
        column.setMoveable(true);
        
        if(visible) {
            column.width = width
            column.resizable = true
        } else {
            column.width = 0
            column.resizable = false
        }
        return viewerColumn
    }
}