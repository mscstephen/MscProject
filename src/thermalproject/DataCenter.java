package thermalproject;

/**
 *
 * @author fsmm2
 */
import java.util.*;

public class DataCenter {

    int count = 0;
    int[] coolingPlan = {0, 0, 0, 0};
    //cooling plan from optimization. Not sure of exact format
    int[] futureCooling;
    int[] intensityPlan;
    String[] racks = {"a", "b", "c", "d"};
    //enum char[] better suited for racks. don't know how.
    ArrayList<CPU> list = new ArrayList<CPU>();
    double[] s;

    public DataCenter() {
        int j = 0;
        while (j < 4) {
            int i = 1;
            String current = racks[j];
            while (i < 11) {
                //TESTING purposes: one CPU with excessive heat PLACEHOLDER: testing methods
                if (i == 3 && j == 1) {
                    CPU name = new CPU(i, current, 343.15, 0, true);
                    i++;
                    list.add(name);
                } else {
                    CPU name = new CPU(i, current);
                    i++;
                    list.add(name);
                }


            }
            j++;
        }
    }

    public void showTempArray() //need to add useful getTempArray() PLACEHOLDER: stub
    {
        for (CPU a : list) {
            System.out.println(a.getName() + ": " + (a.getTemp() - 273.15));
        }
    }

    public ArrayList getTempArray() {
        ArrayList array = new ArrayList();
        for (CPU a : list) {
            float temp = (float) (a.getTemp() - 273.15);
            array.add(temp);
        }
        return array;



    }

    public void showIntensityArray() //need to add useful getIntensityArray() PLACEHOLDER: stub
    {
        for (CPU a : list) {
            System.out.println(a.getName() + ": " + a.getIntensity());
        }
    }

    public ArrayList<String> getHotspots() //should also timestamp array, as may runs will be taken
    {
        ArrayList<String> stringlist = new ArrayList<String>();
        for (CPU a : list) {
            double temp = a.getTemp();
            //temp 343.15 is a PLACEHOLDER: group decision
            if (temp > 343.15) {
                String hot = "!" + a.getName() + " : " + (a.getTemp() - 273.15) + "!";
                stringlist.add(hot);
            }

        }
        return stringlist;

    }

    public void showHotspots() {
        ArrayList<String> secondstring = new ArrayList<String>();
        for (CPU a : list) {
            double temp = a.getTemp();
            if (temp > 343.15) {
                String hot = "!" + a.getName() + " : " + (a.getTemp() - 273.15) + "!";
                secondstring.add(hot);
            }

        }
        for (String a : secondstring) {
            System.out.println(a);
        }

    }

    public void updateArray(int[] q) {
        CPU current = new CPU();
        CPU next = new CPU();

        for (int p = 0; p + 1 < list.size(); p++) {
            if (p < 11) {
                current = list.get(p);
                if (current.getPos() != 10) {
                    next = list.get(p + 1);
                    current.run(q[0], next);
                    list.set(p, current);
                    list.set(p + 1, next);
                } else {
                    current.run(q[0]);
                    list.set(p, current);
                }

            }
            if (10 < p && p < 21) {
                current = list.get(p);
                if (current.getPos() != 10) {
                    next = list.get(p + 1);
                    current.run(q[1], next);
                    list.set(p, current);
                    list.set(p + 1, next);
                } else {
                    current.run(q[1]);
                    list.set(p, current);
                }
            }


            if (20 < p && p < 31) {
                current = list.get(p);
                if (current.getPos() != 10) {
                    next = list.get(p + 1);
                    current.run(q[2], next);
                    list.set(p, current);
                    list.set(p + 1, next);
                } else {
                    current.run(q[2]);
                    list.set(p, current);
                }
            }
            if (30 < p) {
                current = list.get(p);
                if (current.getPos() != 10) {
                    next = list.get(p + 1);
                    current.run(q[3], next);
                    list.set(p, current);
                    list.set(p + 1, next);
                } else {
                    current.run(q[3]);
                    list.set(p, current);
                }
            }
            //run each CPUs run() function to update the array. Will need to be certain runs from logically
            //lowest CPU to highest. Need to add rule for topmost CPUs and separate out racks PLACEHOLDER: improve
        }
    }

    public void showArrayLength() {
        System.out.println(list.size());
    }

    public void runArray(int i) {
        for (int j = 0; j < i; j++) {
            if (count <= 30) {
                this.updateArray(coolingPlan);
                count++;
            } else {
                coolingPlan = futureCooling;
                this.updateArray(coolingPlan);
                count = 1;
            }

        }
        //   this.showTempArray();

    }

    public void predictArray(int i)
    {
        for(int j=0; j<i; j++)
        this.updateArray(coolingPlan);
    }

    public void HotspotDisplay() {
        ArrayList<String> hotspots = this.getHotspots();

        for (String a : hotspots) {
            System.out.println(a);
        }
    }

    public void updateCooling(int[] a) {
        futureCooling = a;
    }

//count defines the time in the datacenter. at 30, i.e. 5 minute mark, new cooling plan is implemented and count is reset.
    public int getCount()
    {
        return count;
    }

}
