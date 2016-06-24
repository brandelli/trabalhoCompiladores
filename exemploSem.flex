%%

%byaccj

%{
  //Bruno Dorscheidt Brandelli, 122019003 João Vicente 11180565, João Berte 14280223
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    yyline = 1;
  }


  public int getLine() {
      return yyline;
  }

%}

NUM = [0-9]+
DOUBLE = {NUM}+ ("." {NUM}+)?
NL  = \n|\r|\r\n

%%


"$TRACE_ON"  { yyparser.setDebug(true);  }
"$TRACE_OFF" { yyparser.setDebug(false); }
"$MOSTRA_TS" { yyparser.listarTS(); }


/* operators */
"+" |
"." |
"-" |
":" |
"*" |
"/" |
"," |
"=" |
">" |
"<" |
";" |
"(" |
")" |
"\{" |
"\}" |
"\[" |
"\]"    { return (int) yycharat(0); }

"&&" { return Parser.AND; }
"||" { return Parser.OR; }
">=" {return Parser.GREATEREQUAL;}
"=<" {return Parser.LESSEREQUAL;}

{NUM}  { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
         return Parser.NUM; }
{DOUBLE}  { yyparser.yylval = new ParserVal(Float.parseFloat(yytext()));
                  return Parser.DOUBLE; }

return {return Parser.RETORNO;}
new {return Parser.NEW;}
int    { return Parser.INT;}
bool   { return Parser.BOOL; }
double {return Parser.DOUBLE;}
string { return Parser.STRING; }
void { return Parser.VOID; }
class {return Parser.CLASSE;}
main { return Parser.MAIN; }
if { return Parser.IF; }
while {return Parser.WHILE;}
endWhile {return Parser.ENDWHILE;}
for {return Parser.FOR;}
Escreva {return Parser.ESCREVA;}
Leia {return Parser.LEIA;}
endFor {return Parser.ENDFOR;}
break {return Parser.BREAK;}
else { return Parser.ELSE; }
endIf { return Parser.ENDIF; }
private: {return Parser.PRIVATE;}
public: {return Parser.PUBLIC;}
struct { return Parser.STRUCT; }
true {return Parser.TRUE;}
false {return Parser.FALSE;}
[a-zA-Z][a-zA-Z_0-9]* { yyparser.yylval = new ParserVal(yytext());
                     return Parser.IDENT; }



\"[^\"]*\" { yyparser.yylval = new ParserVal(yytext());
             return Parser.LITERAL; }



{NL}   {yyline++;}
[ \t]+ { }

.    { System.err.println("Error: unexpected character '"+yytext()+"' na linha "+yyline); return YYEOF; }
