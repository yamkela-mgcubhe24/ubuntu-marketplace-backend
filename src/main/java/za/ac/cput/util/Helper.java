package za.ac.cput.util;
import org.apache.commons.validator.routines.EmailValidator;

public class Helper {

    public static boolean isEmptyOrNull(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNumNeg(float num) {
        if (num < 0) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static <T> boolean isValidType(T t) {
        if (t == null) {
            return true;
        }
        return false;
    }

    public static boolean isValidRating(Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            return false;
        }
        return true;
    }

    public static boolean isNumNeg(Integer num) {
        if (num < 0) {
            return true;
        }
        return false;
    }
}
