import java.io.FileInputStream;

public class AoCD3 {

    public static void main(String[] args)
    {
        FileInputStream fis;
        try {
            fis=new FileInputStream("input");
        }
        catch (Exception e)
        {System.out.println("File not found.");
            fis=null;
        }
    }
}
class _2DMap
{
    private String map;
    //Please Note a line separator(i.e \n) is important to use this class or a SeparatorNotFoundException is thrown.
    _2DMap(String map)
    { this.map=map; }

    public String getObjectByRowCol(int rowNo,int colNo) throws SeparatorNotFoundException
    {
        String obj="";
        if (!map.contains("\n"))
            throw new SeparatorNotFoundException("Expected a line separator.");
        String shape=getShape();
        int maxCordPerLine=getChildrenPerLine();
        int lineNum=0;//TODO from here.

        return null;
    }

    private String getShape()
    {
        String[] lineMap=map.split("\n");
        if (lineMap.length==lineMap[0].length())
            return "Square";
        else
            return "Rectangle";
    }

    private int getChildrenPerLine() {return map.split("\n")[0].length();}
}

class SeparatorNotFoundException extends Exception
{
    public SeparatorNotFoundException(String message) {
        super(message);
    }
}
