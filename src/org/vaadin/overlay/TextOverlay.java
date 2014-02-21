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

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * Server-side class for creating text overlays for other components.
 * <p/>
 * The overlays are strings rendered on the top of the specified component and
 * they can be aligned using the
 * {@link #setComponentAnchor(com.vaadin.ui.Alignment)},
 * {@link #setXOffset(int)} and {@link #setYOffset(int)} functions.
 *
 * @author Sami Ekblad
 */

public class TextOverlay extends CustomClickableOverlay {
    private static final long serialVersionUID = -4604714578885982118L;

    public static final ContentMode CONTENT_DEFAULT = ContentMode.TEXT;
    public static final ContentMode CONTENT_PREFORMATTED = ContentMode.PREFORMATTED;
    public static final ContentMode CONTENT_RAW = ContentMode.RAW;
    public static final ContentMode CONTENT_TEXT = ContentMode.TEXT;
    public static final ContentMode CONTENT_XHTML = ContentMode.HTML;
    public static final ContentMode CONTENT_XML = ContentMode.XML;

    private String text;
    private Label label;

    /**
     * Create new text overlay for a component.
     *
     * @param referenceComponent Align the overlay to this component.
     * @param text               Text content of the overlay
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public TextOverlay(Component referenceComponent, String text) {
        setComponent(referenceComponent);
        setText(text);
    }

    /**
     * Create new text empty overlay for a component.
     * <p/>
     * The text must be added later using the {@link #setText(String)}
     *
     * @param referenceComponent Align the overlay to this component.
     * @see #setText(String)
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public TextOverlay(Component referenceComponent) {
        setComponent(referenceComponent);
    }

    /**
     * Create new text empty overlay.
     * <p/>
     * The text must be added later using the {@link #setText(String)} and the
     * reference component using {@link #setComponent(Component)}.
     *
     * @see #setText(String)
     * @see #setComponentAnchor(com.vaadin.ui.Alignment)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     */
    public TextOverlay() {
    }

    /**
     * Set overlay text.
     * <p/>
     * Creates a {@link Label} for the text.
     *
     * @param text
     * @see #setContentMode(ContentMode)
     */
    public void setText(final String text) {
        this.text = text;
        if (label == null) {
            label = new Label();
            label.setSizeUndefined();
            setOverlay(label);
        }
        label.setValue(this.text);
    }

    /**
     * Set the content mode.
     * <p/>
     * This is a shortcut for ((Label)getOverlay()).setContentMode(int).
     *
     * @param contentMode One of the CONTENT_MODE_* constants. Same as
     *                    Label.CONTENT_MODE_* constants.
     */
    public void setContentMode(ContentMode contentMode) {
        label.setContentMode(contentMode);
    }

    /**
     * Get the content mode.
     * <p/>
     * This is a shortcut for ((Label)getOverlay()).getContentMode().
     *
     * @return One of the CONTENT_MODE_* constants. Same as Label.CONTENT_MODE_*
     *         constants.
     */
    public ContentMode getContentMode() {
        if (label != null) {
            return label.getContentMode();
        }
        return CONTENT_DEFAULT;
    }

}
