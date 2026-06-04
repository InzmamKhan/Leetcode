class Solution {
    public boolean isPowerOfThree(int n) {
        int x=0;
        while(true){
            if(n == Math.pow(3,x) ){
                return true;
            }
            else if(n > Math.pow(3,x)){
                x++;
            }
            else{
                return false;
            }
        }
    }
}