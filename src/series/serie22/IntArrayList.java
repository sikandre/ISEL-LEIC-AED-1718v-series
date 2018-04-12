package series.serie22;

public class IntArrayList {
    private int [] arrayList;
    private int k;
    private int nextIdx;
    private int val;

    IntArrayList(int[]arrayList, int k){
        this.k=k;
        this.arrayList=new int[k];
        nextIdx =0;
    }

    public boolean append(int x){
        if(nextIdx >k) return false;
        arrayList[nextIdx++]=x;
        return true;
    }
    public int get(int i) {
        return arrayList[i]+val;


    }
    public void addToAll(int x){
        val = x;
    }

    public static void main(String[] args) {
        int []a= new int[3];
        new IntArrayList(a, 3);
    }
}
