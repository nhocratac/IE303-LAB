import java.util.Random;
import java.util.Scanner;

public class Cau2 {
    public static boolean isInsideCircel (double x,double y, double R){
        return (x*x + y*y <= R*R);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Nhập bán kính R");
        double R = sc.nextDouble();
        int NumpointInside = 0;

        // lấy random các điểm trong khoảng từ -R đến R
        for (int i =0 ; i <100000000; i ++) {
            double x = random.nextDouble()*2*R -R; // [-R,R]
            double y = random.nextDouble()*2*R -R; // [-R,R]
            if(isInsideCircel (x,y,R)){
                NumpointInside++;
            }
        }
        double circumference = ((double) NumpointInside / 100000000 )* 8*R;
        System.out.println("chu vi hình tròn là "+circumference);

    }
}
