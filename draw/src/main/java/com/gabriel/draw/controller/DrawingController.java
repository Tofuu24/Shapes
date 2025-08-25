//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.gabriel.draw.controller;

import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController implements MouseListener, MouseMotionListener {
    private Point end;
    private final DrawingView drawingView;
    private Shape currentShape;
    private final AppService appService;

    public DrawingController(AppService appService, DrawingView drawingView) {
        this.appService = appService;
        this.drawingView = drawingView;
        drawingView.addMouseListener(this);
        drawingView.addMouseMotionListener(this);
        appService.setDrawMode(DrawMode.Idle);
        appService.setShapeMode(ShapeMode.Rectangle);
    }

    public void mousePressed(MouseEvent e) {
        Point start = e.getPoint();
        if (this.appService.getDrawMode() == DrawMode.Idle) {
            switch (this.appService.getShapeMode()) {
                case Line -> this.currentShape = new Line(start, start);
                case Ellipse -> this.currentShape = new Ellipse(start, start);
                case Rectangle -> this.currentShape = new Rectangle(start, start);
            }

            this.currentShape.getRendererService().render(this.drawingView.getGraphics(), this.currentShape, false);
            this.appService.setDrawMode(DrawMode.MousePressed);
        }

    }

    public void mouseDragged(MouseEvent e) {
        if (this.appService.getDrawMode() == DrawMode.MousePressed) {
            this.end = e.getPoint();
            this.currentShape.getRendererService().render(this.drawingView.getGraphics(), this.currentShape, true);
            this.appService.scale(this.currentShape, this.end);
            this.currentShape.getRendererService().render(this.drawingView.getGraphics(), this.currentShape, true);
        }

    }

    public void mouseReleased(MouseEvent e) {
        if (this.appService.getDrawMode() == DrawMode.MousePressed) {
            this.end = e.getPoint();
            this.appService.create(this.currentShape);
            this.appService.setDrawMode(DrawMode.Idle);
        }

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }
}
