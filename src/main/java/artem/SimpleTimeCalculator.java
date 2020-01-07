package artem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimpleTimeCalculator extends Description implements TimeCalculator {

    @Override
    public Response calculate(TimeCalcArguments arguments) throws NotTimeException {
        try {
            uranus(arguments);
            this.clear();

            int result = getMinutes(arguments.getFinish()) - getMinutes(arguments.getStart());
            this.doMainPhase(arguments.getStart(), arguments.getFinish(), getHours(result));

            if (arguments.getInputs() != null && arguments.getInputs().length > 0) {
                for (String r : arguments.getInputs()) result = qwe(result, r);
            }

            return Response.create(getHours(result), this.getResult());

        } catch (RuntimeException e) {
            return Response.create(e.getMessage(), "check out description");
        }
    }

    private void uranus(TimeCalcArguments calcArguments) {
        String[] T = Arrays.stream(calcArguments.getInputs())
                .map(this::saturn)
                .toArray(String[]::new);

        String start = saturn(calcArguments.getStart());
        String finish = saturn(calcArguments.getFinish());

        if (getMinutes(start) > getMinutes(finish))
            throw new ArithmeticException("incorrect input: \'Finish\' must be bigger then \'Start\'");

        calcArguments.setStart(start);
        calcArguments.setFinish(finish);
        calcArguments.setInputs(T);
    }

    public String saturn(String input) {
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
        String result, h = a > 1 ? " hours " : " hour ";

        if (a == 0) result = b + " min ";
        else if (b == 0) result = a + h;
        else result = a + h + b + " min ";

        return result;
    }

    private int qwe(int input, String argument) {
        int arg, result = 0;

        if (argument.contains(":")) {
            String[] t = argument.substring(2).split(" ");
            arg = getMinutes(t[1]) - getMinutes(t[0]);
            this.doMainPhase(t[0], t[1], getHours(arg));
        } else arg = getMinutes(argument);

        if (argument.charAt(0) == '+') result = input + arg;
        else if (argument.charAt(0) == '-') result = input - arg;

        this.doSecondaryPhase(getHours(input), argument.charAt(0) + " " + getHours(arg), getHours(result));

        return result;
    }
}