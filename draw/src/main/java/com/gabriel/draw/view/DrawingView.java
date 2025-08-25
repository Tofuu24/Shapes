//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class DrawingView extends JPanel {
    AppService appService;
    JComboBox<ShapeMode> shapeSelector;

    public DrawingView(AppService appService) {
        this.appService = appService;
        new DrawingController(appService, this);
        this.shapeSelector = new JComboBox(ShapeMode.values());
        this.shapeSelector.setSelectedItem(appService.getShapeMode());
        this.shapeSelector.addActionListener((e) -> {
            ShapeMode selectedShape = (ShapeMode)this.shapeSelector.getSelectedItem();
            appService.setShapeMode(selectedShape);
        });
        this.add(this.shapeSelector);
        this.setLayout(new FlowLayout(0));
    }

    public void paint(Graphics g) {
        super.paint(g);
        Drawing drawing = (Drawing)this.appService.getModel();

        for(Shape shape : drawing.getShapes()) {
            shape.getRendererService().render(g, shape, false);
        }

    }
}
