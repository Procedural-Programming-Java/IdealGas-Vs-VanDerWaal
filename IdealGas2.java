/*
 * @author Yaaqov Shkifati
 * @since 10/24/19
 * @version 1.0
  Description : The ideal gas was developed during 17th and 18th centry by a 
  group of scientists. They were inspired and had a desire to understand nature 
  and a quest to make balloons in which they could fly and also the get the
  relationships  between the macroscopic physical properties of gases, that is, 
  pressure, volume,temperature, and amount of gas. Although, the eqution 
  (P*V=n*RT) applies only as an approximation to a real gas that behaves 
  sufficiently like an ideal gas. There are in fact many different forms of the 
  equation of state. Since the ideal gas law neglects both molecular size and 
  inter molecular attractions, it is most accurate for monatomic gases at high 
  temperatures and low pressures. The neglect of molecular size becomes less 
  important for lower densities, for larger volumes at lower pressures, because 
  the average distance between adjacent molecules becomes much larger than the 
  molecular size. The relative importance of intermolecular attractions 
  diminishes with increasing thermal kinetic energy, with increasing 
  temperatures. More detailed equations of state, such as the van der Waals 
  equation, account for deviations from ideality caused by molecular size and 
  intermolecular forces.
  This program will conduct an experimeint between the ideal gas equation and
  the van der Waals equation. To do so will take 5 different gas and compare 
  the pressure as the temperature increase from 100 to 500 degrees kalvin.
  Will then take the differance between the ideal gas and van der waals and 
  divide by van der waals and mlutiply by 100 precent to get the precent error.
  As temperatur rises the precent error would dcrease due to the as that 
  intermolecular attractions.
 */
package idealgas2;

import java.io.File;
import java.util.Scanner;
import java.io.PrintStream;

public class IdealGas2 {

    public static PrintStream gas;

    public static void main(String[] args) throws Exception {

        double a, b, idealPressure, vanPressure, moles, tem, volume, error;

        String gasName;
        moles = 1.0;
        volume = 1.0;

        Scanner sc = new Scanner(new File("particles.txt"));

        gas = new PrintStream("VanDerWaalsConstants2.txt");

        while (sc.hasNext()) {

            gasName = sc.next();

            System.out.println(gasName);
            gas.println(gasName);

            a = sc.nextDouble();
            b = sc.nextDouble();

            System.out.printf("a = %.4f and b = %.4f%n%n", a, b);

            gas.printf("a = %.4f and b = %.4f%n%n", a, b);

            System.out.printf(" Temperature\tIdealPressure  "
                    + "\tVanPressure\tPrecentError %n");

            gas.printf("Temperature\tIdealPressure  "
                    + "\tVanPressure\tPrecentError %n");

            for (tem = 100; tem <= 500; tem = tem + 25) {

                idealPressure = idealPresure(moles, tem, volume);

                vanPressure = VanDerWaalsPresure(a, b, moles, tem, volume);

                error = precentError(idealPressure, vanPressure);

                System.out.printf("\n %.2f\t\t %.3f\t\t%.3f\t\t%.3f%n%n",
                        tem, idealPressure, vanPressure, error);

                gas.printf("%.2f\t\t%.3f\t\t%.3f\t\t%.3f%n%n",
                        tem, idealPressure, vanPressure, error);

            }

        }
    }

    public static double idealPresure(double moles, double tem, double volume) {

        double idealPressure = (moles * 0.0821 * tem) / (volume);

        return idealPressure;

    }

    public static double VanDerWaalsPresure(double a, double b, double moles,
            double tem, double volume) {

        double vanPressure = (moles * 0.0821 * tem) / (volume - moles * b)
                - (a * Math.pow(moles, 2)) / (Math.pow(volume, 2));

        return vanPressure;
    }

    public static double precentError(double idealPressure, double vanPressure){

        double error = (idealPressure - vanPressure) / (vanPressure) * 100;

        return error;

    }
}
