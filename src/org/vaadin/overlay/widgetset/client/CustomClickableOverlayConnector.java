package org.vaadin.overlay.widgetset.client;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import org.vaadin.overlay.CustomClickableOverlay;

/**
 * @author nevinsky
 * @version $Id$
 */
@Connect(CustomClickableOverlay.class)
public class CustomClickableOverlayConnector extends CustomOverlayConnector {
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
    }
}
