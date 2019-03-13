package artem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/timecalc/result")
    public String getResult(@RequestBody TimeCalcArguments calcArguments) {
        System.out.println(calcArguments.toString());
        try {
            return timeCalculator.calculate(calcArguments);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
