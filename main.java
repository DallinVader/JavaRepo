public class main {
    public static void main(String[] args) {
        int LengthCount = 100;
        String IsEven = "false";
        int TotalSum = 0;
        for (int i = 0; i <= LengthCount; i++) {
            IsEven = "Is a odd number";
            if (i % 2 == 0) {
                IsEven = "Is a even number";
            }
            TotalSum += i;
            System.err.println(i + " " + IsEven);
        }
        System.err.println("The total sum is " + TotalSum );
    }
}