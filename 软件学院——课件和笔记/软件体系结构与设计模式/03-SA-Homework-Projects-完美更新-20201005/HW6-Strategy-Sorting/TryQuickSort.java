



public class TryQuickSort{

    public static void main(String[] v){

        SortAlgorithm q = new QuickSort();
        Context context = new Context(q);
        int[] a = new int[10000000];

        for (int k=0;k< 100000; k++){
				   int randomNum = (int)(Math.random()*10000000);
				   a[k] = randomNum;
        }

        q.sort(a, context);
        long eTime = context.getExeTime();
        System.out.println("Execution time for quick sort = " + eTime);

    }
}