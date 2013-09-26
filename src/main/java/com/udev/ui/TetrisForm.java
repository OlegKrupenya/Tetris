package com.udev.ui;

import com.udev.domain.Cell;
import com.udev.domain.Field;
import com.udev.events.EventDispatcher;
import com.udev.events.PaintEventListener;

import javax.swing.*;
import java.awt.*;

/**
 * User: oleg.krupenya
 * Date: 9/25/13
 * Time: 10:14 PM
 */
public class TetrisForm extends JFrame implements PaintEventListener {

    private EventDispatcher dispatcher;

    private TetrisPanel panel;

    public EventDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Constructs a new frame that is initially invisible.
     * <p/>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws java.awt.HeadlessException if GraphicsEnvironment.isHeadless()
     *                                    returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see java.awt.Component#setSize
     * @see java.awt.Component#setVisible
     * @see javax.swing.JComponent#getDefaultLocale
     */
    public TetrisForm() throws HeadlessException {
        this.panel = new TetrisPanel();
        this.add(this.panel);
    }

    @Override
    public void paintField(Field field) {
        this.panel.setField(field);
        this.panel.repaint();
        field.showData();
    }
}
