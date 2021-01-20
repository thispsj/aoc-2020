import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AoCD6 {
    public static void main(String[] args)
    {
        AoCD6 doTask=new AoCD6();
        FileInputStream fis;
        try{
            fis=new FileInputStream("input");
        }
        catch(Exception e)
        {fis=null;}

        String file= doTask.fToString(fis);
        file=file.replace("\r","");
        int s= doTask.countTotalYes(file);
        System.out.println(s);
        s= doTask.countEveryoneYes(file);
        System.out.println(s);
    }

    private int countYesEo(String list)
    {
        int k=0;
        ArrayList<Character> arrayList=new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            char ch=list.charAt(i);
            if(ch!='\n'&&!arrayList.contains(ch))
            {
                list=list.replace(ch+"","#");
                arrayList.add(ch);
                if (list.contains("\n"))
                {String[] divs=list.split("\n");
                int countn=divs.length,cl=0;
                    for (String div : divs) {
                        if (div.contains("#"))
                            cl++;
                    }
                if (cl==countn)
                    k++;}
                else
                {k+=list.length();break;}
                list=list.replace('#',ch);
            }
        }
        return k;
    }

    private int countYes(String list)
    {
        int k=0;
        list=list.replace("\n","");
        for (int i = 0; i < list.length(); i++) {
            char ch=list.charAt(i);
            if(ch!='#')
            {
                k++;
                list=list.replace(ch+"","#");
            }
        }
        return k;
    }

    private int countTotalYes(String file)
    {
        int count=0;
        String[] lists=file.split("\n\n");
        for (String list : lists) {
            count += countYes(list);
        }
        return count;
    }

    private int countEveryoneYes(String file)
    {
        int count=0;
        String[] lists=file.split("\n\n");
        for (String list : lists) {
            count += countYesEo(list);
        }
        return count;
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

}
