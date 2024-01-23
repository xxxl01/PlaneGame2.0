package PlaneGame.Object;

import java.awt.*;
/*
    这是一个抽象类 GameObject，它定义了一般性的游戏对象的属性和行为。
    protected Image image;：表示游戏对象的图像。这个图像用于在屏幕上绘制对象。
    protected int x; 和 protected int y;：表示游戏对象在屏幕上的坐标。这些属性用于确定对象在窗口中的位置。
    protected int width; 和 protected int height;：表示游戏对象的宽度和高度。这些属性描述了游戏对象图像的尺寸。
*/
abstract class GameObject {
    protected Image image;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.drawImage(image,x,y,width,height,null);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
