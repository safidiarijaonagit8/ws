package web.service.webservicefrontofficeetmobile.fonctionsBdd;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Crud 
{
    public Boolean verifierCaracter(String phrase, char caratere)
    {
        Boolean retour = false;
        char[] tabCaractere = phrase.toCharArray();
        for(int i = 0; i < tabCaractere.length; i++)
        {
            if(tabCaractere[i] == caratere)
            {
                retour = true;
            }
        }
        return retour;
    }

    public String formatPhrase(String phrase, char caratere)
    {
        String retour = null;
        ArrayList list = new ArrayList();
        Boolean condition = this.verifierCaracter(phrase, caratere);
        if(condition == true)
        {
            char[] tabCaractere = phrase.toCharArray();
            for(int i = 0; i < tabCaractere.length; i++)
            {
                if(tabCaractere[i] != caratere)
                {
                    list.add(tabCaractere[i]);
                }
                else
                {
                    int indice = i;
                    char caratere2 = '\\';
                    list.add(caratere2);
                    list.add(tabCaractere[indice]);
                }
            }
            char[] tab = new char[list.size()];
            for(int k = 0; k < list.size(); k++)
            {
                tab[k] = (char) list.get(k);
            }
            retour = new String(tab);
        }
        else
        {
            retour = phrase;
        } 
        return retour;
    }

    public String parametreType(Object obj) 
    {
        String retour = null;
        if(obj != null)
        {
            String type = obj.getClass().getSimpleName();
            if (type.equalsIgnoreCase("String") || type.equalsIgnoreCase("Date") || type.equalsIgnoreCase("Time")) 
            {
                String phrase = obj.toString();
                String format = formatPhrase(phrase, '\'');
                retour = "'" + format + "'";
            } 
            else 
            {
                if(obj.toString().equalsIgnoreCase("0"))
                {
                    retour = "null";
                }
                else
                {
                    retour = obj.toString();
                }
            }
        }
        return retour;
    }

    public String[] getAttributes() 
    {
        Field[] fields = this.getClass().getDeclaredFields();
        String[] attributs = new String[fields.length];
        for (int i = 0; i < fields.length; i++) 
        {
            attributs[i] = "get" + fields[i].getName();
        }
        return attributs;
    }

    public String getColonnes() 
    {
        String colonne = "(";
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) 
        {
            colonne = colonne + fields[i].getName() + ", ";
            if (i == fields.length - 2) 
            {
                colonne = colonne + fields[fields.length - 1].getName() + ")";
                break;
            }
        }
        return colonne;
    }

    public String getSimpleColonne() 
    {
        String condition = "";
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) 
        {
            condition = fields[i].getName();
            break;
        }
        return condition;
    }

    public String[] getAllColonne() 
    {
        Field[] fields = this.getClass().getDeclaredFields();
        String[] colonne = new String[fields.length];
        for (int i = 0; i < fields.length; i++) 
        {
            colonne[i] = fields[i].getName();
        }
        return colonne;
    }

    public String[] getMethode() 
    {
        Method[] methods = this.getClass().getDeclaredMethods();
        String[] methode = new String[methods.length];
        for (int i = 0; i < methods.length; i++) 
        {
            methode[i] = methods[i].getName();
        }
        return methode;
    }
    


    public String toFirtUpperCase(String words)
    {
        String retour = words.substring(0,1).toUpperCase()+words.substring(1);
        return retour;
    }
    
    public String toFirtLowerCase(String words)
    {
        String retour = words.substring(0,1).toLowerCase()+words.substring(1);
        return retour;
    }

    public Method[] getAllMethodeSetters()
    {
        Field[] field = this.getClass().getDeclaredFields();
        Method[] methods = this.getClass().getDeclaredMethods();
        Method[] methodSetters = new Method[field.length];
        for(int i = 0; i < field.length; i++)
        {
            for(int j = 0; j < methods.length; j++)
            {
                String condition = "set".concat(toFirtUpperCase(field[i].getName()));
                if(methods[j].getName().equalsIgnoreCase(condition))
                {
                    methodSetters[i] = methods[j];
                }
            }
        }
        return methodSetters;
    }

    public String[] getAllTypeAttributes()
    {
        Field[] fields = this.getClass().getDeclaredFields();
        String[] type = new String[fields.length];
        for(int i = 0; i < fields.length; i++)
        {
            type[i] = fields[i].getType().getSimpleName();
        }
        return type;
    }
    
    public String[] getMethodeGetters2() 
    {
        String[] attributs = this.getAttributes();
        String[] methodes = this.getMethode();
        String[] tabGetters = null;
        ArrayList<String> list = new ArrayList<>();
        try 
        {
            for (int i = 1; i < attributs.length; i++) 
            {
                for (int j = 1; j < methodes.length; j++) 
                {
                    if (attributs[i].equalsIgnoreCase(methodes[j])) 
                    {
                        String temp = parametreType(this.getClass().getMethod(methodes[j]).invoke(this));
                        if(temp != null)
                        {
                            if(temp.equalsIgnoreCase("'null'")|| temp.equalsIgnoreCase("''") || temp.equalsIgnoreCase("null"))
                            {
                                continue;
                            }
                            list.add(temp);
                        }
                    }
                }
            }
            tabGetters = new String[list.size()];
            for(int j = 0; j < list.size(); j++)
            {
                tabGetters[j] = list.get(j);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return tabGetters;
    }

    public String[] getMethodeGetters3() 
    {
        String[] attributs = this.getAttributes();
        String[] methodes = this.getMethode();
        String[] tabColonne = null;
        ArrayList<String> list = new ArrayList<>();
        try 
        {
            for (int i = 1; i < attributs.length; i++) 
            {
                for (int j = 1; j < methodes.length; j++) 
                {
                    if (attributs[i].equalsIgnoreCase(methodes[j])) 
                    {
                        String temp = parametreType(this.getClass().getMethod(methodes[j]).invoke(this));
                        if(temp != null)
                        {
                            if(temp.equalsIgnoreCase("'null'")|| temp.equalsIgnoreCase("''") || temp.equalsIgnoreCase("null"))
                            {
                                continue;
                            }
                            String exemple = methodes[j].substring(3);
                            list.add(exemple);
                        }
                    }
                }
            }
            tabColonne = new String[list.size()];
            for(int j = 0; j < list.size(); j++)
            {
                tabColonne[j] = list.get(j);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return tabColonne;
    }

    public String[] getMethodeSetters2() 
    {
        String[] attributs = this.getAttributes();
        String[] methodes = this.getMethode();
        String[] tabSetters = null;
        ArrayList<String> list = new ArrayList<>();
        try 
        {
            for (int i = 1; i < attributs.length; i++) 
            {
                for (int j = 1; j < methodes.length; j++) 
                {
                    if (attributs[i].equalsIgnoreCase(methodes[j])) 
                    {
                        String temp = parametreType(this.getClass().getMethod(methodes[j]).invoke(this));
                        if(temp != null)
                        {
                            if(temp.equalsIgnoreCase("'null'")|| temp.equalsIgnoreCase("''") || temp.equalsIgnoreCase("null"))
                            {
                                continue;
                            }
                            String setters = methodes[i];
                            list.add(setters);
                        }
                    }
                }
            }
            tabSetters = new String[list.size()];
            for(int j = 0; j < list.size(); j++)
            {
                tabSetters[j] = list.get(j);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return tabSetters;
    }
    

    public Method[] getAllMethodSetters()
    {
        Method[] methods = this.getClass().getDeclaredMethods();
        String[] tabSetters = this.getMethodeSetters2();
        Method[] retour = new Method[tabSetters.length];
        for(int i = 0; i < tabSetters.length; i++)
        {
            for(int j = 0; j < methods.length; j++)
            {
                if(tabSetters[i].equalsIgnoreCase(methods[j].getName()))
                {
                    retour[i] = methods[j];
                }
            }
        }
        return retour;
    }


    public String[] getTypeAttribute2()
    {
        String[] tabAttribute = this.getMethodeGetters3();
        Field[] att = this.getClass().getDeclaredFields();
        String[] type = new String[tabAttribute.length];
        for(int i = 0; i < tabAttribute.length; i++)
        {
            for(int j = 0; j < att.length; j++)
            {
                if(tabAttribute[i].equalsIgnoreCase(att[j].getName()))
                {
                    type[i] = att[j].getType().getSimpleName();
                }
            }
        }
        return type;
    }

    public String getRequeteFind()
    {
        String nomClasse = this.getClass().getSimpleName();
        String[] tabGetters = this.getMethodeGetters2();
        String[] tabColonne = this.getMethodeGetters3();
        String requete = "select * from "+nomClasse+" where ";
        for(int i = 0; i < tabColonne.length; i++)
        {
            if(tabColonne.length == 1)
            {
                requete = requete + tabColonne[i] +" = "+ tabGetters[i];
            }
            else
            {
                requete = requete + tabColonne[i] +" = "+ tabGetters[i]+" and ";
                if(i == tabColonne.length-2)
                {
                    requete = requete + tabColonne[tabColonne.length-1] +" = "+ tabGetters[tabColonne.length-1];
                    break;
                }
            }
        }
        return requete;
    }
}
