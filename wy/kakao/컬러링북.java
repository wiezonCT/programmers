package wy.kakao;

import java.util.Queue;
import java.util.*;

public class 컬러링북 {
    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = new int[6][4];
        boolean[][] visited = new boolean[m][n];
        
        int areaCnt = 0;
        int sameColorCnt = 0;

        for(int i = 0; i< m; i++){
            for (int j = 0; j< n; j++){
                if(picture[i][j] != 0 && !visited[i][j]){
                    areaCnt++;
                    sameColorCnt = Math.max(sameColorCnt, bfs(i, j, picture, visited, m,s));

                }
            }
        }

        System.out.println(new int[]{areaCnt, sameColorCnt});
    }
    

    public static int bfs(int x, int y, int[][]picture, boolean[][] visited, int m, int n){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;
        int color = picture[x][y];
        int size = 1;

        while(!queue.isEmpty()){
            int []pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for(int d = 0; d< 4; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                
                if(nx >= 0 && ny >=0 && nx < m && ny < n){
                    if(!visited[nx][ny] && picture[nx][ny] == color){
                        queue.offer(new int[]{nx,ny});
                        visited[nx][ny] = true;
                        size++;
                    }
                }
            }
        }

        return size;

    }    

}
