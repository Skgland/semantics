package de.cau.cs.kieler.synccharts.diagram.part;

import java.util.StringTokenizer;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

/**
 * @generated
 */
public class SyncchartsCreationWizardPage extends WizardNewFileCreationPage {

    /**
     * @generated
     */
    private final String fileExtension;

    /**
     * @generated
     */
    public SyncchartsCreationWizardPage(String pageName, IStructuredSelection selection,
            String fileExtension) {
        super(pageName, selection);
        this.fileExtension = fileExtension;
    }

    /**
     * Override to create files with this extension.
     * 
     * @generated
     */
    protected String getExtension() {
        return fileExtension;
    }

    /**
     * Override to create files with this extension.
     * 
     * @generated not
     */
    protected String getDefaultExtension() {
        return fileExtension.split(",")[0];
    }
    
    
    /**
     * @generated
     */
    public URI getURI() {
        return URI.createPlatformResourceURI(getFilePath().toString(), false);
    }

    /**
     * @generated
     */
    protected IPath getFilePath() {
        IPath path = getContainerFullPath();
        if (path == null) {
            path = new Path(""); //$NON-NLS-1$
        }
        System.out.println("");
        String fileName = getFileName();
        if (fileName != null) {
            path = path.append(fileName);

            if (path.getFileExtension() == null) {
                path = path.addFileExtension(fileExtension.split(",")[0]);
            }
        }
        return path;
    }

    /**
     * @generated
     */
    public void createControl(Composite parent) {
        super.createControl(parent);
        setFileName(SyncchartsDiagramEditorUtil.getUniqueFileName(getContainerFullPath(),
                getFileName(), getDefaultExtension()));
        setPageComplete(validatePage());
    }

 
    
    /**
     * @generated
     */
    protected boolean validatePage() {
        if (!super.validatePage()) {
            return false;
        }

        String extension = getExtension();
        if (extension != null) {
            StringTokenizer t = new StringTokenizer(extension, ",");
            String ext = null;
            while (t.hasMoreTokens()) {
                ext = t.nextToken();
                if (getFilePath().toString().endsWith("." + ext)) {
                    return true;
                }
            }
            setErrorMessage(NLS
                    .bind(Messages.SyncchartsCreationWizardPageExtensionError, extension));
        }
        return false;

    }
}