import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class AoCD8 {

    private int acc;
    public static void main(String[] args) {
        FileInputStream fis;
        try {
            fis=new FileInputStream("F:\\Profile_Data\\Documents\\D drive data\\Khanna\\temp\\AoC 2020\\src\\input");
        }
        catch (Exception e)
        {fis=(FileInputStream)System.in;}
        AoCD8 doTask=new AoCD8();
        String file= doTask.ftoString(fis);
        file=file.replace("\r","");
        doTask.runTasks(file.split("\n"));
        System.out.println("Part 1 : "+doTask.acc);
        doTask.acc=0;
        doTask.chkProb(file);
        System.out.println("Part 2 : "+doTask.acc);
    }

    private void runTasks(String[] cmds) //Part 1.
    {
        List<String> dict=new ArrayList<>();
        List<Integer> pos=new ArrayList<>();
        String cmd;
        for (int i=0;i<cmds.length;i++) {
            cmd=cmds[i];
            if(dict.contains(cmd)&&pos.contains(i))
                break;
            if (cmd.contains("acc"))
            acc+=execAcc(cmd);
            if(cmd.contains("jmp"))
            i+=execJmp(cmd)-1;
            dict.add(cmd);
            pos.add(i);
        }
    }

    private void chkProb(String file) //Part 2
    {
        String[] cmds=file.split("\n");
        String cmd;
        String[] nCmds =new String[cmds.length];
        for (int i=0;i<cmds.length;i++) {
            cmd=cmds[i];
            if (cmd.contains("nop"))
            {
                System.arraycopy(cmds,0,nCmds,0,cmds.length);
                nCmds[i]=cmd.replace("nop","jmp");
                if (!isInfinity(nCmds))
                 runTasks(nCmds);
            }
            if(cmd.contains("jmp"))
            {
                System.arraycopy(cmds,0,nCmds,0,cmds.length);
                nCmds[i]=cmd.replace("jmp","nop");
                if (!isInfinity(nCmds))
                    runTasks(nCmds);
            }
        }
    }

    private boolean isInfinity(String[] cmds)
    {
        List<String> dict=new ArrayList<>();
        List<Integer> pos=new ArrayList<>();
        String cmd;
        for (int i=0;i<cmds.length;i++) {
            cmd=cmds[i];
            if(dict.contains(cmd)&&pos.contains(i))
                return true;
            if(cmd.contains("jmp"))
                i+=execJmp(cmd)-1;
            dict.add(cmd);
            pos.add(i);
        }
        return false;
    }

    private int execJmp(String cmd)
    {
        return Integer.parseInt(cmd.split(" ")[1]);
    }

    private int execAcc(String cmd){return Integer.parseInt(cmd.split(" ")[1]);}

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
