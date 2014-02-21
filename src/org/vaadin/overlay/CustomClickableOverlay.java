/*
 * Copyright 2010 Sami Ekblad, 2013 Haulmont Development
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vaadin.overlay;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

/**
 * Server-side class for creating image overlays that can be clicked. This class
 * is used to implement click support to {@link TextOverlay} and
 * {@link ImageOverlay} but it can be used for other similar Overlays that need
 * the click support.
 *
 * @author Sami Ekblad
 */
@SuppressWarnings("serial")
public class CustomClickableOverlay extends CustomOverlay {
    private static final long serialVersionUID = -354623604620366005L;
    private CssLayout layout = new CssLayout();
    private Component realOverlay;
    private OverlayClickListener clickListener;

    public CustomClickableOverlay() {
        layout.addLayoutClickListener(new ClickListener());
        super.setOverlay(layout);
    }

    /**
     * Create new overlay for a component.
     *
     * @param overlay
     * @param referenceComponent
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public CustomClickableOverlay(Component overlay, Component referenceComponent) {
        this();
        setComponent(referenceComponent);
        setOverlay(overlay);
    }

    @Override
    public Component getOverlay() {
        return realOverlay;
    }

    @Override
    public void setOverlay(Component overlay) {
        realOverlay = overlay;
        layout.removeAllComponents();
        if (realOverlay != null) {
            layout.addComponent(realOverlay);
        }
    }

    /**
     * Set a click listener for to receive the overlay click events.
     *
     * @param clickListener
     */
    public void setClickListener(OverlayClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * Get a click listener for to receive the overlay click events.
     *
     * @return clickListener
     */
    public OverlayClickListener getClickListener() {
        return clickListener;
    }

    /**
     * Implementation of the LayoutClickListener to receive the clicks.
     */
    private class ClickListener implements LayoutClickListener {
        private static final long serialVersionUID = -3844465450135961729L;

        public void layoutClick(LayoutClickEvent event) {
            if (clickListener != null) {
                clickListener.overlayClicked(CustomClickableOverlay.this);
            }
        }
    }

}
