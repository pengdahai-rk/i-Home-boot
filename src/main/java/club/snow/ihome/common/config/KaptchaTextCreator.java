package club.snow.ihome.common.config;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;

import java.util.Random;

public class KaptchaTextCreator extends DefaultTextCreator {

    private static final String[] NUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

    @Override
    public String getText() {
        int result;
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder strBuilder = new StringBuilder();
        int randomOperands = random.nextInt(3);
        if (randomOperands == 0) {
            result = x * y;
            strBuilder.append(NUMBERS[x]);
            strBuilder.append("*");
            strBuilder.append(NUMBERS[y]);
        } else if (randomOperands == 1) {
            if ((x != 0) && y % x == 0) {
                result = y / x;
                strBuilder.append(NUMBERS[y]);
                strBuilder.append("/");
                strBuilder.append(NUMBERS[x]);
            } else {
                result = x + y;
                strBuilder.append(NUMBERS[x]);
                strBuilder.append("+");
                strBuilder.append(NUMBERS[y]);
            }
        } else {
            if (x >= y) {
                result = x - y;
                strBuilder.append(NUMBERS[x]);
                strBuilder.append("-");
                strBuilder.append(NUMBERS[y]);
            } else {
                result = y - x;
                strBuilder.append(NUMBERS[y]);
                strBuilder.append("-");
                strBuilder.append(NUMBERS[x]);
            }
        }
        strBuilder.append("=?@").append(result);
        return strBuilder.toString();
    }
}
