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
import com.vaadin.client.Util;
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
        super();
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

    /**
     * Called whenever an update is received from the server
     */
//    public void updateFromUIDL(UIDL uidl, final ApplicationConnection client) {
//
//        // Custom visibility handling
//        if (uidl.getBooleanAttribute("invisible") && overlay != null) {
//            overlay.hide();
//        }
//        if (client.updateComponent(this, uidl, false)) {
//            return;
//        }
//
//        // Find the reference component
//        if (uidl.hasAttribute("comp")) {
//            Paintable refComp = client.getPaintable(uidl
//                    .getStringAttribute("comp"));
//            if (refComp != null) {
//                Widget w = (Widget) refComp;
//                if (w instanceof VCustomOverlay) {
//                    w = ((VCustomOverlay) w).getOverlayWidget();
//                }
//                refCompEl = w.getElement();
//            }
//        }
//
//
//        // Render the component
//        final UIDL child = uidl.getChildUIDL(0);
//        if (child != null) {
//            Paintable p = client.getPaintable(child);
//            Widget w = overlay.getWidget();
//            if (p != w && w != null) {
//                client.unregisterPaintable((Paintable) w);
//                overlay.clear();
//            }
//            overlay.setWidget((Widget) p);
//            overlay.show();
//            p.updateFromUIDL(child, client);
//        } else {
//            overlay.hide();
//        }
//
//        Widget wgt = getOverlayWidget();
//        int w = Util.getRequiredWidth(wgt);
//        int h = Util.getRequiredHeight(wgt);
//        ApplicationConnection.getConsole().log("PAINT: w=" + w + "h=" + h);
//
//        // Position the component
//        x = uidl.getIntAttribute("x");
//        y = uidl.getIntAttribute("y");
//        align = new AlignmentInfo(uidl.getIntAttribute("align"));
//        overlayAlign = new AlignmentInfo(uidl.getIntAttribute("overlayAlign"));
//
//        deferredUpdatePosition();
//    }
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
                    int w = Util.getRequiredWidth(wgt);
                    int h = Util.getRequiredHeight(wgt);

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
}
