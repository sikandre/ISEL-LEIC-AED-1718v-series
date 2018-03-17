package series.serie1;


import java.util.function.Predicate;

public class Arrays {
    public	static	int	countEquals(int[] v1, int l1, int r1, int[]	v2,	int	l2,	int	r2){
        int	count=0;
        while(l1<=r1 && l2<=r2){
            if(v1[l1] > v2[l2])
                l2++;
            else
            if(v1[l1] < v2[l2])
                l1++;
            else{
                count++;
                l1++;
                l2++;
            }
        }
        return	count;
    }

    public static int removeIf(Integer[] v, int l, int r, Predicate<Integer> predicate){
        int idx=0;
        for (; l <= r; l++) {
            if(predicate.test(v[l]))
                v[idx++]=v[l];
        }
        return idx;
    }

    //ex 2
    public static int maximum(int[] v, int left, int right){
        if(left>right)
            return 0;
        int mid = left + (right - left) / 2;
        if(mid==left && v[mid+1] < v[mid])
            return v[mid];
        if (v[mid-1] < v[mid] && v[mid+1] < v[mid])
            return v[mid];
        if(v[mid-1]<v[mid] && v[mid+1]>v[mid])
            return maximum(v, mid + 1, right); //direita
        return maximum(v,left,mid);
    }

    //ex 3
    public static int[] countLessOrEqual(int[] a, int[] b){
        int[]res=new int[a.length];
        int idx= a.length-1;
        for (int i = b.length-1 ; i>=0 && idx>=0;) {
            if( a[idx] >= b[i])
                res[idx--]=i+1;
            else
                i--;
        }
        return res;
    }

    // ex 4
    public static int countInverses(String[] v, int i, int i1) {
        quickSort(v,i,i1);
        int count = 0;
        for (; i <= i1; i++)
            count+=binarySearch(v, i, i1, reverse(v[i]));
        return count;
    }

    public static int binarySearch(String[] names, int left, int right, String elem) {
        if(left>right)
            return 0;
        int mid = left + (right - left) / 2;
        if (names[mid].equals(elem)) {
            return 1;
        }
        if(strCmp(names[mid],elem)>0)
            return binarySearch(names, left, mid-1, elem);
        return binarySearch(names, mid + 1, right, elem); //direita
    }

    private static void quickSort(String[] v, int min, int max) {
        if (min>=max) return;
        String str = v[min];
        int l = min + 1;
        int r = max;
        do {
            while (l <= max && strCmp(v[l], str) < 0)
                ++l;
            while (r >= min && strCmp(v[r], str) > 0)
                --r;
            if (l < r) {
                String aux = v[l];
                v[l++] = v[r];
                v[r--] = aux;
            } else if (l == r)
                ++l;
        }while (l<=r);
        v[min]=v[r];
        v[r]=str;
        quickSort(v,min,r-1);
        quickSort(v,r+1,max);
    }

    private static String reverse(String txt) {
        StringBuilder res=new StringBuilder(txt.length());
        for (int i = txt.length()-1; i >= 0; i--)
            res.append(txt.charAt(i));
        return res.toString();
    }

    private static int strCmp(String s, String elem) {
        int len = Math.min(s.length(), elem.length());
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) != elem.charAt(i))
                return s.charAt(i)- elem.charAt(i);
        }
        return s.length()- elem.length();
    }


}
