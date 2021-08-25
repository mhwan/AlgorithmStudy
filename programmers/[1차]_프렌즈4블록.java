package com.company.pack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class FriendsBlock {
    private char[][] array;
    private int m, n;
    private static final char EMPTY = '\0';
    
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        array = new char[m][n];

        for (int i = 0; i < board.length; i++) {
            array[i] = board[i].toCharArray();
        }
        return process();
    }

    private int process() {
        int answer = 0;
        while (true) {
            HashSet<Position> removeSet = findToRemoveBlock();
            if (removeSet.isEmpty()) {
                break;
            }

            answer += removeSet.size();
            removeBlock(removeSet);
            compactArray();
        }

        return answer;
    }

    private HashSet<Position> findToRemoveBlock() {
        HashSet<Position> removeSet = new HashSet<>();

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m - 1; i++) {
                char b1 = array[i][j];
                char b2 = array[i][j+1];
                char b3 = array[i+1][j];
                char b4 = array[i+1][j+1];

                if (b1 == EMPTY) {
                    continue;
                }

                if (b1 == b2 && b1 == b3 && b1 == b4) {
                    removeSet.add(new Position(i, j));
                    removeSet.add(new Position(i, j+1));
                    removeSet.add(new Position(i+1, j));
                    removeSet.add(new Position(i+1, j+1));
                }
            }
        }

        return removeSet;
    }

    private void removeBlock(HashSet<Position> removeList) {
        for (Position pos : removeList) {
            int i = pos.x;
            int j = pos.y;

            array[i][j] = EMPTY;
        }
    }

    private void compactArray() {
        for (int col = 0; col < n; col++) {
            compactColumnArray(col);
        }
    }

    private void compactColumnArray(int col) {
        int firstEmptyRow = m-1;

        while (firstEmptyRow >= 0 && array[firstEmptyRow][col] != EMPTY) {
            firstEmptyRow--;
        }

        ArrayList<Character> moveList = new ArrayList<>();
        int row = firstEmptyRow;
        while (row >= 0) {
            if (array[row][col] != EMPTY) {
                moveList.add(array[row][col]);
                array[row][col] = EMPTY;
            }
            row--;
        }

        for (Character ch : moveList) {
            array[firstEmptyRow--][col] = ch;
        }
    }

    private class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
