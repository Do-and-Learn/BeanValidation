import jakarta.validation.MessageInterpolator;
import jakarta.validation.Validation;

import java.util.Locale;
import java.util.Map;

public class CustomMessageInterpolator implements MessageInterpolator {
    private final Map<String, String> message = Map.of("{jakarta.validation.constraints.NotNull.message}", "請提供資料");
    private MessageInterpolator defaultMessageInterpolator = Validation.byDefaultProvider().configure().getDefaultMessageInterpolator();

    @Override
    public String interpolate(String s, Context context) {
        if (message.containsKey(s))
            return message.get(s);
        return defaultMessageInterpolator.interpolate(s, context);
    }

    @Override
    public String interpolate(String s, Context context, Locale locale) {
        if (message.containsKey(s))
            return message.get(s);
        return defaultMessageInterpolator.interpolate(s, context, locale);
    }
}
