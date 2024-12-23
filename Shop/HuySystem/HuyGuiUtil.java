package HuySystem;

import javax.swing.*;
import java.awt.*;

public class HuyGuiUtil extends HuyUtil
{
    protected void setFixedSize(JComponent component, int width, int height)
    {
        component.setPreferredSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
        component.setMinimumSize(new Dimension(width, height));
    }

    protected void setAlignmentCenter(JComponent component)
    {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    protected void setAlignmentCenterLeft(JComponent component)
    {
        component.setAlignmentX(Component.LEFT_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    protected void setAlignmentCenterRight(JComponent component)
    {
        component.setAlignmentX(Component.RIGHT_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    protected JButton createButton(String name, int width, int height)
    {
        JButton button = new JButton(name);
        this.setFixedSize(button, width, height);
        return button;
    }
}
