
import java.util.ArrayList;
public class Regle {
    public boolean active = true;
    public int id; 
    public String etat_initial ;
    public String etat_final;
    public ArrayList<String> conditions= new ArrayList<String>() ;
  
    int Calculer(ArrayList<Integer> Operande,ArrayList<String> Operateur)
    {
    	int resultat=0;
        if(Operateur.get(0).equals("+"))    
        	resultat= Operande.get(0)+ Operande.get(1);
        if (Operateur.get(0).equals("-"))  
        	resultat= Operande.get(0) - Operande.get(1);
        
        Operande.clear();
        Operateur.clear();
       return resultat;
    } 
    public boolean Verif(String S )//S est le resultat de l'unification
    {   int x=0 ;
    	int y=0;
        
        if (S == "") return true;
        if (S.contains("echec")) return false; //si l'unification retourne echec 
        String[] Unif = S.split(" ");
       
        if (S.contains("?x") )
        {
        	String X= Unif[0].split("/")[1];
        	 x= Integer.parseInt(X);
          
         
         if (S.contains("?y") )
        {
        	 String Y= Unif[1].split("/")[1];
        	  y= Integer.parseInt(Y);
        
        }
        }
        else if (S.contains("?y") )
        { 
            String Y= Unif[1].split("/")[1];
             y= Integer.parseInt(Y);
            
        }   
       
       
         
     
      
      
      
      
      int i=0;
      boolean bool= true;
      while(bool &&  i< conditions.size())
      {  
          ArrayList<Integer> Operande= new ArrayList<Integer>(); //opérandes
          ArrayList<String> Operateur =new ArrayList <String>();  //opérateur
          String[] condition = conditions.get(i).split(" ");
      
         int j=0;
          while(j < condition.length)
          { condition[j]=condition[j].trim();
           
              if(condition[j].contains("?x")) 
              {Operande.add(x);  }
              else if(condition[j].contains("?y")) Operande.add(y);
              else if(condition[j].contains("+")) Operateur.add("+");
              else if(condition[j].contains("-") ) Operateur.add("-");
              else if((condition[j].contains("<")) || (condition[j].contains("<=")) ||
                    (condition[j].contains( ">=")) || (condition[j].contains("==") || (condition[j].contains( ">")))) 
            {  
            	  if(Operande.size()>1)
                      { 
            		  int number=Calculer(Operande,Operateur);
            		  Operande.add(number); }
          
                   Operateur.add(condition[j]);
            } 
            
              else{
                int n= Integer.parseInt(condition[j]);
                Operande.add(n);
            }
            
            j++;
          }
          
          if(Operateur.get(0).equals( "==")) 
          {
        	  if(Operande.get(0)== Operande.get(1)) bool= true  ;
        	  else bool=false;
          }
          
          
          if(Operateur.get(0).equals("<"))
          {
        	  if(Operande.get(0) <Operande.get(1))
        	  { 
        		  bool= true; 
        		  } 
        	  else bool=false;
              
          }
         
          if(Operateur.get(0).equals("<="))  
          {
        	  if(Operande.get(0)<= Operande.get(1)) 
        	  {
        		  bool= true;
        		  } 
        	  else bool=false;
          }
          
          
          if(Operateur.get(0).equals(">"))   
          {
        	  if(Operande.get(0)> Operande.get(1)){ 
        		  bool= true; 
        		  }
        	  else bool=false;
          }
                   
          if(Operateur.get(0).equals(">="))  
          {
        	  if(Operande.get(0)>= Operande.get(1)) {
        		  bool= true;
        		  } 
        	  else bool=false; 
          }
               
         i++;  
      } return bool;
      }    
    
    
    
    
    public int CalcExpr(String S,Etat init )
    { 
    	int x=0; 
    	int y=0; 
        String[] expr= S.split(" ");
      
       if (expr[0].contains("?x") ) 
    	   x= init.x;
       else if( expr[0].contains("?y")) 
    	   x=init.y;
       else 
    	   x=Integer.parseInt(expr[0]);
       if (expr[2].contains("?x") ) 
        	 y= init.x;
       else if( expr[2].contains("?y")) 
    	   y=init.y;
       else y=Integer.parseInt(expr[2]);
         if(expr[1].equals("+")) 
        	 return y+x ;
         if(expr[1].equals("-")) 
        	 return x - y ;
         return 0;
    }   
    
    
    
    public Etat Etat_final(Etat EtatInitial) 
    {  
    	int x=0; 
    	int y=0;
    	String expr="";
        String X= etat_final.substring(etat_final.indexOf("(")+1, etat_final.indexOf(",")).trim();
        
         String Y= etat_final.substring(etat_final.indexOf(",")+1,etat_final.lastIndexOf(")")).trim();
         if(X.contains("("))
         {
        	 expr= X.substring(X.indexOf("(")+1,X.indexOf(")"));
        	 int n= CalcExpr(expr,EtatInitial);
             String N= Integer.toString(n);
             X=X.replace("("+ expr + ")", N); 
         }
         if(Y.contains("("))
         {  expr= Y.substring(Y.indexOf("(")+1,Y.indexOf(")"));
        	 int n= CalcExpr(expr,EtatInitial);
             String N= Integer.toString(n);          
         	Y=Y.replace("(" + expr + ")", N);
         }
                 
        if(X.split(" ").length == 1) 
        {
            if(X.contains("?x")) x=EtatInitial.x;
           else if(X.contains("?y")) x=EtatInitial.y;
            else x=Integer.parseInt(X); 
        }
        else if(X.split(" ").length > 1)  {
            x=CalcExpr(X,EtatInitial);
        } 
       
         if(Y.split(" ").length == 1) 
        {
            if(Y.contains("?x")) y=EtatInitial.x;
           else if(Y.contains("?y")) y=EtatInitial.y;
            else y=Integer.parseInt(Y); 
        }
         else if(Y.split(" ").length > 1) { 
             
            y=CalcExpr(Y,EtatInitial);
        }
         return  new Etat(x,y);
    }
            }