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

import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;

/**
 * Server-side class for creating image overlays for other components.
 * <p/>
 * The overlays are images rendered on the top of the specified component and
 * they can be aligned using the {@link #setComponentAnchor(com.vaadin.ui.Alignment)},
 * {@link #setXOffset(int)} and {@link #setYOffset(int)} functions.
 *
 * @author Sami Ekblad
 */
public class ImageOverlay extends CustomClickableOverlay {
    private static final long serialVersionUID = -4604714578885982118L;
    private Resource imageResource;

    /**
     * Create new overlay for a component using the specified image resource.
     *
     * @param referenceComponent
     * @param imageResource
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public ImageOverlay(Component referenceComponent, Resource imageResource) {
        setComponent(referenceComponent);
        setImage(imageResource);
    }

    /**
     * Create new overlay for a component. The image resource must be added
     * later using {@link #setImage(Resource)}
     *
     * @param referenceComponent
     * @see #setImage(Resource)
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public ImageOverlay(Component referenceComponent) {
        setComponent(referenceComponent);
    }

    /**
     * Create new empty overlay.
     * <p/>
     * The image resource must be added later using {@link #setImage(Resource)}
     * and reference component using {@link #setComponent(Component)}
     *
     * @see #setImage(Resource)
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public ImageOverlay() {
    }

    /**
     * Sets the overlay image.
     * <p/>
     * The overlay creates an {@link Embedded} from the resource.
     *
     * @param imageResource
     */
    public void setImage(Resource imageResource) {
        this.imageResource = imageResource;
        Embedded embedded = new Embedded(null, imageResource);
        setOverlay(embedded);
    }

    /**
     * Get the image.
     *
     * @return
     */
    public Resource getImage() {
        return imageResource;
    }

}
