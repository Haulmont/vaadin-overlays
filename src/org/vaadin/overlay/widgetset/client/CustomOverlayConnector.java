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

package org.vaadin.overlay.widgetset.client;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.shared.Connector;
import com.vaadin.shared.ui.AlignmentInfo;
import com.vaadin.shared.ui.Connect;
import org.vaadin.overlay.CustomOverlay;

import java.util.List;

@Connect(CustomOverlay.class)
public class CustomOverlayConnector extends AbstractComponentContainerConnector {
    private static final long serialVersionUID = -1812155693173030620L;

    @Override
    public CustomOverlayWidget getWidget() {
        return (CustomOverlayWidget) super.getWidget();
    }

    @Override
    public CustomOverlayState getState() {
        return (CustomOverlayState) super.getState();
    }

    @Override
    protected void updateWidgetStyleNames() {
        super.updateWidgetStyleNames();

        String themeName = getConnection().getUIConnector().getActiveTheme();
        getWidget().setThemeName(themeName);
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        if (stateChangeEvent.hasPropertyChanged("x")) {
            getWidget().x = getState().x;
        }
        if (stateChangeEvent.hasPropertyChanged("y")) {
            getWidget().y = getState().y;
        }
        if (stateChangeEvent.hasPropertyChanged("alignBitMask")) {
            getWidget().align = new AlignmentInfo(getState().alignBitMask);
        }
        if (stateChangeEvent.hasPropertyChanged("overlayAlignBitMask")) {
            getWidget().overlayAlign = new AlignmentInfo(getState().overlayAlignBitMask);
        }
        if (stateChangeEvent.hasPropertyChanged("component")) {
            Connector connector = getState().component;
            if (connector != null) {
                Widget w = ((ComponentConnector) connector).getWidget();
                if (w instanceof CustomOverlayWidget) {
                    w = ((CustomOverlayWidget) w).getOverlayWidget();
                }
                getWidget().refCompEl = w.getElement();
            }

            List<ComponentConnector> connectors = getChildComponents();
            if (connectors != null && connectors.size() > 0 && connectors.get(0) != null) {
                Widget p = connectors.get(0).getWidget();
                Widget w = getWidget().overlay.getWidget();
                if (p != w && w != null) {
                    getWidget().overlay.clear();
                }
                getWidget().overlay.setWidget(p);
                getWidget().overlay.show();
            } else {
                getWidget().overlay.hide();
            }
        }
        getWidget().deferredUpdatePosition();
    }

    @Override
    public void onUnregister() {
        super.onUnregister();

        VConsole.log(">> Unregister");

        getWidget().hideOverlay();
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        if (getState().component != null) {
            getWidget().deferredUpdatePosition();
        }
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        //No captions for overlays
    }
}