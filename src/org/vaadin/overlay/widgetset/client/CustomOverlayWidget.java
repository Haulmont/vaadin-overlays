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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.WidgetUtil;
import com.vaadin.shared.ui.AlignmentInfo;

import java.util.logging.Logger;

/**
 * Client side widget which communicates with the server. Messages from the
 * server are shown as HTML and mouse clicks are sent to the server.
 */
public class CustomOverlayWidget extends SimplePanel {
    private static Logger log = Logger.getLogger("CustomOverlayWidget");

    public static final String CLASSNAME = "v-customoverlay";

    protected int x;
    protected int y;

    protected Element refCompEl;

    protected AlignmentInfo align = new AlignmentInfo(AlignmentInfo.LEFT, AlignmentInfo.TOP);
    protected AlignmentInfo overlayAlign = new AlignmentInfo(AlignmentInfo.LEFT, AlignmentInfo.TOP);

    protected PopupPanel overlay;
    private int refY;
    private int refX;

    /**
     * The constructor should first call super() to initialize the component and
     * then handle any initialization relevant to Vaadin.
     */
    public CustomOverlayWidget() {
        setWidget(new HTML()); // Seems that we need this one
        overlay = new PopupPanel();
        overlay.addStyleName(CLASSNAME);
        overlay.setAutoHideEnabled(false);
        overlay.setAnimationEnabled(false);
        overlay.setModal(false);

        Event.addNativePreviewHandler(new NativePreviewHandler() {
            public void onPreviewNativeEvent(NativePreviewEvent event) {
                int typeInt = event.getTypeInt();
                // We're only listening for these
                if (typeInt == Event.ONSCROLL) {
                    CustomOverlayWidget.this.updateOverlayPosition();
                }
            }
        });
    }

    protected Widget getOverlayWidget() {
        return overlay.getWidget();
    }

    protected void updateOverlayPosition() {
        if (refCompEl != null) {
            // Calculate the position based on reference component size and the
            // align point.
            refY = refCompEl.getAbsoluteTop();
            refX = refCompEl.getAbsoluteLeft();

            if (align.isBottom()) {
                refY += refCompEl.getOffsetHeight();
            } else if (align.isVerticalCenter()) {
                refY += refCompEl.getOffsetHeight() / 2;
            }
            if (align.isRight()) {
                refX += refCompEl.getOffsetWidth();
            } else if (align.isHorizontalCenter()) {
                refX += refCompEl.getOffsetWidth() / 2;
            }
            // Show popup
            overlay.setPopupPositionAndShow(new PopupPanel.PositionCallback() {

                public void setPosition(int offsetWidth, int offsetHeight) {
                    // Calculate the position based on over component size and
                    // the alignment point.
                    Widget wgt = getOverlayWidget();
                    int w = WidgetUtil.getRequiredWidth(wgt);
                    int h = WidgetUtil.getRequiredHeight(wgt);

                    log.info("POSITION: w=" + w + "h=" + h);

                    int top = refY + y;
                    int left = refX + x;
                    if (overlayAlign.isBottom()) {
                        top -= h;
                    } else if (overlayAlign.isVerticalCenter()) {
                        top -= h / 2;
                    }

                    if (overlayAlign.isRight()) {
                        left -= w;
                    } else if (overlayAlign.isHorizontalCenter()) {
                        left -= w / 2;
                    }
                    log.info("top=" + top + "left=" + left);

                    overlay.setPopupPosition(left, top);
                }
            });
        }
    }

    protected void deferredUpdatePosition() {
        Scheduler scheduler = Scheduler.get();
        scheduler.scheduleDeferred(new Command() {
            public void execute() {
                updateOverlayPosition();
            }
        });
    }

    @Override
    protected void onDetach() {
        if (overlay != null) {
            overlay.hide();
        }
        super.onDetach();
    }

    public void setThemeName(String themeName) {
        overlay.addStyleName(themeName);
    }

    public void hideOverlay() {
        if (overlay != null) {
            overlay.hide();
        }
    }
}