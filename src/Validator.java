public class Validator {

    public void validate(String strToCheck, int l) throws InvalidInputException {
        if (strToCheck.length() > l || strToCheck.length() <= 0) {
            throw new InvalidInputException("Invalid input. ");
        }
    }

    public void validate(String strToCheck, String a) throws InvalidInputException {
        if (strToCheck.equals(a)) {
            return;
        }
        throw new InvalidInputException("Invalid input. ");
    }

    public void validate(String strToCheck, String a, String b) throws InvalidInputException {
        if (strToCheck.equals(a) || strToCheck.equals(b)) {
            return;
        }
        throw new InvalidInputException("Invalid input. ");
    }

    public void validate(String strToCheck, String a, String b, String c) throws InvalidInputException {
        if (strToCheck.equals(a) || strToCheck.equals(b) || strToCheck.equals(c)) {
            return;
        }
        throw new InvalidInputException("Invalid input. ");
    }
}
