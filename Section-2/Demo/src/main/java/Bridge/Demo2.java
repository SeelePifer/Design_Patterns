package Bridge;

interface Renderered
{
    public String whatToRenderAs();
}

abstract class Shaped
{
    private Renderered renderer;
    public String name;

    public Shaped(Renderered renderer)
    {
        this.renderer = renderer;
    }

    @Override
    public String toString()
    {
        return String.format("Drawing %s as %s",
                name, renderer.whatToRenderAs());
    }
}

class Triangle extends Shaped
{
    public Triangle(Renderered renderer)
    {
        super(renderer);
        name = "Triangle";
    }
}

class Square extends Shaped
{
    public Square(Renderered renderer)
    {
        super(renderer);
        name = "Square";
    }
}

class RasterRenderered implements Renderered
{

    @Override
    public String whatToRenderAs()
    {
        return "pixels";
    }
}

class VectorRenderered implements Renderered
{
    @Override
    public String whatToRenderAs()
    {
        return "lines";
    }
}
