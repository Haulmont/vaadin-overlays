package org.vaadin.overlay;

import org.vaadin.codelabel.CodeLabel;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class OverlaySampleApplication extends Application {
    private static final long serialVersionUID = -6981722743014196328L;

    private static final String ICON_BETA = "icon-beta.png";
    private static final String ICON_BUBBLE = "icon-bubble.png";
    private static final String ICON_NEW = "icon-new.png";
    private static final String ICON_WARNING = "icon-warning.png";
    private static final String ICON_SPINNER = "icon-spinner.gif";

    private Select img;
    private OptionGroup anchor;
    private OptionGroup overlayAnchor;
    private Slider x;
    private Slider y;
    private ImageOverlay imageOverlay;
    private CodeLabel code;
    private Label label;
    private Button button;
    private Select select;
    private Select ref;

    private TextField textArea;

    private Table table;

    @Override
    public void init() {
        Window mainWindow = new Window("Overlay Application");
        setMainWindow(mainWindow);
        ((VerticalLayout) mainWindow.getContent()).setSpacing(true);

        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        mainWindow.addComponent(hl);

        Panel sample = new Panel("Sample");
        hl.addComponent(sample);
        sample.setWidth("350px");
        sample.setHeight("400px");

        label = new Label(
                "These are some sample components that you can try to position the ImageOverlay. Use the configuration panel on the right. A sample Java code gets generated below.");
        sample.addComponent(label);

        button = new Button("Click me", new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            public void buttonClick(ClickEvent event) {
                getMainWindow().showNotification("Clicked");

            }
        });
        sample.addComponent(button);

        select = new Select("Select");
        select.addItem("Item 1");
        sample.addComponent(select);

        table = new Table();
        sample.addComponent(table);
        table.addContainerProperty("Name", String.class, null);
        table.addContainerProperty("Occupation", String.class, null);
        table.addContainerProperty("Age", String.class, null);
        table.setWidth("250px");
        table.setHeight("70px");

        textArea = new TextField("TextField");
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setValue("Some text in the TextField");
        sample.addComponent(textArea);

        VerticalLayout vl = (VerticalLayout) sample.getContent();
        vl.setMargin(true);
        vl.setSpacing(true);

        // By default image overlay sample for the button
        imageOverlay = new ImageOverlay(button);
        mainWindow.addComponent(imageOverlay);

        VerticalLayout lo = new VerticalLayout();
        lo.setSpacing(true);
        lo.setMargin(true);
        Panel props = new Panel("Overlay configurator", lo);
        hl.addComponent(props);
        props.setWidth("350px");
        props.setHeight("400px");

        ref = new Select("Choose reference component");
        props.addComponent(ref);
        ref.addItem(label);
        ref.addItem(button);
        ref.addItem(select);
        ref.addItem(table);
        ref.addItem(textArea);
        ref.setItemCaption(label, "Label");
        ref.setItemCaption(button, "Button");
        ref.setItemCaption(select, "Select");
        ref.setItemCaption(table, "Table");
        ref.setItemCaption(textArea, "TextField");
        ref.setNullSelectionAllowed(false);
        ref.setImmediate(true);

        img = new Select("Choose overlay image");
        props.addComponent(img);
        img.addItem(ICON_BETA);
        img.addItem(ICON_BUBBLE);
        img.addItem(ICON_NEW);
        img.addItem(ICON_WARNING);
        img.addItem(ICON_SPINNER);
        img.setNullSelectionAllowed(false);
        img.setImmediate(true);

        HorizontalLayout propsA = new HorizontalLayout();
        props.addComponent(propsA);
        anchor = new OptionGroup("Anchor point:");
        propsA.addComponent(anchor);
        anchor.addItem(Alignment.TOP_LEFT);
        anchor.addItem(Alignment.TOP_CENTER);
        anchor.addItem(Alignment.TOP_RIGHT);
        anchor.addItem(Alignment.MIDDLE_LEFT);
        anchor.addItem(Alignment.MIDDLE_CENTER);
        anchor.addItem(Alignment.MIDDLE_RIGHT);
        anchor.addItem(Alignment.BOTTOM_LEFT);
        anchor.addItem(Alignment.BOTTOM_CENTER);
        anchor.addItem(Alignment.BOTTOM_RIGHT);

        for (Object ao : anchor.getItemIds()) {
            String caption = ((Alignment) ao).getHorizontalAlignment() + " "
                    + ((Alignment) ao).getVerticalAlignment();
            anchor.setItemCaption(ao, caption);
        }
        anchor.setNullSelectionAllowed(false);
        anchor.setImmediate(true);

        overlayAnchor = new OptionGroup("Overlay anchor point:");
        propsA.addComponent(overlayAnchor);
        overlayAnchor.addItem(Alignment.TOP_LEFT);
        overlayAnchor.addItem(Alignment.TOP_CENTER);
        overlayAnchor.addItem(Alignment.TOP_RIGHT);
        overlayAnchor.addItem(Alignment.MIDDLE_LEFT);
        overlayAnchor.addItem(Alignment.MIDDLE_CENTER);
        overlayAnchor.addItem(Alignment.MIDDLE_RIGHT);
        overlayAnchor.addItem(Alignment.BOTTOM_LEFT);
        overlayAnchor.addItem(Alignment.BOTTOM_CENTER);
        overlayAnchor.addItem(Alignment.BOTTOM_RIGHT);

        for (Object ao : overlayAnchor.getItemIds()) {
            String caption = ((Alignment) ao).getHorizontalAlignment() + " "
                    + ((Alignment) ao).getVerticalAlignment();
            overlayAnchor.setItemCaption(ao, caption);
        }
        overlayAnchor.setNullSelectionAllowed(false);
        overlayAnchor.setImmediate(true);

        x = new Slider("X");
        x.setWidth("150px");
        props.addComponent(x);
        x.setMin(-50);
        x.setMax(50);
        x.setImmediate(true);

        y = new Slider("Y");
        y.setWidth("150px");
        props.addComponent(y);
        y.setMin(-50);
        y.setMax(50);
        y.setImmediate(true);

        Panel codePanel = new Panel("Sample Code");
        codePanel.setWidth((350 + 8 + 350) + "px");

        mainWindow.addComponent(codePanel);
        code = new CodeLabel("");
        codePanel.addComponent(code);

        // Some extra samples...
        ImageOverlay io = new ImageOverlay(sample, new ClassResource(this
                .getClass(), "images/sticker-blue.png", this));
        io.setComponentAnchor(Alignment.BOTTOM_RIGHT);
        io.setXOffset(-128);
        io.setYOffset(-128);
        mainWindow.addComponent(io);

        TextOverlay to = new TextOverlay(io, " Try Live\nConfig! -->");
        to.setContentMode(TextOverlay.CONTENT_PREFORMATTED);
        to.setXOffset(24);
        to.setYOffset(37);
        mainWindow.addComponent(to);

        TextOverlay to2 = new TextOverlay(table, "<i>[No entries found]</i>");
        to2.setContentMode(TextOverlay.CONTENT_RAW);
        to2.setComponentAnchor(Alignment.MIDDLE_CENTER);
        to2.setOverlayAnchor(Alignment.MIDDLE_CENTER);
        mainWindow.addComponent(to2);

        ImageOverlay b = new ImageOverlay(getMainWindow().getContent(), new ClassResource(this
                .getClass(), "images/corner_banner.png", this));
        b.setComponentAnchor(Alignment.TOP_RIGHT);
        b.setOverlayAnchor(Alignment.TOP_RIGHT);
        b.setYOffset(0);
        mainWindow.addComponent(b);

        ImageOverlay jl = new ImageOverlay(codePanel, new ClassResource(this
                .getClass(), "images/java-logo.png", this));
        jl.setComponentAnchor(Alignment.TOP_RIGHT);
        jl.setXOffset(-60);
        jl.setYOffset(30);
        mainWindow.addComponent(jl);

//        ImageOverlay v = new ImageOverlay(props, new ClassResource(this
//                .getClass(), "images/vaadin-symbol-beige.png", this));
//        v.setComponentAnchor(Alignment.TOP_RIGHT);
//        v.setXOffset(20);
//        v.setYOffset(-20);
//        mainWindow.addComponent(v);

        ValueChangeListener l = new Property.ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            public void valueChange(ValueChangeEvent event) {
                apply();
            }
        };
        ref.addListener(l);
        img.addListener(l);
        anchor.addListener(l);
        overlayAnchor.addListener(l);
        x.addListener(l);
        y.addListener(l);

        // Defaults
        ref.setValue(label);
        img.setValue("icon-beta.png");
        anchor.setValue(Alignment.TOP_RIGHT);
        overlayAnchor.setValue(Alignment.MIDDLE_CENTER);

    }

    protected void apply() {

        // Reference component
        imageOverlay.setComponent((Component) ref.getValue());

        // Overlay image
        Resource res = new ClassResource(this.getClass(), "images/"
                + img.getValue(), this);
        imageOverlay.setImage(res);

        // TODO: Set size to make sure that images get rendered in right
        // position.
        // IMO, this should not be required, but Embedded does not work without.
        if (ICON_BETA.equals(img.getValue())) {
            imageOverlay.getOverlay().setWidth("40px");
            imageOverlay.getOverlay().setHeight("40px");
        } else if (ICON_BUBBLE.equals(img.getValue())) {
            imageOverlay.getOverlay().setWidth("48px");
            imageOverlay.getOverlay().setHeight("48px");
        } else if (ICON_NEW.equals(img.getValue())) {
            imageOverlay.getOverlay().setWidth("37px");
            imageOverlay.getOverlay().setHeight("17px");
        } else if (ICON_WARNING.equals(img.getValue())) {
            imageOverlay.getOverlay().setWidth("14px");
            imageOverlay.getOverlay().setHeight("11px");
        } else if (ICON_SPINNER.equals(img.getValue())) {
            imageOverlay.getOverlay().setWidth("16px");
            imageOverlay.getOverlay().setHeight("16px");
        }

        // Anchor point (on reference component)
        imageOverlay.setComponentAnchor((Alignment) anchor.getValue());

        // Anchor point (of the overlay component)
        imageOverlay.setOverlayAnchor((Alignment) overlayAnchor.getValue());

        // X and Y offsets
        imageOverlay.setXOffset(((Double) x.getValue()).intValue());
        imageOverlay.setYOffset(((Double) y.getValue()).intValue());

        updateJavaCode();

    }

    private void updateJavaCode() {

        Component comp = imageOverlay.getComponent();
        String cv = comp.getClass().getSimpleName().toLowerCase();
        String av = "Alignment.";
        Alignment a = imageOverlay.getComponentAnchor();
        av += a.getHorizontalAlignment().toUpperCase() + "_"
                + a.getVerticalAlignment().toUpperCase();

        String oav = "Alignment.";
        Alignment oa = imageOverlay.getOverlayAnchor();
        oav += oa.getHorizontalAlignment().toUpperCase() + "_"
                + oa.getVerticalAlignment().toUpperCase();

        String xv = "" + imageOverlay.getXOffset();
        String yv = "" + imageOverlay.getYOffset();
        ClassResource ir = (ClassResource) imageOverlay.getImage();
        String iv = "" + ir.getFilename();
        iv = iv.substring(iv.lastIndexOf('/') + 1);

        String c = "";
        c += "Resource icon = new ClassResource(\"images/" + iv
                + "\", this);\n";
        c += "ImageOverlay overlay = new ImageOverlay(" + cv + ",icon);\n";
        c += "mainWindow.addComponent(overlay);\n";
        c += "overlay.setComponentAnchor(" + av + ");\n";
        c += "overlay.setOverlayAnchor(" + oav + ");\n";
        c += "overlay.setXOffset(" + xv + ");\n";
        c += "overlay.setYOffset(" + yv + ");\n";
        code.setValue(c);
    }
}
