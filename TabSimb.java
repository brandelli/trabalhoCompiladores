//Bruno Dorscheidt Brandelli, 122019003 João Vicente 11180565, João Berte 14280223
import java.util.ArrayList;
import java.util.Iterator;


public class TabSimb
{
    private ArrayList<TS_entry> lista;

    public TabSimb( )
    {
        lista = new ArrayList<TS_entry>();
    }

     public void insert( TS_entry nodo ) {
      lista.add(nodo);
    }

    public void listar() {
      int cont = 0;
      System.out.println("\n\nListagem da tabela de simbolos:\n");
      for (TS_entry nodo : lista) {
          System.out.println(nodo);
      }
    }

    public TS_entry pesquisa(String umId) {
      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId)) {
	      return nodo;
            }
      }
      return null;
    }


    public TS_entry pesquisaMetodo(String umId,int nroAtributos,String array) {

      for (TS_entry nodo : lista) {
          if (nodo.getId().equals(umId) && nroAtributos == nodo.getNumElem() && nodo.getAtribs().equals(array)) {
        return nodo;
            }
      }
      return null;
    }

    public  ArrayList<TS_entry> getLista() {return lista;}
}
