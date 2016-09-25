package com.home.latest.dp;

import java.util.List;

/**
 * Created by pranabdas on 8/15/16.
 */
public class TextJustification {

    /**
     * min{ (pageWidth - textWidth)^3 ,  infinity if textWidth > pageWidth}
     * 1st word starts the 1st line, next guess has to be the next word starting 2nd line and so on.
     * Last line can only be left justified.
     * @param text
     * @param pageWidth
     * @return
     */
    public List<String> fullyJustify(String[] text, int pageWidth){
        int N = text.length;
        int[][] dp = new int[N+1][N+1];
        for(int i=1; i<N+1; i++){
            for(int j=i; j<N+1; j++){
                dp[i][j] = dp[i-1][j-1] + badness(text, i, j, pageWidth);
            }
        }
        return null;
    }

    public int badness(String[] text, int start, int end, int pageWidth){
        StringBuilder sb = new StringBuilder();
        while(start <= end){
            sb.append(text[start]);
            start++;
            sb.append("");
        }
        return -1;
    }

}
