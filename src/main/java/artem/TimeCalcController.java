package artem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TimeCalcController {

    private TimeCalculator timeCalculator;

    @Autowired
    public TimeCalcController(TimeCalculator timeCalculator) {
        this.timeCalculator = timeCalculator;
    }

    @RequestMapping("/timecalc")
    public ModelAndView index() {
        return new ModelAndView("/test");
    }

    @PostMapping(value = "/timecalc/result",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response getResult(@RequestBody TimeCalcArguments calcArguments) {
        System.out.println(calcArguments.toString() + " " + calcArguments.getInputs().length);

        return timeCalculator.calculate(calcArguments);
    }
}
