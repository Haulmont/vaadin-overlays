package org.vaadin.overlay;

/**
 * Interface for receiving click events on Overlay.
 * <p/>
 * Use this together with implementations of the {@link CustomClickableOverlay}
 * such as {@link ImageOverlay} and {@link TextOverlay}.
 *
 * @author Sami Ekblad
 */
public interface OverlayClickListener {

    public void overlayClicked(CustomClickableOverlay overlay);

}
