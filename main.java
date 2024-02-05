public class main {
    public static void main(String[] args) {
        int LengthCount = 100;
        String IsEven = "false";
        int TotalSum = 0;
        for (TotalSum = 0; TotalSum <= LengthCount; TotalSum++) {
            IsEven = "Is a odd number";
            if (TotalSum % 2 == 0) {
                IsEven = "Is a even number";
            }
            System.err.println(TotalSum + " " + IsEven);
        }
        TotalSum--;
        System.err.println("The total sum is " + TotalSum );
    }
}