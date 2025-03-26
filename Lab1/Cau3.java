import java.lang.reflect.Array;
import java.util.*;

public class Cau3 {

    public static class Coordiante {
        int x;
        int y;
        // Euclidean distance
        public Coordiante(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }

        public double euculidDistance (Coordiante b) {
            return Math.sqrt(Math.pow(b.getX() - x, 2) + Math.pow(b.getY() - y, 2));
        }
    }

    public static int orinentation(Coordiante q,Coordiante p1, Coordiante p2) {
        int val = (p1.getX()-q.getX())*(p2.getY()-q.getY()) - (p1.getY()-q.getY())*(p2.getX()-q.getX());
        if(val == 0) return 0;
        else if (val > 0) return 1;
        else return 2;
    }

    public static void swap(Coordiante[] arr, int i, int j) {
        Coordiante temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();
        Coordiante[] arr = new Coordiante[n];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[i] = new Coordiante(x, y);
        }

        int minIndex = 0 ;
        for (int i = 0; i < n; i++) {
            if(arr[i].getY()<arr[minIndex].getY() || (arr[i].getY() == arr[minIndex].getY() && arr[i].getX()<arr[minIndex].getX())) {
                minIndex = i;
            }
        }

        // đưa phần tử mốc về đầu
        swap(arr,minIndex,0);

        final Coordiante base = arr[0];

        Arrays.sort(arr,1,n,new Comparator<Coordiante>() {
            public int compare(Coordiante o1, Coordiante o2) {
                int o = orinentation(base,o1,o2);
                if(o==0 ) {
                    // trả về đoạn dài hơn
                    double d1 = base.euculidDistance(o1);
                    double d2 = base.euculidDistance(o2);
                    return (int) (d1 - d2);
                }
                return (o==2) ?-1 :1;
            }
        });

        Stack<Coordiante> stack = new Stack<>();
        stack.push(base);
        if(n >= 2 )
            stack.push(arr[1]);
        if(n>= 3)
            stack.push(arr[2]);
        for (int i = 3; i < n; i++){
            // Kiểm tra và loại bỏ các điểm không thuộc đa giác lồi
            while(stack.size() >= 2) {
                Coordiante top = stack.pop();
                Coordiante nextToTop = stack.peek();
                int o = orinentation(nextToTop, top, arr[i]);
                if (o == 2) { // Nếu xoay ngược (counterclockwise) thì giữ lại điểm top
                    stack.push(top);
                    break;
                }
                // Nếu xoay theo chiều kim đồng hồ hoặc thẳng hàng, tiếp tục pop
            }
            stack.push(arr[i]);
        }

        List<Coordiante> hull = new ArrayList<>(stack);
        for(Coordiante p : hull){
            System.out.println(p.x + " " + p.y);
        }
        // sắp xêp các phần tử trong mảng
    }
}
