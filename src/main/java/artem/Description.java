package artem;

public class Description {
    private int x = 1;
    private StringBuffer result = new StringBuffer();

    void doMainPhase(String start, String finish, String res) {
        this.result
                .append(x++).append(") ")
                .append("difference between ")
                .append(start).append(" and ")
                .append(finish)
                .append(" is ")
                .append("<b>").append(res).append("</b>")
                .append(System.lineSeparator());
    }

    void doSecondaryPhase(String old_res, String arg, String res) {
        this.result
                .append(x++).append(") ")
                .append(old_res).append(" ")
                .append(arg).append("= ")
                .append("<b>").append(res).append("</b>")
                .append(System.lineSeparator());
    }

    void clear() {
        x = 1;
        result.delete(0, result.capacity());
    }

    String getResult() {
        return result.toString();
    }
}