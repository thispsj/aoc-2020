
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AoCD10 {

    private int oneJoltDiffs,threeJoltDiffs=1;
    private long totalPaths=1;
    private Integer[] adapters;

    public static void main(String[] args) {
        AoCD10 jolts=new AoCD10();
        jolts.initAdapters();
        jolts.countJolts();
        jolts.permutations();
        System.out.println(jolts.oneJoltDiffs*jolts.threeJoltDiffs);
        System.out.println(jolts.totalPaths);
    }

    private void permutations()
    {
        // I did not knew that we had so much of array manipulation in part 2 that I had to switch to lists :D
        List<Integer> adap=new ArrayList<>(adapters.length+2);
        Collections.addAll(adap,adapters);
        adap.add(adap.get(adapters.length-1)+3);
        adap.add(0);
        Collections.sort(adap);
        List<List<Integer>> rdaplist=new ArrayList<>();//Count the lists having adapters who have less than 3 diff between them.
        List<Integer> list=new ArrayList<>();
        list.add(0);
        for (int i = 0; i < adap.size()-1; i++) {
            int diff=adap.get(i+1)-adap.get(i);
            if(diff==3)
            {
                rdaplist.add(list);
                list=new ArrayList<>();
                list.add(adap.get(i));
            }
            else
            {
              list.add(adap.get(i));
            }
        }
        // Multiply the number of times reqd. If we calculate all the power sets n(P(A))=2^(No. of Elements of the set), then in sets having n(A)=1or2 will have no value here. We have to follow same path. When n(A)=3 n(P(A))=8 but here only 2 subsets are valid, so multiply by 2. Same applies for other sets. You just need to find that once.
        for (List<Integer> lst :
                rdaplist) {
            switch(lst.size())
            {
                case 1:case 2:
                    break;
                case 3:
                    totalPaths*=2;
                    break;
                case 4:
                    totalPaths*=4;
                    break;
                case 5:
                    totalPaths*=7; // This happens in almost in every puzzle input. At first I thought this is rare.
                    break;
            }
        }
    }

    private void countJolts()
    {
        int lastAdapter=0;

        for (int i :
                adapters) {
            int diff=i-lastAdapter;
            lastAdapter=i;
            if(diff==1)
                oneJoltDiffs++;
            else if(diff==3)
                threeJoltDiffs++;
        }
    }

    private void initAdapters()
    {
        String file = "";
        try{
            FileInputStream fis=new FileInputStream("./input");
            file= fToString(fis);
        }
        catch (IOException e)
        {System.exit(1);}
        String[] adps =file.split("\n");
        adapters=new Integer[adps.length];
        for (int i = 0; i < adapters.length; i++) {
            adapters[i]=Integer.parseInt(adps[i]);
        }
        Arrays.sort(adapters); // Sort the array in ascending order.
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
