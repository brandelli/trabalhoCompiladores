//Bruno Dorscheidt Brandelli, 122019003 João Vicente 11180565, João Berte 14280223
import java.util.ArrayList;
/**
 * Write a description of class Paciente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TS_entry
{
   private String id;
   private ClasseID classe;
   private String escopo;
   private TS_entry tipo;
   private int nElem;
   private TS_entry tipoBase;
   private TabSimb locais;
   private String atribs;


   // construtor para arrays
   public TS_entry(String umId, TS_entry umTipo,
		   int ne, TS_entry umTBase,
            String umEscopo, ClasseID umaClasse,String umArray) {
      id = umId;
      tipo = umTipo;
      escopo = umEscopo;
      nElem = ne;
      tipoBase = umTBase;
      classe = umaClasse;
      locais = new TabSimb();
      atribs = umArray;
   }

   // construtor default
   public TS_entry(String umId, TS_entry umTipo, String escopo, ClasseID classe) {
      this(umId, umTipo, -1, null, escopo, classe, null);
   }

   //Contrutor para funcoes
   public TS_entry(String umId, TS_entry umTipo, int nro, String escopo, ClasseID classe,String umArray) {
      this(umId, umTipo, nro, null, escopo, classe,umArray);
   }

   public String getId() {
       return id;
   }

   public TS_entry getTipo() {
       return tipo;
   }

   public String getTipoStr() {
       return tipo2str(this);
   }

   public String getEscopo() {
       return escopo;
   }

   public int getNumElem() {
       return nElem;
   }

   public String getAtribs() {
       return atribs;
   }

   public TS_entry getTipoBase() {
       return tipoBase;
   }
   public TabSimb getLocais() {
       return locais;
   }

   public String toString() {
       StringBuilder aux = new StringBuilder("");

	     aux.append("Id: ");
	     aux.append(String.format("%-10s", id));

	     aux.append("\tClasse: ");
	     aux.append(classe);
	     aux.append("\tEscopo: ");
	     aux.append(String.format("%-4s", escopo));
	     aux.append("\tTipo: ");
	     aux.append(tipo2str(this.tipo));
       aux.append("\tnro atributos: ");
	     aux.append(nElem);
       aux.append("\ttipo atributos: ");
       aux.append(atribs);
    //
    //   if (this.tipo == Parser.Tp_ARRAY) {
    // 	     aux.append(" (ne: ");
	  //        aux.append(nElem);
    // 	     aux.append(", tBase: ");
	  //        aux.append(tipo2str(this.tipoBase));
    // 	     aux.append(")");
    //
    // }

        ArrayList<TS_entry> lista = locais.getLista();
        for (TS_entry t : lista) {
            aux.append("\n\t");
	    		  aux.append(t.toString());
        }

      return aux.toString();

   }

    public String tipo2str(TS_entry tipo) {
      if (tipo == null)  return "null";
     	else if (tipo==Parser.Tp_INT)    return "int";
      else if (tipo==Parser.Tp_BOOL)   return "boolean";
      else if (tipo==Parser.Tp_DOUBLE) return "double";
      else if (tipo==Parser.Tp_STRING) return "string";
      else if (tipo==Parser.Tp_CLASS) return "class";
      else if (tipo==Parser.Tp_ERRO)  return  "_erro_";
	    else                             return "erro/tp";
   }



   // public void insereLocal(String id, int tp, ClasseID cl) {
   //      locais.insert(new TS_entry(id, tp, cl));
   //}

}
