package com.company.pack;

public class Main {
    private static final char SEAT = 'P';
    private static final char PARTITION = 'X';
    private static final int SIZE = 5;
    private static char[][] placeArray = new char[SIZE][SIZE];

    public static void main(String[] args) {
        new Main().solution(new String[][]{{"OOPOO", "OPOOO", "OOOOO", "OOOOO", "OOOOO"}});
    }

    public int[] solution(String[][] places) {
        int [] answer = new int[SIZE];

        for (int i = 0; i < places.length; i++){
            String[] place = places[i];
            for (int j = 0; j < SIZE; j++) {
                placeArray[j] = place[j].toCharArray();
            }

            answer[i] = isSocietyDistance()? 1 : 0;
        }

        return answer;
    }

    public boolean isSocietyDistance() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isPerson(i, j)) {
                    if (!isHorizontalSocietyDistance(i, j)) return false;
                    if (!isVerticalSocietyDistance(i, j)) return false;
                    if (!isCrossSocietyDistance(i, j)) return false;
                    if (!isCrossSocietyDistance2(i, j)) return false;
                }
            }
        }
        return true;
    }

    public boolean isHorizontalSocietyDistance(int x, int y) {
        for (int i = 2; i >= 1; i--) {
            int newX = x;
            int newY = y + i;

            if (isInBoundary(newX, newY)) {
                if (i == 2 && isPerson(newX, newY)){
                    //거리가 2일때는 사이에 파티션
                    if (!isPartition(newX, newY - 1)) {
                        return false;
                    }
                }

                if (i == 1 && isPerson(newX, newY)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isVerticalSocietyDistance(int x, int y) {
        for (int i = 2; i >= 1; i--) {
            int newX = x + i;
            int newY = y;

            if (isInBoundary(newX, newY)) {
                if (i == 2 && isPerson(newX, newY)){
                    //거리가 2일때는 사이에 파티션
                    if (!isPartition(newX - 1, newY)) {
                        return false;
                    }
                }

                if (i == 1 && isPerson(newX, newY)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isCrossSocietyDistance(int x, int y) {
        int newX = x + 1;
        int newY = y + 1;

        if (isInBoundary(newX, newY)) {
            if (isPerson(newX, newY)) {
                if (isPartition(newX, newY - 1) && isPartition(newX - 1, newY)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCrossSocietyDistance2(int x, int y) {
        int newX = x + 1;
        int newY = y - 1;

        if (isInBoundary(newX, newY)) {
            if (isPerson(newX, newY)) {
                if (isPartition(newX, y) && isPartition(x, newY)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isInBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < SIZE && y < SIZE;
    }

    private boolean isPerson(int x, int y) {
        return placeArray[x][y] == SEAT;
    }

    private boolean isPartition(int x, int y) {
        return placeArray[x][y] == PARTITION;
    }
}
