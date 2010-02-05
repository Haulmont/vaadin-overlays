package org.vaadin.overlay;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

/**
 * CustomOverlay can be used to add overlays to other components. You can use
 * this to add any component that hovers on top of the component, but it may be
 * easier to use one of the more specific implementations instead:
 * {@link ImageOverlay} or {@link TextOverlay}
 *
 * This is the server-side part of the component.
 */
@com.vaadin.ui.ClientWidget(org.vaadin.overlay.client.ui.VCustomOverlay.class)
public class CustomOverlay extends AbstractComponent {
    private static final long serialVersionUID = 4484572264185406155L;

    private Component overlay = null;
    private Component component = null;
    private Alignment align = Alignment.TOP_LEFT;
    private Alignment overlayAlign = Alignment.TOP_LEFT;
    private int x = 0;
    private int y = 0;

    /**
     * Create empty overlay.
     *
     * Use {@link #setComponent(Component)} and {@link #setOverlay(Component)}
     * to bind the overlay to a component.
     */
    public CustomOverlay() {
    }

    /**
     * Create empty overlay for a component.
     *
     * Use {@link #setOverlay(Component)} to add overlay content.
     */
    public CustomOverlay(Component overlay, Component onComponent) {
        super();
        setOverlay(overlay);
        setComponent(onComponent);
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        if (component != null) {
            target.addAttribute("comp", component);
        }
        target.addAttribute("align", align.getBitMask());
        target.addAttribute("overlayAlign", overlayAlign.getBitMask());
        target.addAttribute("x", x);
        target.addAttribute("y", y);

        if (overlay != null) {
            overlay.paint(target);
        }
    }

    /**
     * Set the horizontal offset of the overlay from the alignment point.
     *
     * This is screen coordinates and default is 0.
     *
     * @param x
     *            Positive for right or negative left offset.
     * @see #setComponentAnchor(Alignment)
     */
    public void setXOffset(int x) {
        this.x = x;
        requestRepaint();
    }

    /**
     * Get the horizontal offset of the overlay from the alignment point.
     *
     * This is screen coordinates.
     *
     * @return Positive for right or negative left offset.
     * @see #getComponentAnchor()
     */
    public int getXOffset() {
        return x;
    }

    /**
     * Set the vertical offset of the overlay from the alignment point.
     *
     * This is screen coordinates and default is 0.
     *
     * @param y
     *            Positive for downward or negative for upward offset.
     * @see #setComponentAnchor(Alignment)
     */
    public void setYOffset(int y) {
        this.y = y;
        requestRepaint();
    }

    /**
     * Get the vertical offset of the overlay from the alignment point.
     *
     * This is screen coordinates.
     *
     * @return Positive for downward or negative upward offset.
     * @see #getComponentAnchor()
     */
    public int getYOffset() {
        return y;
    }

    public void setOverlay(Component overlay) {
        this.overlay = overlay;
        requestRepaint();
    }

    /**
     * Get the overlay content.
     *
     * @return
     */
    public Component getOverlay() {
        return overlay;
    }

    /**
     * Set the reference component.
     *
     * @param component
     *            The component that this overlay is aligned to.
     */
    public void setComponent(Component component) {
        this.component = component;
        requestRepaint();
    }

    /**
     * Get the reference component.
     *
     * @return The component that this overlay is aligned to.
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Set the anchor point of the reference component.
     *
     * The X and Y offsets are relative to this point and overlayAnchor point.
     *
     * The default is {@link Alignment.TOP_LEFT}
     *
     * @param anchorPoint
     *            One of the {@link Alignment} constants.
     * @see #setComponent(Component)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setOverlayAnchor(Alignment)
     */
    public void setComponentAnchor(Alignment anchorPoint) {
        align = anchorPoint != null ? anchorPoint : Alignment.TOP_LEFT;
        requestRepaint();
    }

    /**
     * Get the anchor point of the reference component.
     *
     * The X and Y offsets are relative to this point.
     *
     * @return align One of the {@link Alignment} constants.
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setOverlayAnchor(Alignment)
     */
    public Alignment getComponentAnchor() {
        return align;
    }

    /**
     * Set the alignment point of the overlay component.
     *
     * The X and Y offsets are relative to this point.
     *
     * @return align One of the {@link Alignment} constants.
     * @see #setOverlay(Component)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setComponentAnchor(Alignment)
     */
    public void setOverlayAnchor(Alignment overlayAnchorPoint) {
        overlayAlign = overlayAnchorPoint != null ? overlayAnchorPoint
                : Alignment.TOP_LEFT;
        requestRepaint();
    }

    /**
     * Get the alignment point of the overlay component.
     *
     * The X and Y offsets are relative to this point.
     *
     * @return align One of the {@link Alignment} constants.
     * @see #setOverlay(Component)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setComponentAnchor(Alignment)
     */
    public Alignment getOverlayAnchor() {
        return overlayAlign;
    }

}
