import java.util.Objects;

public class TicTacToeTg {
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_0 = 200;

    public int[][] field;

    private boolean isXturn;
    public boolean getIsXturn() { return isXturn; }
    public void setIsXturn(boolean xturn) { isXturn = xturn; }

    public TicTacToeTg() {
        field = new int[3][3];
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD_EMPTY;
            }
        }
        isXturn = true;
    }


    public String process() {

        String possible_result = "";

        possible_result = checkField();
        if (!Objects.equals(possible_result, "continue")) {
            return possible_result;
        }
        botStep();
        isXturn = !isXturn;

        possible_result = checkField();
        if (!Objects.equals(possible_result, "continue")) {
            return possible_result;
        }

        return possible_result;

    }


    String checkField() {
        int res = checkState();
        if (res != 0) {
            if (res == FIELD_0 * 3) {
                initGame();
                return "O win";
            } else if (res == FIELD_X * 3) {
                initGame();
                return "X win";
            } else {
                initGame();
                return "draw!";
            }

        }
        return "continue";
    }

    int checkState() {
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag1 += field[i][i];
            diag2 += field[i][2 - i];
        }
        if(diag1 == FIELD_0 * 3 || diag1 == FIELD_X * 3) {
            return diag1;
        }
        if(diag2 == FIELD_0 * 3 || diag2 == FIELD_X * 3) {
            return diag2;
        }

        int check_i, check_j;
        boolean hasEmpty = false;
        for (int i = 0; i < 3; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            if (check_i == FIELD_0 * 3 || check_i == FIELD_X * 3) {
                return check_i;
            }
            if (check_j == FIELD_0 * 3 || check_j == FIELD_X * 3) {
                return check_j;
            }
        }
        return hasEmpty ? 0 : -1;
    }

    void botStep() {
        int[] lines = {0, 0,
                0, 0, 0,
                0, 0, 0};
        //int[] arr = {0, 10, 20, 200, 210, 400};
        int way = 0;

        for (int i = 0; i < 3; i++) {
            lines[0] += field[i][i];
            lines[1] += field[i][2 - i];
        }
        for (int i = 0; i < 3; i++) {
            lines[2] += field[i][0];
            lines[3] += field[i][1];
            lines[4] += field[i][2];
            lines[5] += field[0][i];
            lines[6] += field[1][i];
            lines[7] += field[2][i];
        }

        if (way == 0) {
            for (int i = 0; i < 8; i++) {
                if (lines[i] == 400) {
                    way = 1;
                    way1_2_3(i);
                    break;
                }
            }
        }
        if (way == 0) {
            for (int i = 0; i < 8; i++) {
                if (lines[i] == 20) {
                    way = 2;
                    way1_2_3(i);
                    break;
                }
            }
        }
        if (way == 0) {
            for (int i = 0; i < 8; i++) {
                if (lines[i] == 200 || lines[i] == 10 || lines[i] == 210) {
                    way = 3;
                    way1_2_3(i);
                    break;
                }
            }
        }


    }

    // to win or not fail or do sth
    void way1_2_3 (int i) {
        if (i == 0) {
            if (field[0][0] == FIELD_EMPTY) {
                field[0][0] = FIELD_0;
            } else if (field[1][1] == FIELD_EMPTY) {
                field[1][1] = FIELD_0;
            } else {
                field[2][2] = FIELD_0;
            }
        }
        else if (i == 1) {
            if (field[2][0] == FIELD_EMPTY) {
                field[2][0] = FIELD_0;
            } else if (field[1][1] == FIELD_EMPTY) {
                field[1][1] = FIELD_0;
            } else {
                field[0][2] = FIELD_0;
            }
        }
        else if (i < 5) {
            for (int j = 0; j < 3; j++) {
                if (field[j][i-2] == FIELD_EMPTY) {
                    field[j][i-2] = FIELD_0;
                    break;
                }
            }
        }
        else {
            for (int j = 0; j < 3; j++) {
                if (field[i-5][j] == FIELD_EMPTY) {
                    field[i-5][j] = FIELD_0;
                    break;
                }
            }
        }
    }

}
