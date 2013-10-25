package org.vaadin.overlay.widgetset.client;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import org.vaadin.overlay.ImageOverlay;

/**
 * @author nevinsky
 * @version $Id$
 */
@Connect(ImageOverlay.class)
public class ImageOverlayConnector extends CustomOverlayConnector {

    public ImageOverlayConnector() {
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
    }
}
