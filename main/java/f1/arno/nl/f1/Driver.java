package f1.arno.nl.f1;

import java.io.Serializable;

public class Driver implements Serializable{

    private final String givenName;
    private final String familyName;
    private final String nationality;
    private final String code;
    private final String permanentNumber;
    private final String driverId;

    public Driver(String givenName, String familyName, String nationality, String code, String permanentNumber, String driverId){
        this.givenName = givenName;
        this.familyName = familyName;
        this.nationality = nationality;
        this.code = code;
        this.permanentNumber = permanentNumber;
        this.driverId = driverId;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCode() {
        return code;
    }

    public String getPermanentNumber() {
        return permanentNumber;
    }

    public String getDriverId() {
        return driverId;
    }

}
