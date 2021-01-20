import java.io.FileInputStream;

public class AoCD5 {

    public static void main(String[] args)
    {
        FileInputStream fis;
        try {
            fis=new FileInputStream("input");
        }
        catch (Exception e)
        {fis=(FileInputStream)System.in;}
        AoCD5 doTask=new AoCD5();
        String file= doTask.ftoString(fis);
        file=file.replace("\r","");
        int hsid= doTask.getHighSeatID(file);
        System.out.println(hsid);
        int myid= doTask.getMySeatID(file);
        System.out.println(myid);
    }

    private int getHighSeatID(String file)
    {
        String[] bPass=file.split("\n");
        int sid=Integer.MIN_VALUE;
        for(String s:bPass)
        {
            int tsid=seatID(getRow(s),getCol(s));
            if(tsid>sid)
                sid=tsid;
        }
        return sid;
    }

    private boolean searchSeat(int sid,String file)
    {
        String[] passes=file.split("\n");
        for (String pass : passes) {
            if (sid == seatID(getRow(pass),getCol(pass)))
                return true;
        }
        return false;
    }

    private int getMySeatID(String file)
    {
        String[] passes=file.split("\n");
        int mySid =0,cid;
        for (int i = 0; i < passes.length-1; i++) {
            cid=seatID(getRow(passes[i]),getCol(passes[i]));
            if (!searchSeat(cid+1,file))
                mySid=cid+1;
        }
        return mySid;
    }

    private int getCol(String pass)
    {
        int col;
        String bin="";
        for (int i =7; i <10; i++){
            char ch=pass.charAt(i);
            if(ch=='L')
                bin+=0;
            else
                bin+=1;
        }
        col=Integer.parseInt(bin,2);
        return col;
    }

    private int getRow(String pass)
    {
        int row;
        String bin="";
        for (int i =0; i <7; i++){
            char ch=pass.charAt(i);
            if(ch=='F')
                bin+=0;
            else
                bin+=1;
        }
        row=Integer.parseInt(bin,2);
        return row;
    }

    private int seatID(int r,int c) {return (r*8)+c;}

    private String ftoString(FileInputStream fileInputStream)
    {
        String inp="";

        try {
            int i=fileInputStream.read();
            while (i!=-1)
            {
                inp=inp.concat(Character.toString((char)i));
                i=fileInputStream.read();
            }
            fileInputStream.close();
        }
        catch (Exception e)
        {System.out.println("File not read.");}
        return inp;
    }}
