public class ExecutionTimeLong {
    private static long sum =0;

    public static void main(String[] args) {
        ExecutionTimeLong extil= new ExecutionTimeLong();
        System.out.println(extil.fragment_1(10));
        System.out.println(extil.fragment_2(10));
        System.out.println(extil.fragment_3(10));
        System.out.println(extil.fragment_4(10));
        System.out.println(extil.fragment_5(10));
        System.out.println(extil.fragment_6(10));
        System.out.println(extil.fragment_7(10));
    }
    private long set (){
        sum=0;
        return sum;
    }
    //Fragment 1
    private long fragment_1 (long n){
        for (long i =0; i<n ;i++)
            sum++;
        return sum;
    }

    //Fragment 2
    private long fragment_2 (long n){
        for (long i =0; i<n ;i++)
            for (long k=0 ;k<n;k++)
            sum++;
        return sum;
    }

    //Fragment 3
    private long fragment_3 (long n){
        for (long i =0; i<n ;i++)
            for (long k=i ;k<n;k++)
                sum++;
        return sum;
    }
    //Fragment 4
    private long fragment_4 (long n){
        for (long i =0; i<n ;i++)
            sum++;
            for (long k=0 ;k<n;k++)
                sum++;
        return sum;
    }
    //Fragment 4
    private long fragment_5 (long n){
        for (long i =0; i<n ;i++)
            for (long k=0 ;k<n*n;k++)
                sum++;
        return sum;
    }
    //Fragment 6
    private long fragment_6 (long n){
        for (long i =0; i<n ;i++)
            for (long k=0 ;k<i;k++)
                sum++;
        return sum;
    }

    //Fragment 7
    private long fragment_7 (long n){
        for (long i =1; i<n ;i++)
            for (long k=0 ;k<n*n;k++)
                if (k%i==0){
                    for (long j =0;j<k ;k++)
                        sum++;
                }
        return sum;
    }

    private static class String {
    }
}
