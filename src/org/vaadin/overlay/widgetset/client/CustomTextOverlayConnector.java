package org.vaadin.overlay.widgetset.client;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import org.vaadin.overlay.TextOverlay;

/**
 * @author nevinsky
 * @version $Id$
 */
@Connect(TextOverlay.class)
public class CustomTextOverlayConnector extends CustomOverlayConnector {

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
    }
}
