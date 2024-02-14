//Hello my name is Dallin Whitaker and this program count from 1 to 100 tells you if a nuber is odd or even and gives the sum as well.
public class main {
    public static void main(String[] args) {
        int LengthCount = 100;
        String IsEven = "false";
        int TotalSum = 0;
        for (int i = 0; i <= LengthCount; i++) {
            //Sets IsEven var as a odd number unless it has a remainder of 0.
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