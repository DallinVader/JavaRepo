//My name is Dallin Whitaker, this program is to sort a array to least to greatest.

import java.lang.Math;

public class sortingscript {

    //Variables.
    int[] ListOfNum = {7, 7, 6, 8, 9, 2, 1, 2, 3};
    int[] arrayToSort={1,3,4,5,1,23,57,126,4,543,345,23,12,45,67,97};

    //Function that will run at the start of the program;
    void main(){
        if(Math.round(Math.random()) == 0) {
            BubbleSort(ListOfNum);
        }
        else{
            BubbleSort(arrayToSort);
        }
    }

    //Function to sort the array.
    void BubbleSort(int[] CurrentArray){
        boolean SwapNum = true;
        while (SwapNum) {
            SwapNum = false;
            //Loops through the array and if the number is greater than the next it swaps the numbers. Reapeats it for the length of the array to sort all of them properly.
            for (int a = 0; a < CurrentArray.length; a++) {
                for (int i = 0; i < CurrentArray.length - 1; i++) {
                    //Swaps the numbers
                    if (CurrentArray[i] > CurrentArray[i + 1]) {
                        int Temp = CurrentArray[i];
                        CurrentArray[i] = CurrentArray[i + 1];
                        CurrentArray[i + 1] = Temp;
                        SortIntArrayAssending(CurrentArray, i);
                    }
                }
            }
            PrintIntArray(CurrentArray);
        }
    }

    void SortIntArrayAssending(int[] TempArray, int SortedNum){
        if (TempArray[SortedNum] > TempArray[SortedNum + 1]) {
            int Temp = TempArray[SortedNum];
            TempArray[SortedNum] = TempArray[SortedNum + 1];
            TempArray[SortedNum + 1] = Temp;
        }
    }

    //Prints a int[] array.
    void PrintIntArray(int[] TempArray){
        for (int i = 0; i < TempArray.length; i++) {
            System.err.print(TempArray[i] + " ");
        }
    }
}
