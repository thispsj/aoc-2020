import java.io.FileInputStream;

public class AoCD9 {
    private int index;
    private String cp[];
    public static void main(String[] args) {
        FileInputStream fis;
        AoCD9 doTask=new AoCD9();
        try {
            fis=new FileInputStream("input");
        }
        catch (Exception e)
        {fis=(FileInputStream)System.in;}
        String file;
        file= doTask.ftoString(fis).replace("\r","");
        int num= doTask.rXmas(file);
        System.out.println(num);
        System.out.println(doTask.encW(num));
    }

    private int encW(int nfx)
    {
        int[] set=cset(nfx);
        int max=0,min=Integer.MAX_VALUE;
        for (int i :
                set) {
            if (i>max)
                max=i;
        }
        for (int i :
                set) {
            if (i<min)
                min=i;
        }
        return max+min;
    }

    private int[] cset(int nfx)
    {
        boolean sw=false;
        int l=0,u=0;
        for (int i = 0; i <index; i++) {
            int sum=0;
            for (int j =i; j<index&&sum<=nfx;j++) {
                sum+=Integer.parseInt(cp[j]);
                if (sum==nfx)
                {sw=true;l=i;u=j;}
            }
            if (sw)
                break;
        }
        int[] set=new int[(u-l)+1];
        for (int i=l,j=0;i<=u&&j<set.length;i++,j++) {
            set[j]=Integer.parseInt(cp[i]);
        }
        return set;
    }

    private int rXmas(String file)
    {
        cp=file.split("\n");
        int[] preamble=new int[25]; int pn = 0,tn=0;
        for (int i = 0; i <cp.length; i++) {
            int k=Integer.parseInt(cp[i]);
            if (i>0)
                pn=Integer.parseInt(cp[i-1]);
            if (i<25)
            {preamble[i]=k;continue;}
            if(i==25)
            { if (!followsXmas(preamble,k))
                {tn=k;index=i;break;}
              else
                  continue;
            }
            preamble=delFirstElement(preamble);
            preamble[24]=pn;
            if (followsXmas(preamble,k))
                continue;
            tn=k;
            index=i;
            break;
        }
        return tn;
    }

    private int[] delFirstElement(int[] arr)
    {
        int[] newArr=new int[arr.length];
        for (int i = 1,j=0; i < arr.length&&j<newArr.length; i++,j++) {
            newArr[j]=arr[i];
        }
        return newArr;
    }

    private boolean followsXmas(int[] preamble,int tbc)
    {
        for (int i = 0; i < preamble.length; i++) {
            int k=preamble[i];
            for (int j = 0; j < preamble.length; j++) {
                if (j==i)
                    continue;
                int sum=k+preamble[j];
                if (sum==tbc)
                    return true;
            }
        }
        return false;
    }

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
    }
}
