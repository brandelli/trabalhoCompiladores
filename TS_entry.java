//Bruno Dorscheidt Brandelli 122019003 bdbrandelli@hotmail.com
//João Berte 14280223 joao.berte@acad.pucrs.br
//João Vicente 11180565 joao.silva.008@acad.pucrs.br
import java.util.ArrayList;
public class TS_entry
{
   private String id;
   private ClasseID classe;
   private String escopo;
   private TS_entry tipo;
   private int nElem;
   private TS_entry tipoBase;
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
       return tipo2str(this.tipo);
   }

   public String getTipoStrParam() {
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


}
