//My name is Dallin Whitaker, this program creates a array and sets each value of the array from 1 to 100 and tells if number is odd or even and prints the result it also prints the total sum.
public class another {
    public static void main(String[] args) {
        String IsEven = "odd";
        int TotalSum = 0;
        int[] numbers = new int[100];

        //Sets the numbers array to the corisponding number from 1 to 100.
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        //checks if is odd or even and prints the result.
        for (int i = 0; i < numbers.length; i++) {
            TotalSum += numbers[i];
            IsEven = "odd";
            if (numbers[i] % 2 == 0) {
                IsEven = "even";
            }
            System.err.println(numbers[i] + " is " + IsEven);
        }
        //Prints the total sum.
        System.err.println("The sum is " + TotalSum);
    }
}
