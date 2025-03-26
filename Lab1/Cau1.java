import java.util.Scanner;

public class Cau1 {

    // calc sqrt(R^2 - x^2) = y (với y >0)
    public static double CalcY(double R, double x) {
        return Math.sqrt(R*R - x*x);
    }

    public static double CalcIntegral(double a, double b, int step, double R) {
        double res = 0.0;
        double distancePerStep = (b - a) / step; // Khoảng cách giữa các bước

        // Tính giá trị tại hai đầu mút
        res += 0.5 * (CalcY(R, a) + CalcY(R, b));

        // Tính tổng các giá trị tại các điểm bên trong
        for (int i = 1; i < step; i++) {
            double x = a + i * distancePerStep;
            res += CalcY(R, x)*distancePerStep;
        }

        // Nhân với khoảng cách để có kết quả cuối cùng
        res *= distancePerStep;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter R: ");
        double R  = sc.nextDouble();
        System.out.println("Enter số step: ");
        int step = sc.nextInt();
        System.out.println("Diện tích của hình tròn là ");
        System.out.println(CalcIntegral(-R,R,step,R));
    }
}
