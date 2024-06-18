import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int opcion = 0;

        while (opcion != 5 ){
            System.out.println ("Elija una consulta para realizar:" );
            System.out.println ("1. Top 10 canciones en un dia dado");
            System.out.println ("2. Top 5 canciones con mas apariciones en los top 50 en un dia dado");
            System.out.println ("3. Top 7 artistas que mas aparecen en los top 50 para un rango de fechas");
            System.out.println ("4. Cantidad de veces que aparece un artista en un top 50");
            System.out.println ("5. Cantidad de canciones con un tempo en un rango especifico para un rango de fechas");

            opcion= sc. nextInt();

            switch (opcion) {
                case 1:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 2:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 3:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 4:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 5:
                    //poner aca la funcion a la que quiero que vaya;
                    break;
            }
        }
        sc.close();

    }


}
