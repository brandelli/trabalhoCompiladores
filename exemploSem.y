
%{
  import java.io.*;
%}


%token IDENT, INT, BOOL, NUM, STRING, DOUBLE, NEW, TRUE, FALSE
%token LITERAL, AND, VOID, MAIN, IF, CLASSE, WHILE, ENDWHILE, FOR, ENDFOR, ESCREVA, LEIA
%token STRUCT, PRIVATE, PUBLIC , OR, GREATEREQUAL, LESSEREQUAL, RETORNO, ENDIF, ELSE, BREAK

%right '='
%nonassoc '>' GREATEREQUAL LESSEREQUAL '<'
%left '+' '-'
%left '/' '*'
%left AND OR
%left '['

%type <sval> IDENT
%type <ival> NUM
%type <obj> type
%type <obj> exp

%%

prog : { currEscopo = "Global"; currClass = ClasseID.VarGlobal; } clas ;

clas :  CLASSE IDENT {tipoClasse = (String)$2;  TS_entry nodo = ts.pesquisa($2);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + $2 + "< jah declarada");

                          else ts.insert(new TS_entry($2, (TS_entry)Tp_CLASS, currEscopo, ClasseID.TipoClasse));
                        }'{' privados publicos '}'
 			;


privados: PRIVATE dList
				;


publicos: PUBLIC mList
        ;

dList : type {currType = $1;} decl dList
      |
			;

decl : IDENT moreDecl ';'{  TS_entry nodo = ts.pesquisa($1);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + $1 + "< jah declarada");

                          else ts.insert(new TS_entry($1, (TS_entry)currType, currEscopo, currClass));
                        }
      ;

moreDecl : ',' IDENT moreDecl {  TS_entry nodo = ts.pesquisa($2);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + $2 + "< jah declarada");

                          else ts.insert(new TS_entry($2, (TS_entry)currType, currEscopo, currClass));
                        }
         |
				 ;

type : INT    { $$ = Tp_INT; }
     | BOOL   { $$ = Tp_BOOL; }
		 | DOUBLE { $$ = Tp_DOUBLE; }
	   | STRING { $$ = Tp_STRING; }
     | IDENT  { TS_entry nodo = ts.pesquisa($1);
		 									if($1.equals(tipoClasse)){
											    $$ = Tp_CLASS;
											}else{
                        $$ = Tp_ERRO;
												yyerror("(sem) Nome de tipo <" + $1 + "> nao declarado ");
											}
              }
     ;


mList: {currClass = ClasseID.VarLocal;} blocos main

		 ;

blocos:  blocos bl
 			|
			;

bl : INT IDENT '(' parametros ')' {currRetorno = Tp_INT; currEscopo = (String)$2;
TS_entry nodo = ts.pesquisa($2);
                         if (nodo != null && nodo.getEscopo().equals(currEscopo))
                             yyerror("metodo ja declarado >" + $2 + "< jah declarada");

                         else ts.insert(new TS_entry($2, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));} dList bloco
   | BOOL   IDENT '(' parametros ')' {currRetorno = Tp_BOOL ; currEscopo = (String)$2;
   TS_entry nodo = ts.pesquisa($2);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + $2 + "< jah declarada");

                            else ts.insert(new TS_entry($2, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));} dList bloco
	 | DOUBLE  IDENT '(' parametros ')'  { currRetorno = Tp_DOUBLE ;currEscopo = (String)$2;
   TS_entry nodo = ts.pesquisa($2);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + $2 + "< jah declarada");

                            else ts.insert(new TS_entry($2, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));} dList bloco
	 | STRING IDENT '(' parametros ')' { currRetorno = Tp_STRING ;currEscopo = (String)$2;
   TS_entry nodo = ts.pesquisa($2);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + $2 + "< jah declarada");

                            else ts.insert(new TS_entry($2, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));} dList bloco
   | IDENT '(' parametros ')' blocoConstrutor {         if(!($1.equals(tipoClasse))){
											  yyerror("(sem) Nome de tipo <" + $1 + "> nao declarado ");
											}
              }
     ;

parametros : type IDENT lParametros  {  TS_entry nodo = ts.pesquisa($2);
    	                    		if (nodo != null)
                              		yyerror("(sem) variavel >" + $2 + "< jah declarada");
                          		else ts.insert(new TS_entry($2, (TS_entry)$1, currEscopo, currClass));
                        }
           |
          ;

lParametros	: ',' type  IDENT lParametros  {  TS_entry nodo = ts.pesquisa($3);
    	                    		if (nodo != null)
                              		yyerror("(sem) variavel >" + $3 + "< jah declarada");
                          		else ts.insert(new TS_entry($3, (TS_entry)$2, currEscopo, currClass));
                        }
            |
            ;


main :  VOID MAIN '(' ')' dList blocoConstrutor
     |
		 ;

blocoConstrutor : '{' listacmd '}';

bloco : '{' listacmd retorno '}';

retorno : RETORNO exp { if ($2 != currRetorno)
  yyerror("Tipo de retorno incorreto"); }; ';'
        ;

listacmd : listacmd cmd
		     |
 		     ;

listacmdrep : listacmdrep cmd
            | BREAK
            |
     		    ;

cmd :  exp ';'
    | IF  exp  {if ($2 != Tp_BOOL){
          yyerror("Expressao deve ser booleana");}
          } ':'
    listacmd else ENDIF
    | while
    | for
    | ESCREVA escreva ';'
    | LEIA leia ';'
    ;

