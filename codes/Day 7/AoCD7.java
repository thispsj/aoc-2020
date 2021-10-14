import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class AoCD7 {

    private Map<String,List<Bag>> bagRules;

    public static void main(String[] args) {
       AoCD7 bagCounter=new AoCD7();
       bagCounter.loadRules();
       bagCounter.partA();
       bagCounter.partB();
    }

    // Find bags containing at least 1 gold bag.
    private void partA()
    {
        int bagsHoldGold=0;
        for(String name: bagRules.keySet())
        {
            if(hasGold(name))
                bagsHoldGold++;
        }
        System.out.println(bagsHoldGold);
    }

    // Find the number of bags you'll eventually require with your shiny gold bag.
    private void partB()
    {
        System.out.println(bagContents("shiny gold bag"));
    }

    private boolean hasGold(String name)
    {
        List<Bag> contents=bagRules.get(name);
        if (contents==null)
            return false;
        for(Bag b:contents)
        {
            if(b.name.equalsIgnoreCase("shiny gold bag"))
                return true;
            else if(hasGold(b.name))
                return true;
        }
        return false;
    }

    private int bagContents(String name)
    {
        List<Bag> contents=bagRules.get(name);
        int count=0;
        if (contents==null)
            return count;
        for(Bag b:contents)
        {
            count+=b.count;
            count+=bagContents(b.name)*b.count;
        }
        return count;
    }

    private void loadRules()
    {
        String[] rules;
        try{
            FileInputStream fos=new FileInputStream("./input"); // Replace with full path to the input file if it does not work.
            rules=fToString(fos).split("\n");
        }
        catch(IOException e)
        {
            System.out.println("Error loading rules");
            rules=null;
            System.exit(1);

        }
        bagRules=new HashMap<>();
        for (String s :
                rules) {
            String[] nameAndRule=s.split("\\b contain \\b");
            List<Bag> bags=new ArrayList<>();
            String[] children=nameAndRule[1].split(",");
            if(!(children[0].contains("no other"))) {
                for (int i = 0; i < children.length; i++) {
                    children[i] = children[i].replace("bags", "bag").replace(".", "").trim();
                    if (children[i].substring(0,1).matches("\\d"))
                        bags.add(new Bag(children[i].substring(2),Integer.parseInt(children[i].substring(0,1))));
                    else
                        bags.add(new Bag(children[i],1));
                }
                bagRules.put(nameAndRule[0].replace("bags","bag"),bags);
            }
            else
                bagRules.put(nameAndRule[0].replace("bags","bag"),null);
        }
    }

    private String fToString(FileInputStream fileInputStream)
    {
        try {
            StringBuilder obj= new StringBuilder();
            int i=fileInputStream.read();
            while(i!=-1)
            {
                obj.append((char) i);
                i=fileInputStream.read();
            }
            return obj.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

class Bag
{
    public final String name;
    public final int count;

    public Bag(String name,int count)
    {
        this.name=name;
        this.count=count;
    }
}