import java.io.FileInputStream;

public class AoCD3 {

    private static String file;

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
        AoCD3 doTask=new AoCD3();
        file=doTask.ftoString(fis);
        long mulTrees= doTask.getTrees(1,1)* doTask.getTrees(3,1)* doTask.getTrees(5,1)* doTask.getTrees(7,1)*doTask.getTrees(1,2);
        System.out.println("Product of number of trees encountered on different slopes : "+mulTrees);
    }

    private int getTrees(int tr,int td)
    {
        String obj;
        _2DMap twodmap=new _2DMap(file);
        int cx=tr,cy=td,trees=0,t;
        for(int i=0;i<file.length();i++,cx+=tr,cy+=td)
        {
            t=cx%(twodmap.getChildrenPerLine()-1);
            try {
                obj = twodmap.getObjectByRowCol(cy, t);
                if(obj.equals("#"))
                    trees++;
            }
            catch (Exception e)
            {break;}
        }
        return trees;
    }

    private String ftoString(FileInputStream fileInputStream)
    {
        String inp="";
        int i=0;
        try {
            while (i!=-1)
            {
                i=fileInputStream.read();
                inp=inp.concat(Character.toString((char)i));
            }
            fileInputStream.close();
        }
        catch (Exception e)
        {System.out.println("File not read.");}
        return inp;
    }
}
class _2DMap
{
    private final String map;
    //Please Note a line separator(i.e \n) is important to use this class or a SeparatorNotFoundException is thrown.
    _2DMap(String map)
    { this.map=map; }

    public String getObjectByRowCol(int rowNo,int colNo) throws SeparatorNotFoundException
    {
        String obj;
        if (!map.contains("\n"))
            throw new SeparatorNotFoundException("Expected a line separator.");
        if (rowNo>map.split("\n").length||colNo>getChildrenPerLine())
            throw new SeparatorNotFoundException("Co-ordinates are invalid.");
        obj=""+map.split("\n")[rowNo].charAt(colNo);
        return obj;
    }

    public int getChildrenPerLine() {return map.split("\n")[0].length();}
}

class SeparatorNotFoundException extends Exception
{
    public SeparatorNotFoundException(String message) {
        super(message);
    }
}