else :  '['  ELSE  ':' cmd listacmd ']'
     |
     ;

escreva : exp {if ($1 != Tp_STRING ){
      yyerror("Expressao deve ser do tipo string");}
      } parametroEscreva
        ;

leia : exp {if ($1 != Tp_BOOL && $1 != Tp_INT && $1 != Tp_DOUBLE && $1 != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
     ;

parametroEscreva : ',' exp {if ($2 != Tp_BOOL && $2 != Tp_INT && $2 != Tp_DOUBLE && $2 != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
                 |
                 ;

while : WHILE exp {if ($2 != Tp_BOOL){
      yyerror("Expressao deve ser booleana");}
      } cmd listacmdrep ENDWHILE
      ;


      for :  FOR  IDENT "=" exp { TS_entry nodo = ts.pesquisa($2);
                         if (nodo == null)
                            yyerror("(sem) var <" + $2 + "> nao declarada");
                            else{
                              validaTipo(ATRIB, (TS_entry)nodo.getTipo(), (TS_entry)$4);
                            }
      }  ';' exp  {if ($7 != Tp_BOOL){
                          yyerror("Expressao deve ser booleana");}
                          }';' exp ':' cmd listacmdrep ENDFOR
          ;



exp : exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
    | exp '*' exp { $$ = validaTipo('*', (TS_entry)$1, (TS_entry)$3); }
    | exp '-' exp { $$ = validaTipo('-', (TS_entry)$1, (TS_entry)$3); }
    | exp '/' exp { $$ = validaTipo('/', (TS_entry)$1, (TS_entry)$3); }
   	| exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3);}
    | exp '<' exp { $$ = validaTipo('<', (TS_entry)$1, (TS_entry)$3);}
 	  | exp AND exp { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); }
    | exp OR exp  { $$ = validaTipo(OR, (TS_entry)$1, (TS_entry)$3); }
    | exp LESSEREQUAL exp { $$ = validaTipo(LESSEREQUAL, (TS_entry)$1, (TS_entry)$3); }
    | exp GREATEREQUAL exp { $$ = validaTipo(GREATEREQUAL, (TS_entry)$1, (TS_entry)$3); }
    | TRUE {$$ = Tp_BOOL;}
    | FALSE {$$ = Tp_BOOL;}
    | NUM         { $$ = Tp_INT;}
    | DOUBLE       {$$ = Tp_DOUBLE;}
    | '(' exp ')' { $$ = $2; }
    | LITERAL    { $$ = Tp_STRING; }
    | IDENT       { TS_entry nodo = ts.pesquisa($1);
    	                 if (nodo == null)
	                        yyerror("(sem) var <" + $1 + "> nao declarada");
                      else
			                    $$ = nodo.getTipo();
			            }
     | exp '=' exp  {$$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);}
     | metodo
     ;

metodo : IDENT '.' IDENT '(' parametrosMetodo ')'
      ;

parametrosMetodo :  exp lParametrosMetodo
                 |
                 ;

lParametrosMetodo : ',' exp lParametrosMetodo
                  |
                  ;

%%

  private Yylex lexer;

  private String tipoClasse;
  private Object currType;
  private String currEscopo;
  private ClasseID currClass;
  private TS_entry currRetorno;
  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, "", ClasseID.TipoBase);
	public static TS_entry Tp_DOUBLE =  new TS_entry("double", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_STRING = new TS_entry("string", null, "", ClasseID.TipoBase);
	public static TS_entry Tp_CLASS = new TS_entry("tipo de classe", null, "", ClasseID.TipoClasse);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null, "", ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_STRING);
		ts.insert(Tp_CLASS);


  }

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");


    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

  	//yyparser.listarTS();

	  System.out.print("\n\nFeito!\n");

  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {

         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A ==Tp_STRING)                                     ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

            case '-' :
                          if ( A == Tp_INT && B == Tp_INT)
                                return Tp_INT;
                          else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
      				              		      (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                               return Tp_DOUBLE;
                          else
                              yyerror("(sem) tipos incomp. para subtracao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                          break;
              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
				              		      (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                         return Tp_DOUBLE;
                    else if (A==Tp_STRING || B==Tp_STRING)
                        return Tp_STRING;
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

            case '/' :
                      if ( A == Tp_INT && B == Tp_INT)
                                  return Tp_INT;
                      else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
              				            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                                  return Tp_DOUBLE;
                      else
                                  yyerror("(sem) tipos incomp. para divisao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                                  break;

            case '*' :
                      if ( A == Tp_INT && B == Tp_INT)
                                   return Tp_INT;
                      else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                                   return Tp_DOUBLE;
                      else
                                   yyerror("(sem) tipos incomp. para multiplicacao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                                   break;


             case '>' :
   	              if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
					        else
                         yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
			            break;
            case '<' :
        	        if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                        return Tp_BOOL;
     					    else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
     			        break;

           case GREATEREQUAL :
              	  if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                        return Tp_BOOL;
           				else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
           			  break;

           case LESSEREQUAL :
                 if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                        return Tp_BOOL;
                 else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                 break;

             case AND:
 	                if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
					       else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;

             case OR:
                  if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                  else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                  break;
            						}
            return Tp_ERRO;
				}

/*
  | IDENT '=' exp
                 { TS_entry nodo = ts.pesquisa($1);
    	             if (nodo == null)
                       yyerror("(sem) variavel >" + $1 + "< nao declarada");
                   else
                       $$ = validaTipo(ATRIB, nodo.getTipo(), (TS_entry)$3);
                   }
*/
