package org.vaadin.overlay;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.shared.ui.AlignmentInfo;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import org.vaadin.overlay.widgetset.client.CustomOverlayState;

import java.util.Iterator;

/**
 * CustomOverlay can be used to add overlays to other components. You can use
 * this to add any component that hovers on top of the component, but it may be
 * easier to use one of the more specific implementations instead:
 * {@link ImageOverlay} or {@link TextOverlay}
 * <p/>
 * This is the server-side part of the component.
 */
@JavaScript("public/overlays/overlays.js")
@StyleSheet("public/overlays/styles.css")
public class CustomOverlay extends AbstractComponentContainer {
    private static final long serialVersionUID = 4484572264185406155L;

    private Component overlay = null;
//    private Component component = null;

    /**
     * Create empty overlay.
     * <p/>
     * Use {@link #setComponent(Component)} and {@link #setOverlay(Component)}
     * to bind the overlay to a component.
     */
    public CustomOverlay() {
    }

    /**
     * Create empty overlay for a component.
     * <p/>
     * Use {@link #setOverlay(Component)} to add overlay content.
     */
    public CustomOverlay(Component overlay, Component refenceComponent) {
        super();
        setComponent(refenceComponent);
        setOverlay(overlay);
    }

    @Override
    protected CustomOverlayState getState() {
        return (CustomOverlayState) super.getState();
    }

    @Override
    protected CustomOverlayState getState(boolean markAsDirty) {
        return (CustomOverlayState) super.getState(markAsDirty);
    }

//    @Override
//    public void paintContent(PaintTarget target) throws PaintException {
//        super.paintContent(target);
//
//        if (component != null) {
//            target.addAttribute("comp", component);
//        }
//        target.addAttribute("align", align.getBitMask());
//        target.addAttribute("overlayAlign", overlayAlign.getBitMask());
//        target.addAttribute("x", x);
//        target.addAttribute("y", y);
//
//        if (overlay != null) {
//            overlay.paint(target);
//        }
//    }

    /**
     * Set the horizontal offset of the overlay from the alignment point.
     * <p/>
     * This is screen coordinates and default is 0.
     *
     * @param x Positive for right or negative left offset.
     * @see #setComponentAnchor(Alignment)
     */
    public void setXOffset(int x) {
        getState().x = x;
    }

    /**
     * Get the horizontal offset of the overlay from the alignment point.
     * <p/>
     * This is screen coordinates.
     *
     * @return Positive for right or negative left offset.
     * @see #getComponentAnchor()
     */
    public int getXOffset() {
        return getState(false).x;
    }

    /**
     * Set the vertical offset of the overlay from the alignment point.
     * <p/>
     * This is screen coordinates and default is 0.
     *
     * @param y Positive for downward or negative for upward offset.
     * @see #setComponentAnchor(Alignment)
     */
    public void setYOffset(int y) {
        getState().y = y;
    }

    /**
     * Get the vertical offset of the overlay from the alignment point.
     * <p/>
     * This is screen coordinates.
     *
     * @return Positive for downward or negative upward offset.
     * @see #getComponentAnchor()
     */
    public int getYOffset() {
        return getState(false).y;
    }

    public void setOverlay(Component overlay) {
        if (this.overlay != null) {
            super.removeComponent(this.overlay);
        }
        this.overlay = overlay;
        if (this.overlay != null) {
            super.addComponent(overlay);
        }
        markAsDirty();
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
     * @param component The component that this overlay is aligned to.
     */
    public void setComponent(Component component) {
        getState().component = component;
    }

    /**
     * Get the reference component.
     *
     * @return The component that this overlay is aligned to.
     */
    public Component getComponent() {
        return (Component) getState(false).component;
    }

    /**
     * Set the anchor point of the reference component.
     * <p/>
     * The X and Y offsets are relative to this point and overlayAnchor point.
     * <p/>
     *
     * @param anchorPoint One of the {@link Alignment} constants.
     * @see #setComponent(Component)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setOverlayAnchor(Alignment)
     */
    public void setComponentAnchor(Alignment anchorPoint) {
        getState().alignBitMask = anchorPoint.getBitMask();
        markAsDirty();
    }

    /**
     * Get the anchor point of the reference component.
     * <p/>
     * The X and Y offsets are relative to this point.
     *
     * @return align One of the {@link Alignment} constants.
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setOverlayAnchor(Alignment)
     */
    public Alignment getComponentAnchor() {
        return new Alignment(getState(false).alignBitMask);
    }

    /**
     * Set the alignment point of the overlay component.
     * <p/>
     * The X and Y offsets are relative to this point.
     *
     * @return align One of the {@link Alignment} constants.
     * @see #setOverlay(Component)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setComponentAnchor(Alignment)
     */
    public void setOverlayAnchor(Alignment overlayAnchorPoint) {
        getState().overlayAlignBitMask = overlayAnchorPoint.getBitMask();
        markAsDirty();
    }

    /**
     * Get the alignment point of the overlay component.
     * <p/>
     * The X and Y offsets are relative to this point.
     *
     * @return align One of the {@link Alignment} constants.
     * @see #setOverlay(Component)
     * @see #setXOffset(int)
     * @see #setYOffset(int)
     * @see #setComponentAnchor(Alignment)
     */
    public Alignment getOverlayAnchor() {
        return new Alignment(getState(false).overlayAlignBitMask);
    }

    /**
     * This class only contains overlay components.
     *
     * @see com.vaadin.ui.ComponentContainer#getComponentIterator()
     */
    @Override
    public Iterator<Component> iterator() {
        return new Iterator<Component>() {
            private Component currentOverlay = getOverlay();
            private boolean first = currentOverlay == null;

            public boolean hasNext() {
                return !first;
            }

            public Component next() {
                if (!first) {
                    first = true;
                    return currentOverlay;
                } else {
                    return null;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /*
     * Methods inherited from AbstractComponentContainer. These are unnecessary
     * (but mandatory). Most of them are not supported in this implementation.
     */

    /**
     * Not supported in this implementation.
     *
     * @throws UnsupportedOperationException
     * @see com.vaadin.ui.AbstractComponentContainer#addComponent(com.vaadin.ui.Component)
     */
    @Override
    public void addComponent(Component c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported in this implementation.
     *
     * @throws UnsupportedOperationException
     * @see com.vaadin.ui.ComponentContainer#replaceComponent(com.vaadin.ui.Component,
     *      com.vaadin.ui.Component)
     */
    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported in this implementation
     *
     * @see com.vaadin.ui.AbstractComponentContainer#removeComponent(com.vaadin.ui.Component)
     */
    @Override
    public void removeComponent(Component c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getComponentCount() {
//        TODO
        return 0;
    }

    protected AlignmentInfo alignmentToInfo(Alignment alignment) {
        if (alignment == null) {
            return new AlignmentInfo(Alignment.TOP_LEFT.getBitMask());
        }
        return new AlignmentInfo(alignment.getBitMask());
    }

    protected Alignment infoToAlignment(AlignmentInfo alignment) {
        if (alignment == null) {
            return new Alignment(AlignmentInfo.TOP_LEFT.getBitMask());
        }
        return new Alignment(alignment.getBitMask());
    }
}
