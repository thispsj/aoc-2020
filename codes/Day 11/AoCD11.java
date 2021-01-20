import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class AoCD11 {

    int maxcx,maxcy;
    public static void main(String[] args)
    {
        AoCD11 doTask=new AoCD11();
        FileInputStream fis;
        try{
            fis=new FileInputStream("input");
        }
        catch(Exception e)
        {fis=null;}
        String mfile=doTask.fToString(fis);int s = 0;
        mfile=mfile.replace("\r","");
        char[][] imap =doTask.exMap(mfile),cp=new char[doTask.maxcy][doTask.maxcx];
        while(doTask.compare(imap, cp)) {
            System.arraycopy(imap,0,cp,0,imap.length);
            imap=doTask.simca(imap);
            s = doTask.countSeats(imap);
            //doTask.wFile(imap); For Debugging purposes only.
        }
        System.out.println("Part 1 : "+s);
        imap =doTask.exMap(mfile);cp=new char[doTask.maxcy][doTask.maxcx];
        while(doTask.compare(imap, cp)) {
            System.arraycopy(imap,0,cp,0,imap.length);
            imap=doTask.simcb(imap);
            s = doTask.countSeats(imap);
            doTask.wFile(imap);
        }

        System.out.println("Part 2 : "+s);
    }

    private boolean compare(char[][] map1,char[][] map2)
    {
        for(int i=0;i<maxcy;i++)
        {
            for (int j=0;j<maxcx;j++)
            {
                if(map1[i][j]!=map2[i][j])
                    return true;

            }
        }
        return false;
    }

    private char[][] exMap(String map)
    {
        String[] mapAr =map.split("\n");
        maxcx=mapAr[0].length();
        maxcy=mapAr.length;
        char[][] mapi=new char[mapAr.length][mapAr[0].length()];
        for(int i=0;i<maxcy;i++)
        {
            for (int j=0;j<maxcx;j++)
            {
                mapi[i][j]=mapAr[i].charAt(j);
            }
        }
        return mapi;
    }

    private char[][] simca(char[][] map)
    {
        char[][] simulator=new char[map.length][map[0].length];
        for (int i = 0; i < maxcy; i++) {
            for (int j = 0; j < maxcx; j++) {
                if(map[i][j]!='.')
                {
                if (map[i][j]=='L')
                {
                    if (countAdjSeats(map,i,j)==0)
                        simulator[i][j]='#';
                    else
                        simulator[i][j]='L';
                }

                else if (map[i][j]=='#')
                {
                    if (countAdjSeats(map,i,j)>=4)
                        simulator[i][j]='L';
                    else
                        simulator[i][j]='#';
                }}
                else
                {
                    simulator[i][j]='.';
                }
            }
        }
        return simulator;
    }

    private char[][] simcb(char[][] map)
    {
        char[][] simulator=new char[map.length][map[0].length];
        for (int i = 0; i < maxcy; i++) {
            for (int j = 0; j < maxcx; j++) {
                if(map[i][j]!='.')
                {
                    if (map[i][j]=='L')
                    {
                        if (countDirSeats(map,i,j)==0)
                            simulator[i][j]='#';
                        else
                            simulator[i][j]='L';
                    }

                    else if (map[i][j]=='#')
                    {
                        if (countDirSeats(map,i,j)>=5)
                            simulator[i][j]='L';
                        else
                            simulator[i][j]='#';
                    }}
                else
                {
                    simulator[i][j]='.';
                }
            }
        }
        return simulator;
    }
    
    private String fToString(FileInputStream fileInputStream)
    {
        try {
            String obj="";
            int i=fileInputStream.read();
            while(i!=-1)
            {
                obj=obj+(char)i;
                i=fileInputStream.read();
            }
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private int checkSeat(char[][] map,int i,int j,int op_i,int op_j)
    {
        try{
        while (i>=0&&i<maxcy&&j>=0&&j<maxcx)
        {
            i+=op_i;j+=op_j;
            if (map[i][j]=='#')
                    return 1;
                else if (map[i][j]=='L')
                    return 0;}
            }

        catch (Exception e)
        { return 0;}
        return 0;
    }

    private int countDirSeats(char[][] map, int i, int j)
    {
        int adseats=0;
        adseats+=checkSeat(map,i,j,0,1);
        adseats+=checkSeat(map,i,j,0,-1);
        adseats+=checkSeat(map,i,j,1,0);
        adseats+=checkSeat(map,i,j,-1,0);
        adseats+=checkSeat(map,i,j,1,1);
        adseats+=checkSeat(map,i,j,-1,-1);
        adseats+=checkSeat(map,i,j,1,-1);
        adseats+=checkSeat(map,i,j,-1,1);
        return adseats;

    }

    private int countAdjSeats(char[][] map, int i, int j)
    {
        int adseats=0;char ch;
        if(i==0)
        {
            if(j==maxcx-1)
            {
                ch=map[i+1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i][j-1];
                if(ch=='#')
                    adseats++;
                ch=map[i+1][j-1];
                if (ch=='#')
                    adseats++;
            }

            else if(j==0)
            {
                ch=map[i+1][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i][j+1];
                if(ch=='#')
                    adseats++;
                ch=map[i+1][j];
                if (ch=='#')
                    adseats++;
            }

            else
            {
                ch=map[i+1][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i+1][j-1];
                if(ch=='#')
                    adseats++;
                ch=map[i+1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i][j+1];
                if(ch=='#')
                    adseats++;
                ch=map[i][j-1];
                if(ch=='#')
                    adseats++;
            }
        }
        else if(i==maxcy-1)
        {
            if(j==maxcx-1)
            {
                ch=map[i-1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i][j-1];
                if(ch=='#')
                    adseats++;
                ch=map[i-1][j-1];
                if (ch=='#')
                    adseats++;
            }

            else if(j==0)
            {
                ch=map[i-1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j+1];
                if(ch=='#')
                    adseats++;
                ch=map[i][j+1];
                if (ch=='#')
                    adseats++;
            }

            else
            {
                ch=map[i-1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j-1];
                if(ch=='#')
                    adseats++;
                ch=map[i-1][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i][j+1];
                if(ch=='#')
                    adseats++;
                ch=map[i][j-1];
                if(ch=='#')
                    adseats++;
            }
        }
        else
        {
            if(j==maxcx-1)
            {
                ch=map[i][j-1];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j];
                if(ch=='#')
                    adseats++;
                ch=map[i+1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j-1];
                if (ch=='#')
                    adseats++;
                ch=map[i+1][j-1];
                if (ch=='#')
                    adseats++;
            }

            else if(j==0)
            {
                ch=map[i][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j];
                if(ch=='#')
                    adseats++;
                ch=map[i+1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i+1][j+1];
                if (ch=='#')
                    adseats++;
            }

            else {
                ch=map[i+1][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i][j+1];
                if(ch=='#')
                    adseats++;
                ch=map[i-1][j+1];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j];
                if (ch=='#')
                    adseats++;
                ch=map[i-1][j-1];
                if (ch=='#')
                    adseats++;
                ch=map[i][j-1];
                if (ch=='#')
                    adseats++;
                ch=map[i+1][j-1];
                if (ch=='#')
                    adseats++;
                ch=map[i+1][j];
                if (ch=='#')
                    adseats++;
            }

        }
        return adseats;

    }

    private int countSeats(char[][] map)
    {
        int seats=0;
        for (int i = 0; i <maxcy; i++) {
            for (int j = 0; j < maxcx; j++) {
                if (map[i][j]=='#')
                    seats++;
            }
        }
        return seats;
    }


    private void wFile(char[][] map)
    {
        try{
            FileOutputStream fos=new FileOutputStream("E:\\Projects\\java\\AoC 2020\\src\\output.txt");
            PrintWriter pw=new PrintWriter(fos,true);
            for (int i = 0; i < maxcy; i++) {
                for (int j = 0; j < maxcx; j++) {
                    pw.print(map[i][j]);
                }
                pw.println();
            }
        }
        catch (Exception e)
        {System.out.println(0);}
    }
}
