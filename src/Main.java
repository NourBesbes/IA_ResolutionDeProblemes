
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
      Resolution resolution = new Resolution();
       
       System.out.println("Choisir l'etat initiale");
      System.out.println("Saisir x");
      Scanner sc = new Scanner(System.in);
      int x=sc.nextInt();
      Scanner sc2 = new Scanner(System.in);
      System.out.println("Saisir y");
      int y=sc2.nextInt();
      Etat EtatInitial=new Etat(x,y);
      //BUT
      System.out.println("Choisir le but");
      System.out.println("Saisir x");
      Scanner scb = new Scanner(System.in);
      int xb=scb.nextInt();
      Scanner sc2b = new Scanner(System.in);
      System.out.println("Saisir y");
      int yb=sc2.nextInt();
      Etat but=new Etat(xb,yb);
      ArrayList<String> ch =resolution.genereOperateursApplicables(EtatInitial);
      System.out.println("====Resolution====\n"+ch);
      ArrayList<String> L =new ArrayList<String>();
      Scanner choix = new Scanner(System.in);
      System.out.println("Choisir l’heuristique 1 ou 2");
      System.out.println("1/if ?x=2 alors h=0, si ?x+?y <2 alors h=7, si ?y>2 alors h=3 sinon h=1");
      System.out.println("2/h = abs(?x - 2)");
      int c=choix.nextInt();
     resolution.AAlgorithm(EtatInitial, but,L,c);
     
    }
    
}