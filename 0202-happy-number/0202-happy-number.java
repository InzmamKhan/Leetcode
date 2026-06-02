class Solution {
    int recAdd(int n){
        int result = 0;
        while(n > 0){
            result += Math.pow(n%10, 2);
            n /= 10;
        }
        return result;
    }
    
    public boolean isHappy(int n) {
        if(n < 1){
            return false;
        }
        
        int result = n;

        while(result > 9){
            result = recAdd(result);
        }
        
        if(result == 1 || result == 7){
            return true;
        }
        
        return false;
    }
}