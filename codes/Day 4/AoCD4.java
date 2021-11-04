import java.io.FileInputStream;

public class AoCD4 {

    private String passport;

    public static void main(String[] args)
    {
        FileInputStream fis;
        try {
            fis=new FileInputStream("./input");
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
        for (String s : passports) {
            doTask.passport = s;
            if (doTask.isPassport())
                valid++;
        }

        System.out.println("Valid Passports : "+valid);
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

    private boolean isPassport()
    {
        int byr=7,iyr=8,eyr=9,hgt=10,ecl=11,hcl=12,pid=13,cid=14;
        int uuid=0;
        if(passport.contains("byr")&&checkAttr("byr")) uuid+=byr;
        if (passport.contains("iyr")&&checkAttr("iyr")) uuid+=iyr;
        if (passport.contains("eyr")&&checkAttr("eyr")) uuid+=eyr;
        if (passport.contains("hgt")&&checkAttr("hgt")) uuid+=hgt;
        if (passport.contains("ecl")&&checkAttr("ecl")) uuid+=ecl;
        if (passport.contains("hcl")&&checkAttr("hcl")) uuid+=hcl;
        if (passport.contains("pid")&&checkAttr("pid")) uuid+=pid;
        if (passport.contains("cid")&&checkAttr("cid")) uuid+=cid;

        return uuid==70||uuid==84;
    }
    private boolean checkAttr(String attrName)
    {
        passport=passport.replace("\n"," ");
        String[] attrs=passport.split(" "),spl;
        switch (attrName)
        {
            case "byr":
                for (String a:attrs)
                {
                    if(a.contains("byr"))
                    {   spl=a.split(":");
                        if (spl[1].length()==4) {
                            int byr=Integer.parseInt(spl[1]);
                            if(byr>1919&&byr<2003)
                                return true;
                        }
                    }
                }
                break;
            case "iyr":
                for (String a:attrs)
                {
                    if(a.contains("iyr"))
                    {   spl=a.split(":");
                        if (spl[1].length()==4) {
                            int byr=Integer.parseInt(spl[1]);
                            if(byr>2009&&byr<2021)
                                return true;
                        }
                    }
                }
                break;
            case "eyr":
                for (String a:attrs)
                {
                    if(a.contains("eyr"))
                    {   spl=a.split(":");
                        if (spl[1].length()==4) {
                            int byr=Integer.parseInt(spl[1]);
                            if(byr>2019&&byr<2031)
                                return true;
                        }
                    }
                }
                break;
            case "hgt":
                for (String a:attrs)
                {
                    if(a.contains("hgt"))
                    {   spl=a.split(":");
                        if (spl[1].contains("cm")) {
                            spl[1]=spl[1].replace("cm","");
                            int byr=Integer.parseInt(spl[1]);
                            if(byr>149&&byr<194)
                                return true;
                        }

                        else if (spl[1].contains("in"))
                        {
                            spl[1]=spl[1].replace("in","");
                            int byr=Integer.parseInt(spl[1]);
                            if(byr>58&&byr<77)
                                return true;
                        }
                    }
                    

                }
                break;
            case "ecl":
                for (String a:attrs)
                {
                    if(a.contains("ecl"))
                    {   spl=a.split(":");
                        if (spl[1].contains("amb")||spl[1].contains("blu")||spl[1].contains("brn")||spl[1].contains("gry")||spl[1].contains("grn")||spl[1].contains("hzl")||spl[1].contains("oth")) return true;
                    }
                }
                break;
            case "hcl":
                for (String a:attrs)
                {
                    if(a.contains("hcl"))
                    {   spl=a.split(":");
                        if (spl[1].length()==7) {
                            if(isHexString(spl[1]))
                                return true;
                        }
                    }
                }
                break;
            case "pid":
                for (String a:attrs)
                {
                    if(a.contains("pid"))
                    {   spl=a.split(":");
                        if (spl[1].length()==9) {
                            try{
                                Integer.parseInt(spl[1]);
                                return true;
                            }
                            catch (Exception e)
                            {return false;}
                        }
                    }
                }
                break;
            case "cid":
                return true;
            default:
                return false;
        }

        return false;
    }

    private boolean isHexString(String input)
    {
        for(int i=0;i<input.length();i++)
        {
            char c=input.charAt(i);

            if(Character.isDigit(c)||c=='#')
                continue;
            if(c>'f'||c<'a')
                return false;
        }

        return true;
    }
}
