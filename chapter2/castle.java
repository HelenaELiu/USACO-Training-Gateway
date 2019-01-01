/*
ID: helena.6
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.*;

public class castle {
    static int      M, N;
    static int      maxRoomSize = 0, roomCount = 0;
    static int[][]  modules, rooms;
    static int[][]  dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static String[] dirstr = {"N", "E", "S", "W"};
    static int[]    roomSizes;
    static List<Wall> removedWalls = new ArrayList<>();

    public static void main(String[] args) throws java.io.IOException {
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());        

        modules = new int[N][M];
        rooms = new int[N][M];
        roomSizes = new int[M * N + 1];   // room id start from 1, max M*N rooms

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(f.readLine());
            for (int j = 0; j < M; j++) {
                modules[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {//loop through castle
            for (int j = 0; j < M; j++) {
                if (rooms[i][j] == 0) {
                    roomCount++;
                    rooms[i][j] = roomCount;
                    dfs(i, j, roomCount);
                }
                
                roomSizes[rooms[i][j]]++;
            }
        }
        
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                combinedRoomByRemovingWall(i, j, rooms[i][j]);
        
        Arrays.sort(roomSizes);
        
        Wall[] walls = new Wall[removedWalls.size()];
        walls = removedWalls.toArray(walls);
        Arrays.sort(walls);
        Wall optimalWall = walls[walls.length - 1];
        
        out.println(roomCount);
        out.println(roomSizes[roomSizes.length - 1]);   // last one is the largest room
        out.println(optimalWall.combinedRoomSize);
        out.println(optimalWall.x + " " + optimalWall.y + " " + dirstr[optimalWall.dir]);
        
        out.close();
    }

    public static void dfs(int i, int j, int roomNum) {
        int tmpx, tmpy;
        
        for (int x = 0; x < dirs.length; x++) {
            tmpx = i + dirs[x][0];
            tmpy = j + dirs[x][1];
            
            if (withinBoundary(tmpx, tmpy) && !hasWall(modules[i][j], x) && rooms[tmpx][tmpy] == 0) {
                    rooms[tmpx][tmpy] = roomNum;
                    dfs(tmpx, tmpy, roomNum);
            }
        }
    }
    
    public static void combinedRoomByRemovingWall(int i, int j, int roomNum) {
        int tmpx, tmpy;
        
        for (int x = 0; x < dirs.length; x++) {
            tmpx = i + dirs[x][0];
            tmpy = j + dirs[x][1];
            
            if (withinBoundary(tmpx, tmpy) && hasWall(modules[i][j], x) && rooms[tmpx][tmpy] != rooms[i][j] && (roomSizes[rooms[tmpx][tmpy]] + roomSizes[roomNum] >= maxRoomSize)) {
                removedWalls.add(new Wall(i + 1, j + 1, roomSizes[rooms[tmpx][tmpy]] + roomSizes[roomNum], x));
                maxRoomSize = roomSizes[rooms[tmpx][tmpy]] + roomSizes[roomNum];
            }
        }
    }

    public static boolean hasWall(int module, int dir) {
        // translate index of {{-1, 0}, {0, 1}, {1, 0}, {0, -1}} to N=2, E=4, S=8, W=1
        int[] transDir = {2, 4, 8, 1};  
        
        return (module & transDir[dir]) == transDir[dir];
    }
    
    public static boolean withinBoundary(int x, int y) {
        return ((x < N && x >= 0) && (y < M && y >= 0));
    }
}

class Wall implements Comparable<Wall> {
    int x, y, combinedRoomSize, dir;

    public Wall(int x, int y, int combinedRoomSize, int dir) {
        this.x = x;
        this.y = y;
        this.combinedRoomSize = combinedRoomSize;
        this.dir = dir;
    }

    @Override
    public int compareTo(Wall other) { //sorts walls by room size, west, south, and direction ('N'>'E'), i., e. (0 > 1)
        if (this.combinedRoomSize == other.combinedRoomSize) {
            if (this.y == other.y) {
                if (this.x == other.x)
                    return other.dir - this.dir;
                else 
                    return this.x - other.x;
            } else 
                return other.y - this.y;
        } else
            return this.combinedRoomSize - other.combinedRoomSize;
    }
}