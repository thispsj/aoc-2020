import java.io.FileInputStream;

public class AoCD1 {
    public static void main(String[] args) {
        AoCD1 dotask=new AoCD1();
        FileInputStream fis;
        try {
            fis=new FileInputStream("input");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fis=null;
        }

     String file=dotask.ftoString(fis).replace("\r","");
     long ans=dotask.mul1(file);
     System.out.println(ans);
     ans= dotask.mul2(file);
     System.out.println(ans);
    }

    private long mul1(String file)//Part 1
    {
        String[] nums =file.split("\n");
        long mul=0;boolean sf=false;
        for (int i = 0; i <nums.length; i++) {
            int k=Integer.parseInt(nums[i]);
            for (int j = 0; j <nums.length; j++) {
                if (j==i)
                    continue;
                int m=Integer.parseInt(nums[j]),sum=k+m;
                if (sum==2020)
                { sf=true;mul=k*m;break;}
            }
            if (sf)
                break;
        }
        return mul;
    }

    private long mul2(String file)//Part 2
    {
        String[] nums =file.split("\n");
        long mul=0;boolean sf=false;
        for (int i = 0; i <nums.length; i++) {
            int k=Integer.parseInt(nums[i]);
            for (int j = 0; j <nums.length; j++) {
                if (j==i)
                    continue;
                int m=Integer.parseInt(nums[j]);
                for (int l = 0; l < nums.length; l++) {

                    if (l==i||l==j)
                        continue;
                    int o=Integer.parseInt(nums[l]),sum=k+m+o;
                    if (sum==2020)
                    { sf=true;mul=k*m*o;break;}
                }

            }
            if (sf)
                break;
        }
        return mul;
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
