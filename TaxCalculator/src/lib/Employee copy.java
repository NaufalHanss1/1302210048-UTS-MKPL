package lib;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate dateJoined;
    private boolean isForeigner;
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.dateJoined = dateJoined;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new ArrayList<>();
        childIdNumbers = new ArrayList<>();
    }

    public void setMonthlySalary(Grade grade) {
        monthlySalary = grade.getSalary();
        if (isForeigner) {
            monthlySalary = (int) (monthlySalary * 1.5);
        }
    }

    public void setAnnualDeductible(int deductibleAmount) {
        this.annualDeductible = deductibleAmount;
    }

    public void setOtherMonthlyIncome(int additionalIncome) {
        this.otherMonthlyIncome = additionalIncome;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getMonthsWorkedInYear() {
        LocalDate currentDate = LocalDate.now();
        if (dateJoined.getYear() == currentDate.getYear()) {
            return currentDate.getMonthValue() - dateJoined.getMonthValue() + 1;
        }
        return 12;
    }

    public int getAnnualIncomeTax() {
        int monthsWorked = getMonthsWorkedInYear();
        boolean hasSpouse = !spouseIdNumber.isEmpty();
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, hasSpouse, childIdNumbers.size());
    }
}