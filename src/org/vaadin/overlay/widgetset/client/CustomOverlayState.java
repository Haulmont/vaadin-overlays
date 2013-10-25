package org.vaadin.overlay.widgetset.client;

import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AlignmentInfo;

/**
 * @author nevinsky
 * @version $Id$
 */
public class CustomOverlayState extends AbstractComponentState {
    public int x = 0;
    public int y = 0;
    public int alignBitMask = AlignmentInfo.TOP_LEFT.getBitMask();
    public int overlayAlignBitMask = AlignmentInfo.TOP_LEFT.getBitMask();

    public Connector component;
}
