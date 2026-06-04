class Solution {
    public int hammingWeight(int n) {
        int num = n;
        String binary = "";
        int count = 0;
        while(num != 0){
            if(num % 2 !=0){
                num -= 1;
                num /= 2;
                binary += "1";
                count++;
            }
            else{
                num /= 2;
                binary += "0";
            }
        }
        return count;
    }
}
