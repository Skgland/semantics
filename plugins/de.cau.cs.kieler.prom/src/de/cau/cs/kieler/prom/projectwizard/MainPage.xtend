/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2015 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.prom.projectwizard

import de.cau.cs.kieler.prom.common.EnvironmentData
import de.cau.cs.kieler.prom.common.PromPlugin
import de.cau.cs.kieler.prom.common.ui.UIUtil
import de.cau.cs.kieler.prom.environments.PromEnvironmentsInitializer
import java.util.List
import org.eclipse.jface.viewers.ComboViewer
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.jface.wizard.WizardPage
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite

/**
 * The main page for the project wizard.
 * It contains a control to select the environment to use
 * and the wrapper code snippets to import in the new project.
 * 
 * @author aas
 */
class MainPage extends WizardPage {

    /**
     * The environments loaded from this plugins preference store.
     */
    private List<EnvironmentData> environments

    /**
     * The combobox with the environments.
     */
    private ComboViewer environmentsCombo

    /**
     * Checkbox to specify if a model file (e.g. SCT) should be created and initialized
     */
    private Button createModelFileCheckbox
    
    /**
     * Checkbox to specify if a main file, containing wrapper code, should be created and initialized
     */
    private Button createMainFileCheckbox
    
    /**
     * Checkbox to specify the snippets directory should be initialized with the settings from the environment
     */
    private Button importSnippetsCheckbox
    
    /**
     * Creates a new instance of this class with the given page name as title.
     * 
     * @param pageName The title for the page
     */
    new(String pageName) {
        super(pageName)

        title = pageName
        description = "Set the environment for the project."
    }

    /**
     * {@inheritDoc}
     */
    override createControl(Composite parent) {
        var Composite comp = new Composite(parent, SWT.NONE)
        setControl(comp)

        comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL))
        comp.setLayout(new GridLayout())

        loadEnvironments()

        createEnvironmentsComponent(comp)
        createProjectInitializationComponent(comp)
        
        pageComplete = true
    }

    /**
     * Loads the environments from this plugin's preference store. 
     */
    private def void loadEnvironments() {
        val store = PromPlugin.^default.preferenceStore

        // It might be that on a new installation there are no environments initialized.
        // So we do it here manually.
        if (EnvironmentData.isPreferenceStoreEmpty(store))
            PromEnvironmentsInitializer.initializeDefaultEnvironments()

        // Load environments from store
        environments = EnvironmentData.loadAllFromPreferenceStore(store)
    }

    /**
     * Creates a group with the environments combobox.
     * 
     * @param parent The parent compisite
     */
    private def void createEnvironmentsComponent(Composite parent) {
        val group = UIUtil.createGroup(parent, "Environment", 1)

        environmentsCombo = UIUtil.createEnvironmentsCombo(group, environments)

        // Information label
        UIUtil.createLabel(group, "The environment specifies the following project wizard\n" +
            "and is used to initialize launches and resources.\nEnvironments are configured in the preferences.")
    }

    /**
     * Creates a group with a checkbox to specify if wrapper code snippets should be imported.
     * 
     * @param parent The parent composite
     */
    private def void createProjectInitializationComponent(Composite parent) {
        val group = UIUtil.createGroup(parent, "Project initialization", 1)

        // Wrapper code components
        importSnippetsCheckbox = UIUtil.createCheckButton(group, "Import wrapper code snippets", true);
        importSnippetsCheckbox.toolTipText = "Wrapper code snippets of the selected environment\n" +
            "will be copied to the new project."
        
        // Create model file components     
        createModelFileCheckbox = UIUtil.createCheckButton(group, "Create model file", true);
        createModelFileCheckbox.toolTipText = "Specify if the project should contain an initialized model file."
        
        // Create main file components
        createMainFileCheckbox = UIUtil.createCheckButton(group, "Create main file", true);
        createMainFileCheckbox.toolTipText = "Specify if a file with wrapper code should be created and initialized."
    }
    
    /**
     * @return the selected environment in the combobox.
     */
    public def EnvironmentData getSelectedEnvironment() {
        val selection = environmentsCombo.getSelection();
        if (!selection.isEmpty()) {
            val structuredSelection = selection as IStructuredSelection
            return structuredSelection.getFirstElement() as EnvironmentData
        } else {
            return null
        }
    }

    /**
     * @return the related project wizard class name of the selected environment in the combobox<br />
     *         or an empty string if there is no environment selected. 
     */
    public def String getEnvironmentWizardClassName() {
        val env = getSelectedEnvironment()
        if (env != null) {
            return env.relatedProjectWizardClass
        } else {
            return ""
        }
    }

    /**
     * Returns weather a main file should be created.
     * 
     * @return the selection state of the respective checkbox
     */
    public def boolean isCreateMainFile(){
        return createMainFileCheckbox.selection
    }
    
    /**
     * Returns weather a model file should be created.
     * 
     * @return the selection state of the respective checkbox
     */
    public def boolean isCreateModelFile(){
        return createModelFileCheckbox.selection
    }

    /**
     * Returns weather the wrapper code snippets should be initialized. 
     * 
     * @return the selection state of the respective checkbox
     */
    public def boolean isImportSnippets(){
        return importSnippetsCheckbox.selection
    }

}