package com.gabriel.draw.service;

import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.RendererService;

import java.awt.*;

public class RectangleRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Rectangle rectangle = (Rectangle) shape;
        g.setXORMode(shape.getColor());

        int x = Math.min(rectangle.getLocation().x, rectangle.getEnd().x);
        int y = Math.min(rectangle.getLocation().y, rectangle.getEnd().y);
        int width  = Math.abs(rectangle.getEnd().x - rectangle.getLocation().x);
        int height = Math.abs(rectangle.getEnd().y - rectangle.getLocation().y);

        g.drawRect(x, y, width, height);
    }
}
