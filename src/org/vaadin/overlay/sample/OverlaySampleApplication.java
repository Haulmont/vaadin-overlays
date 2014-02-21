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

package org.vaadin.overlay.sample;

import com.vaadin.server.ClassResource;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.overlay.CustomClickableOverlay;
import org.vaadin.overlay.ImageOverlay;
import org.vaadin.overlay.OverlayClickListener;
import org.vaadin.overlay.TextOverlay;

public class OverlaySampleApplication extends UI {

    private static final long serialVersionUID = 7831853143147236971L;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();

        final Label label = new Label("Alignment.TOP_LEFT");
        layout.addComponent(label);

        for (int i = 0; i < 20; i++) {

            Button button = new Button("Sample Button");
            layout.addComponent(button);

            final ImageOverlay io = new ImageOverlay(button);

            Resource res = new ClassResource(this.getClass(), "../icon-new.png");
            io.setImage(res);
            io.setComponentAnchor(Alignment.TOP_LEFT); // Top left of the button
            io.setOverlayAnchor(Alignment.MIDDLE_CENTER); // Center of the image
            io.setClickListener(new OverlayClickListener() {
                public void overlayClicked(CustomClickableOverlay overlay) {
                    Notification.show("ImageOverlay Clicked!");
                }
            });
            layout.addComponent(io);
            io.setEnabled(true);

            final TextOverlay to = new TextOverlay(button, "New!");
            to.setComponentAnchor(Alignment.MIDDLE_RIGHT); // Top right of the button
            to.setOverlayAnchor(Alignment.MIDDLE_CENTER); // Center of the image
            to.setClickListener(new OverlayClickListener() {
                public void overlayClicked(CustomClickableOverlay overlay) {
                    Notification.show("TextOverlay Clicked!");
                }
            });
            layout.addComponent(to);

            button.addClickListener(new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                    Alignment a = io.getComponentAnchor();
                    String s = "";
                    if (Alignment.TOP_LEFT.equals(a)) {
                        a = Alignment.TOP_CENTER;
                        s = "TOP_CENTER";
                    } else if (Alignment.TOP_CENTER.equals(a)) {
                        a = Alignment.TOP_RIGHT;
                        s = "TOP_RIGHT";
                    } else if (Alignment.TOP_RIGHT.equals(a)) {
                        a = Alignment.MIDDLE_RIGHT;
                        s = "MIDDLE_RIGHT";
                    } else if (Alignment.MIDDLE_RIGHT.equals(a)) {
                        a = Alignment.BOTTOM_RIGHT;
                        s = "BOTTOM_RIGHT";
                    } else if (Alignment.BOTTOM_RIGHT.equals(a)) {
                        a = Alignment.BOTTOM_CENTER;
                        s = "BOTTOM_CENTER";
                    } else if (Alignment.BOTTOM_CENTER.equals(a)) {
                        a = Alignment.BOTTOM_LEFT;
                        s = "BOTTOM_LEFT";
                    } else if (Alignment.BOTTOM_LEFT.equals(a)) {
                        a = Alignment.MIDDLE_LEFT;
                        s = "MIDDLE_LEFT";
                    } else if (Alignment.MIDDLE_LEFT.equals(a)) {
                        a = Alignment.TOP_LEFT;
                        s = "TOP_LEFT";
                    }
                    io.setComponentAnchor(a);
                    label.setValue("Alignment." + s);
                }
            });
        }

        setContent(layout);
    }
}
