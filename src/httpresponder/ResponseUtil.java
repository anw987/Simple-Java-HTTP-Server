/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpresponder;
/**
 *
 * @author alvin.wijaya
 */


import java.util.Random;

public class ResponseUtil {

    private static final Random random = new Random();

    public static String generateResponseCode() {
        int chance = random.nextInt(100);
        if (chance < AppConfig.SUCCESS_RATE) {
            return "00";
        } else {
            // simple failure codes (ISO8583-like)
            String[] errors = {"05", "12", "91"};
            return errors[random.nextInt(errors.length)];
        }
    }
}
