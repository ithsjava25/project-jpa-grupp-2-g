package backend.services;

public class CustomerService {

    public boolean validateName(String name){
        if(name == null || name.isBlank())
            return false;

        return name.matches("^[a-zA-ZåäöÅÄÖ]+$");
    }

    public boolean validatePhoneNumber(String number){
        if(number.length() != 10)
            return false;

        return number.matches("^0[0-9]{9}$");
    }

    public boolean validateEmail(String email){
        if(email == null || email.isBlank())
            return false;

        return email.matches("^[a-zA-ZåäöÅÄÖ]{1,20}@[a-zåäöÅÄÖ]{1,10}(\\.se|\\.com)$");
    }

}
