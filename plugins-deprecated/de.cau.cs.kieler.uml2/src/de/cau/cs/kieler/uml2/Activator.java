/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.uml2;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author haf
 * @kieler.ignore (excluded from review process)
 * 
 */
public class Activator extends Plugin {

    /** The plug-in ID. **/
    public static final String PLUGIN_ID = "de.cau.cs.kieler.synccharts.ksbase";

    /** The shared instance. **/
    private static Activator plugin;

    /**
     * The constructor.
     */
    public Activator() {
    }

    /**
     * Starts the plug-in.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     * @param context
     *            The bundle context for this plug-in
     * @throws Exception
     *             When starting this plug-in fails.
     * 
     */
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        Activator.setDefault(this);
    }

    /**
     * Stops the plug-in.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     * @param context
     *            The bundle context for this plug-in
     * @throws Exception
     *             When stopping this plug-in fails.
     */
    public void stop(final BundleContext context) throws Exception {
        Activator.setDefault(null);
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }
    
    /**
     * Sets the shared instance.
     * @param value The new shared instance value
     */
    public static void setDefault(final Activator value) { 
        plugin = value;
    }
}