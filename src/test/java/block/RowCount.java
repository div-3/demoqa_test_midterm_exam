package block;

public enum RowCount {
    ROWS_5("5"),
    ROWS_10("10"),
    ROWS_20("20"),
    ROWS_25("25"),
    ROWS_50("50"),
    ROWS_100("100");

    private String code;

    RowCount(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
