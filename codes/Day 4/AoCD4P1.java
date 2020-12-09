import java.io.FileInputStream;

public class AoCD4 {

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
        AoCD4 doTask=new AoCD4();
        String file= doTask.ftoString(fis);
        file=file.replace("\r",""); // For CRLF.
        String[] passports =file.split("\n\n");
        int valid=0;
        for (String s:passports)
        {
            if (doTask.isPassport(s))
                valid++;
        }

        System.out.println("Valid Passports : "+valid);
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

    private boolean isPassport(String passport)
    {
        int byr=7,iyr=8,eyr=9,hgt=10,ecl=11,hcl=12,pid=13,cid=14;
        int uuid=0;
        if(passport.contains("byr")) uuid+=byr;
        if (passport.contains("iyr")) uuid+=iyr;
        if (passport.contains("eyr")) uuid+=eyr;
        if (passport.contains("hgt")) uuid+=hgt;
        if (passport.contains("ecl")) uuid+=ecl;
        if (passport.contains("hcl")) uuid+=hcl;
        if (passport.contains("pid")) uuid+=pid;
        if (passport.contains("cid")) uuid+=cid;

        return uuid==70||uuid==84;
    }
}
