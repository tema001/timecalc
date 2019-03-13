package artem;

import java.util.Arrays;

public class TimeCalcArguments {
    private String start;
    private String finish;
    private String[] inputs;

    static String time_pattern = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    static String time_amount_pattern = "^[+|-] ((1[01]|[0-9])\\.)?[0-5][0-9]";
    static String time_interval_pattern = "^[+|-] " + time_pattern + " " + time_pattern;

    public TimeCalcArguments(String start, String finish, String[] inputs) {
        this.start = start;
        this.finish = finish;
        this.inputs = inputs;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    public String[] getInputs() {
        return inputs;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setInputs(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public String toString() {
        return "TimeCalcArguments{" +
                "start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                ", inputs=" + Arrays.toString(inputs) +
                '}';
    }
}
