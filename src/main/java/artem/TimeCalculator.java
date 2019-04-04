package artem;

import java.util.Optional;

public interface TimeCalculator {
    Response calculate(TimeCalcArguments arguments);
}
