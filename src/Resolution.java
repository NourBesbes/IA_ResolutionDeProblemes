
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Resolution {
     public ArrayList<Regle> Extract() throws FileNotFoundException
    {  String S; 
       ArrayList<String> regles = new ArrayList<String>();
       ArrayList<Regle> Regles =new ArrayList<Regle>();
       
        // Lire le fichier ligne par ligne
     Scanner scanner=new Scanner(new File("/home/nour/workspace/TP3_IA/BR.txt"));
		while (scanner.hasNextLine()) {
                       S = scanner.nextLine(); 
                       regles.add(S);
                      
		        }
               
                
   for(int i=0; i< regles.size(); i++)
   {   Regles.add(new Regle());
       Regles.get(i).id = i;
     
       String premisse= regles.get(i).split("Si")[1].split("alors")[0];

       String conclusion= regles.get(i).split("alors")[1];
       Regles.get(i).etat_initial= premisse.substring(premisse.indexOf("("), premisse.indexOf(")")+1);
       Regles.get(i).etat_final= conclusion;
       
       String[] conds = premisse.substring(premisse.indexOf(")")+1).split("et");

       for(int j=0; j< conds.length;j++ )
       {    if(!conds[j].equals("") && !conds[j].equals(" "))
       {   conds[j]=conds[j].trim();  
       
           Regles.get(i).conditions.add(conds[j]);
           
           
       }
    
       }
       
    }
   return Regles;
    }  
     public ArrayList<String> genereOperateursApplicables(Etat etat) throws FileNotFoundException
     {
         ArrayList<Regle> regles= Extract();
         Unification unification= new Unification();
         String S= "(" + Integer.toString(etat.x)+ "," +Integer.toString(etat.y)+ ")";
         
         ArrayList<String> S1 = new ArrayList<String>(); 
         S1.add(S);
         ArrayList<String> S2 = new ArrayList<String>(); 
        String ch = null ;
        ArrayList<String> res = new ArrayList<String>();
         for(int i=0; i< regles.size(); i++)
         { 
             S2.add(regles.get(i).etat_initial);
          
            if(regles.get(i).Verif(unification.unifier(S2,S1))) 
            {   
               int x= regles.get(i).Etat_final(etat).x;
               int y= regles.get(i).Etat_final(etat).y;
              //System.out.println("R"+((regles.get(i).id)+1) +" "+ unification.unifier(S1, S2)+ " ("+x + ","+ y+ ")" );
              ch =  "R"+((regles.get(i).id)+1) +" "+ unification.unifier(S1, S2)+ " ("+x + ","+ y+ ")" ;
              res.add(ch);
           
            }
         
            S2.clear();  
         } 
         return res ;
     }
     public ArrayList<Etat> Generer(ArrayList<String> regles)
     {
    	
         ArrayList<Etat> etats = new ArrayList<Etat>();
    	 
    	 
    	 
    	 for(int i=0; i< regles.size(); i++)
         { 
    		 
    		 String etat = regles.get(i).substring(regles.get(i).indexOf("(")+1, regles.get(i).indexOf(")"));
    		 String X=etat.split(",")[0];
    		 int xe=Integer.parseInt(X);
    		 String Y=etat.split(",")[1];
    		 int ye=Integer.parseInt(Y);
    		 Etat s= new Etat(xe,ye);
    		 etats.add(s);		 
    		 
         }
    	 return etats ;
     }
     
     public void AAlgorithm(Etat init,Etat but,ArrayList<String>L ,int choice) throws FileNotFoundException
     {  int x= init.x;
        int y= init.y;
        int g=0;
        int examine_index =0;
        Etat examine = init;
        ArrayList<Etat> ouverts = new ArrayList<Etat>();
        ouverts.add(init);
        ArrayList<Etat> fermes = new ArrayList<Etat>();
        boolean existe=false ;
      
        while(ouverts.size() != 0 && g<200)
      
        { 
            if( examine.x==but.x && examine.y==but.y )
        {
            System.out.println("But trouvé :( "+ but.x+ "," + but.y+ ")");
            L.add("But trouvé :( "+ but.x+ "," + but.y+ ")\n");
            return;
      
        }  
             
       g=g+1;
            ArrayList<Etat> generes = Generer(genereOperateursApplicables(examine));
            
            for(Etat gen : generes)
            {  
            	for(int i=0;i< generes.size();i++)
                {
                    if(generes.get(i).x == gen.x && generes.get(i).y == gen.y )
                        existe= true;
                }
            	existe= false ;
              
            	if(existe==false)
                 { ouverts.add(gen);
                      gen.g=g; } ;
            }
          
            fermes.add(examine);
            
              ouverts.remove(examine);
                       
          for(int i=0;i< ouverts.size();i++)
          { ouverts.get(i).g= g;       
            //heuristique
          if (choice==1)
          {  
          if (ouverts.get(i).x ==2 )
            {
            	ouverts.get(i).h=0 ;
            }
            else if ((ouverts.get(i).x+ouverts.get(i).y)<2)
            {
            	ouverts.get(i).h=7 ;
            }
            else if ((ouverts.get(i).y) >2)
            {
            	ouverts.get(i).h=3 ;
            }
            else {
            	ouverts.get(i).h=1 ;
            }
            
          }
          else if (choice==2)
          {
          	ouverts.get(i).h= Math.abs(ouverts.get(i).x-2); 
          }
        
            ouverts.get(i).f= g + ouverts.get(i).h;
             
          }
         examine= ouverts.get(0);
     
      int  l=0; boolean B=true;
             while( B && l< fermes.size() )
         {
             if((fermes.get(l).x == ouverts.get(0).x && (fermes.get(l).y == ouverts.get(0).y)))
             {    
              B=false;break;
             }
           
           l++;
             
         }  
           if(!B)
           {
        	   System.out.println("  Ferme :("+ ouverts.get(0).x + "," +  ouverts.get(0).y + ")");
        	   L.add("  Ferme :("+ ouverts.get(0).x + "," +  ouverts.get(0).y + ")\n");
           }
             else 
             {
                  System.out.println(" Ouvert :("+ ouverts.get(0).x + "," +  ouverts.get(0).y + ")");
                  L.add(" Ouvert :("+ ouverts.get(0).x + "," +  ouverts.get(0).y + ")\n");
             }
          
         for( int j=1; j< ouverts.size(); j++)
         {  
             boolean bool=true;int k=0;
       
             while(bool && k< fermes.size() )
         {
             if((fermes.get(k).x == ouverts.get(j).x && (fermes.get(k).y == ouverts.get(j).y)))
             {    
                 bool=false;break; }
            
           k++;
             
         }  
     
             if (bool)
             {
            	 System.out.println(" Ouvert :("+ ouverts.get(j).x + "," +  ouverts.get(j).y + ")\n");
            	 L.add(" Ouvert :("+ ouverts.get(j).x + "," +  ouverts.get(j).y + ")");
             }
             else 
             {
                  System.out.println("  Ferme :("+ ouverts.get(j).x + "," +  ouverts.get(j).y + ")");
                  L.add("  Ferme :("+ ouverts.get(j).x + "," +  ouverts.get(j).y + ")\n");
             }
               if((   ouverts.get(j).f < ouverts.get(j-1).f ) && bool) 
               {examine= ouverts.get(j); examine_index=j; }
         }
            System.out.println("Niveau :" + g + ", Etat : (" + examine.x + ","+ examine.y + ") Heuristique : " + examine.f );
        L.add("Niveau :" + g + ", Etat : (" + examine.x + ","+ examine.y + ") Heuristique : " + examine.f+"\n");
        
        }
         
     }
 
     }
		
     



                    
     