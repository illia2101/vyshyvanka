package ua.university.model;

public class LetterPattern {

    public static int[][] getLetter(char letter) {

        return switch (letter) {

            case 'І' -> new int[][]{
                    {1},
                    {1},
                    {1},
                    {1},
                    {1}
            };

            case 'Л' -> new int[][]{
                    {1,0,1},
                    {1,0,1},
                    {1,0,1},
                    {1,0,1},
                    {1,1,1}
            };

            case 'Я' -> new int[][]{
                    {1,1,0},
                    {1,0,1},
                    {1,1,0},
                    {1,0,1},
                    {1,0,1}
            };

            default -> new int[][]{{0}};
        };
    }
}
