package artem;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleTimeCalculator implements TimeCalculator {

    @Override
    public String calculate(TimeCalcArguments arguments) throws NotTimeException {
        uranus(arguments);
        int result = getMinutes(arguments.getFinish()) - getMinutes(arguments.getStart());

        if (arguments.getInputs() != null && arguments.getInputs().length > 0) {
            for (String r : arguments.getInputs())
                result = qwe(result, r);
        }

        return getHours(result);
    }

    private void uranus(TimeCalcArguments calcArguments) throws NotTimeException {
        String[] T = Arrays.stream(calcArguments.getInputs())
                .map(this::saturn)
                .toArray(String[]::new);

        String start = saturn(calcArguments.getStart());
        String finish = saturn(saturn(calcArguments.getFinish()));

        if (getMinutes(start) > getMinutes(finish))
            throw new ArithmeticException("incorrect input: \'Finish\' must be bigger then \'Start\'");

        calcArguments.setStart(start);
        calcArguments.setFinish(finish);
        calcArguments.setInputs(T);
    }

    public String saturn(String input) throws NotTimeException {
        String temp = input;

        if (input.contains(";")) temp = input.replaceAll(";", ":");
        else if (input.contains(",")) temp = input.replaceAll(",", ".");

        if (temp.contains("+") || temp.contains("-")) {
            if (temp.contains(":")) dumbCheck(temp, TimeCalcArguments.time_interval_pattern);
            else dumbCheck(temp, TimeCalcArguments.time_amount_pattern);
        } else dumbCheck(temp, TimeCalcArguments.time_pattern);

        return temp;
    }

    private void dumbCheck(String input, String pattern) {
        if (!input.matches(pattern)) throw new NotTimeException(input);
    }

    private int getMinutes(int time) {
        return (time / 100) * 60 + time % 100;
    }

    public int getMinutes(String time) {
        return getMinutes(Integer.parseInt(time.replaceAll("\\D", "")));
    }

    private String getHours(int time) {
        int a = time / 60, b = time % 60;
        String result;

        if (a == 0) result = b + " minutes";
        else if (b == 0) result = a + " hours ";
        else result = a + " hours " + b + " minutes";

        return result;
    }

    private int qwe(int input, String argument) {
        int arg, result = 0;

        if (argument.contains(":")) {
            String[] t = argument.substring(2).split(" ");
            arg = getMinutes(t[1]) - getMinutes(t[0]);
        } else arg = getMinutes(argument);

        if (argument.charAt(0) == '+') result = input + arg;
        else if (argument.charAt(0) == '-') result = input - arg;

        return result;
    }
}