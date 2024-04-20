package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	private static final int TAX_RATE = 5;
    private static final int TAX_FREE_BASE = 54000000;
    private static final int TAX_FREE_MARRIED_BONUS = 4500000;
    private static final int TAX_FREE_CHILD_BONUS = 1500000;
	private static final int MAX_CHILDREN_FOR_TAX_FREE = 3;
    private static final int MONTHS_IN_YEAR = 12;
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        validateMonthsWorked(monthsWorked);

        int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome, monthsWorked, deductible, isMarried, numberOfChildren);
        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0); // Tax cannot be negative
    }

	private static void validateMonthsWorked(int monthsWorked) {
        if (monthsWorked > MONTHS_IN_YEAR) {
            throw new IllegalArgumentException("Number of months worked cannot exceed 12 in a year");
        }
    }

	private static int calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        int totalIncome = (monthlySalary + otherMonthlyIncome) * monthsWorked;
        int taxFreeAllowance = calculateTaxFreeAllowance(isMarried, numberOfChildren);
        return totalIncome - deductible - taxFreeAllowance;
    }

    private static int calculateTaxFreeAllowance(boolean isMarried, int numberOfChildren) {
        int taxFreeAllowance = TAX_FREE_BASE;
        if (isMarried) {
            taxFreeAllowance += TAX_FREE_MARRIED_BONUS;
        }
        taxFreeAllowance += Math.min(numberOfChildren, MAX_CHILDREN_FOR_TAX_FREE) * TAX_FREE_CHILD_BONUS;
        return taxFreeAllowance;
    }


// 	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
// 		int tax = 0;
		
// 		if (numberOfMonthWorking > 12) {
// 			System.err.println("More than 12 month working per year");
// 		}
		
// 		if (numberOfChildren > 3) {
// 			numberOfChildren = 3;
// 		}
		
// 		if (isMarried) {
// 			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
// 		}else {
// 			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
// 		}
		
// 		if (tax < 0) {
// 			return 0;
// 		}else {
// 			return tax;
// 		}
			 
// 	}
	
}
