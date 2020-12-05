import java.io.FileInputStream;

public class AoCD2 {

    public static void main(String[] args)
    {
        FileInputStream fis;
        try {
            fis=new FileInputStream("input");
        }
        catch (Exception e)
        {System.out.println("File Not Found.");
            fis=null;
        }
        AoCD2 doTask=new AoCD2();
        String rf= doTask.ftoString(fis);
        rf=rf.replace("\r","");
        int len=rf.split("\n").length,k=0;
        for (int i=0;i<len;i++)
        {
            if(doTask.passwordPolicy(rf,i))
                k++;
        }
        System.out.println("Valid Passwords : "+k);
    }

    private boolean passwordPolicy(String file,int pwdno)
    {
        String cf=file.split("\n")[pwdno];
        String[] div=cf.split(":");
        div[0]=div[0].trim();
        div[1]=div[1].trim();
        int lr=Integer.parseInt(div[0].split(" ")[0].split("-")[0]),hr=Integer.parseInt(div[0].split(" ")[0].split("-")[1]),k;
        String ch=div[0].split(" ")[1];
        div[1]=div[1].replace(ch,"#");
        k=0;
        for (int i=0;i<div[1].length();i++)
        {
            char chr=div[1].charAt(i);
            if (chr=='#'&&(i==(lr-1)||i==(hr-1)))
            { k++;}
        }
        return k==1;
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
        }
        catch (Exception e)
        {System.out.println("File not read.");}
        return inp;
    }
}
