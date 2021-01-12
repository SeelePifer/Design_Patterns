package Facade;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

class Buffer{
    private char [] characters;
    private int lineWidht;

    public Buffer(int lineHeight, int lineWidht) {
        this.lineWidht = lineWidht;
        characters= new char[lineWidht*lineHeight];
    }
    public char CharAt(int x,int y){
        return characters[y*lineWidht+x];
    }
}
class ViewPort{
    private final Buffer buffer;
    private int widht;
    private int height;
    private int offsetX;
    private int offsetY;
    public ViewPort(Buffer buffer, int widht, int height,
                    int offsetX, int offsetY) {
        this.buffer = buffer;
        this.widht = widht;
        this.height= height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    public char charAt(int x,int y){
        return buffer.CharAt(x+offsetX,y+offsetY);
    }
}
class Console{
    private List<ViewPort> viewPorts =
            new ArrayList<>();
    int width,height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void addViewPort(ViewPort viewport){
        viewPorts.add(viewport);
    }
    public static Console newConsole(int width,int height){
        Buffer buffer = new Buffer(width, height);
        ViewPort viewPort = new ViewPort(buffer, width, height, 0, 0);
        Console console = new Console(width, height);
        console.addViewPort(viewPort);
        return  console;
    }
    public void render(){
        for (int y=0; y<height;++y){
            for (int x=0; x<width;++x){
                for (ViewPort vp: viewPorts)
                    System.out.println(vp.charAt(x,y));
            }
            System.out.println();
        }
    }
}
public class Demo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(30,20);
        ViewPort viewPort=new ViewPort(buffer,30,20,0,0);
        Console console= new Console(30,20);
        console.addViewPort(viewPort);
        console.render();

        Console console2 = Console.newConsole(30, 20);
        console2.render();
    }

}
