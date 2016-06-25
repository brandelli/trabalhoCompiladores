//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploSem.y"
  /*Bruno Dorscheidt Brandelli, 122019003 João Vicente 11180565, João Berte 14280223*/
  import java.io.*;
  import java.util.ArrayList;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short BOOL=259;
public final static short NUM=260;
public final static short STRING=261;
public final static short DOUBLE=262;
public final static short NEW=263;
public final static short TRUE=264;
public final static short FALSE=265;
public final static short LITERAL=266;
public final static short AND=267;
public final static short VOID=268;
public final static short MAIN=269;
public final static short IF=270;
public final static short CLASSE=271;
public final static short WHILE=272;
public final static short ENDWHILE=273;
public final static short FOR=274;
public final static short ENDFOR=275;
public final static short ESCREVA=276;
public final static short LEIA=277;
public final static short STRUCT=278;
public final static short PRIVATE=279;
public final static short PUBLIC=280;
public final static short OR=281;
public final static short GREATEREQUAL=282;
public final static short LESSEREQUAL=283;
public final static short RETORNO=284;
public final static short ENDIF=285;
public final static short ELSE=286;
public final static short BREAK=287;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    5,    0,    6,    4,    7,    8,   12,    9,    9,   11,
   13,   13,    1,    1,    1,    1,    1,   15,   10,   14,
   14,   18,   20,   17,   22,   23,   17,   24,   25,   17,
   26,   27,   17,   28,   17,   30,   19,   19,   32,   31,
   31,   33,   16,   16,   29,   21,   36,   35,   34,   34,
   38,   38,   38,   37,   39,   37,   37,   37,   37,   37,
   40,   40,   46,   43,   44,   45,   45,   47,   41,   48,
   49,   42,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,   50,    2,   52,    3,   51,   51,   53,   53,
};
final static short yylen[] = {                            2,
    0,    2,    0,    7,    2,    2,    0,    4,    0,    3,
    3,    0,    1,    1,    1,    1,    1,    0,    3,    2,
    0,    0,    0,    9,    0,    0,    9,    0,    0,    9,
    0,    0,    9,    0,    6,    0,    4,    0,    0,    5,
    0,    0,    7,    0,    3,    4,    0,    4,    2,    0,
    2,    1,    0,    2,    0,    7,    1,    1,    3,    3,
    6,    0,    0,    3,    1,    2,    0,    0,    6,    0,
    0,   14,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    1,    1,    1,    1,    3,    1,    1,    3,
    1,    0,    6,    0,    7,    2,    0,    3,    0,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    2,    3,    0,    0,    0,    0,   17,
   13,   14,   16,   15,    7,    5,   18,    0,    0,    6,
   21,    4,    0,    0,    0,    0,    0,    8,   34,    0,
    0,    0,    0,    0,   19,   20,    0,   10,    0,   22,
   25,   31,   28,   42,   11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,   35,    0,   23,   26,
   32,   29,    0,    0,   37,    0,    0,    0,    0,    0,
   43,    0,   85,   86,    0,   83,   84,   88,    0,    0,
    0,    0,    0,   45,    0,    0,   91,   49,   57,   58,
    0,    0,    0,    0,    0,    0,   92,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   54,    0,   50,   24,
   27,   33,   30,   94,    0,    0,    0,    0,    0,   59,
   60,   87,   79,   80,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   50,    0,    0,
    0,   64,   40,    0,    0,    0,    0,    0,    0,   52,
    0,    0,    0,    0,   46,    0,    0,   96,   93,    0,
    0,   69,   51,    0,    0,   95,    0,    0,   56,    0,
   48,   98,    0,    0,   50,    0,    0,    0,   61,    0,
    0,    0,   72,
};
final static short yydgoto[] = {                          1,
   15,   96,   97,    4,    2,    6,    9,   18,   16,   20,
   24,   19,   27,   25,   21,   35,   36,   47,   52,   77,
  130,   48,   78,   50,   80,   49,   79,   39,   67,   53,
   75,   76,   51,   74,  165,  185,   98,  171,  136,  181,
   99,  100,  112,  114,  162,  139,  137,  172,  194,  135,
  168,  156,  178,
};
final static short yysindex[] = {                         0,
    0, -256, -241,    0,    0,  -92, -240, -224, -233,    0,
    0,    0,    0,    0,    0,    0,    0,  -85, -209,    0,
    0,    0,   16, -224, -153, -196,    3,    0,    0, -194,
 -193, -191, -177, -187,    0,    0,   16,    0,   44,    0,
    0,    0,    0,    0,    0,    0,   49,   50,   51,   57,
   60,   66, -224,    0,    0,    0,    0,   69,  -12, -145,
   72,   73,   80,   83, -224,    0,    0,    0,    0,    0,
    0,    0,  -12,  243,    0,   84, -224, -224, -224, -224,
    0,   86,    0,    0, -123,    0,    0,    0,  178,  178,
 -121,  178,  178,    0,  178,  109,    0,    0,    0,    0,
 -224,   14,   14,   14,   14, -119,    0,  142,  142,   78,
  142,   87,  142,   94,   88,  178,  178,  178,  178,  178,
  178,  178,  178,  178,  178,  178,    0, -110,    0,    0,
    0,    0,    0,    0,  105,   97,  518,  178,  117,    0,
    0,    0,    0,    0,  -39,  -39,  142,  -39,  -39,  -29,
  -29, -262, -262,    0,  -40,  123,  178,    0, -122,  142,
  178,    0,    0,  178,   41,  178,  115,  126,  269,    0,
  287,  113,  142,  142,    0,  132,  178,    0,    0, -106,
 -111,    0,    0,  178,  127,    0,  115,  124,    0,  142,
    0,    0,  518,  129,    0,  178,  344,  136,    0,  518,
 -122,  457,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,  -90,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  133, -116,   68,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  133,    0,    0,    0,
    0,    0,    0,    0,    0,  227,    0,    0,    0,    0,
    0,    0,    0,  227,  227,  227,  227,    0,    0,    0,
    0,    0,    0,    0,   76,    0,    0,   15,    0,    0,
    0,    0,    0,    0,    0,    0,   76,   76,   76,   76,
    0,  -17,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  137,  536,    0,
  -30,    0,  141,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  146,    0,
    0,    0,    0,    0,  322,  370,  436,  392,  414,   58,
   82,   10,   34,   15,    0,    0,  150,    0,  478,  147,
    0,    0,    0,    0,    0,  150,  160,    0,  -78,    0,
    0,    0,  149,  151,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  160,    0,    0,  152,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  496,    0,    0,
};
final static short yygindex[] = {                         0,
  -52,  725,    0,    0,    0,    0,    0,    0,    8,    0,
    0,    0,  172,    0,    0,    0,    0,    0,  -45,    0,
  -83,    0,    0,    0,    0,    0,    0,    0,  139,    0,
   59,    0,    0, -112,    0,    0, -135,   13,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   53,    0,   28,
};
final static int YYTABLESIZE=921;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         95,
   60,  159,  126,  123,  116,  124,    9,  125,   61,   62,
   63,   64,  126,   63,    3,    5,  155,  125,  117,  131,
  132,  133,   89,   89,   89,   89,   89,   89,   63,   89,
    7,   28,   10,   11,   12,  183,   13,   14,    8,   22,
   89,   89,   89,   89,   89,  169,   17,   23,  128,   76,
   76,   76,   76,   76,   76,   41,   76,  195,   39,   26,
   37,   38,   40,   41,  201,   42,  183,   76,   76,   76,
   76,   76,   73,   74,   74,   74,   74,   74,   74,   43,
   74,   44,  197,   46,  102,  103,  104,  105,   54,   55,
   56,   74,   74,   74,   74,   74,   57,   73,   73,   58,
   73,   73,   73,   29,   30,   31,   59,   32,   33,   65,
   66,   68,   69,   70,   34,   73,   73,   73,   73,   73,
   71,   75,   75,   72,   75,   75,   75,  101,  142,  126,
  123,  106,  124,  107,  125,  110,  129,  134,  138,   75,
   75,   75,   75,   75,  157,  140,  154,  122,  120,  121,
  126,  123,  141,  124,  158,  125,  126,  123,  177,  124,
  161,  125,  166,    9,  170,  175,  179,  127,  122,  120,
  121,  184,  186,  189,  122,  120,  121,  126,  123,  188,
  124,  193,  125,  126,  123,  191,  124,  196,  125,    9,
   97,   12,   44,  200,   55,  122,  120,  121,    9,   65,
   99,  122,  120,  121,   67,   70,   62,   66,   45,   47,
   71,   81,  163,  202,  192,    0,   82,   95,  176,   83,
    0,   84,   85,   86,   87,   88,    0,  116,    0,   89,
    0,   90,    0,   91,    0,   92,   93,  116,    0,   89,
    0,  117,   89,  164,   89,   89,   89,   89,   89,   89,
    0,  117,   89,    0,   89,    0,   89,    0,   89,   89,
    0,    0,    0,   89,   89,   89,   76,   38,    0,   76,
    0,   76,   76,   76,   76,   76,    0,    0,    0,   76,
    0,   76,   95,   76,    0,   76,   76,    0,    0,    0,
   74,   76,   76,   74,    0,   74,   74,   74,   74,   74,
    0,    0,    0,   74,    0,   74,    0,   74,   95,   74,
   74,    0,    0,    0,   73,   74,   74,   73,    0,   73,
   73,   73,   73,   73,    0,    0,   95,   73,    0,   73,
    0,   73,    0,   73,   73,    0,    0,    0,   75,   73,
   73,   75,    0,   75,   75,   75,   75,   75,    0,    0,
    0,   75,    0,   75,  116,   75,    0,   75,   75,  180,
    0,   82,   82,   75,   75,   82,    0,   94,  117,  118,
  119,    0,    0,    0,    0,  116,    0,    0,    0,   82,
   82,  116,   82,   95,    0,    0,    0,    0,    0,  117,
  118,  119,    0,    0,    0,  117,  118,  119,    0,    0,
    0,    0,  116,    0,    0,    0,    0,    0,  116,   81,
   81,    0,    0,   81,    0,    0,  117,  118,  119,    0,
    0,    0,  117,  118,  119,    0,    0,   81,   81,    0,
   81,   77,   77,    0,   82,   77,  199,   83,    0,   84,
   85,   86,   87,   88,    0,    0,    0,    0,    0,   77,
   77,    0,   77,   78,   78,    0,    0,   78,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   78,   78,    0,   78,   90,   90,    0,    0,   90,
    0,    0,    0,   36,   36,   36,    0,   36,   36,    0,
    0,    0,    0,   90,   90,    0,   95,    0,    0,   82,
    0,    0,   83,    0,   84,   85,   86,   87,   88,    0,
    0,    0,   89,    0,   90,    0,   91,   53,   92,   93,
    0,    0,    0,    0,    0,   82,    0,    0,   83,    0,
   84,   85,   86,   87,   88,   53,    0,    0,   89,    0,
   90,    0,   91,   82,   92,   93,   83,    0,   84,   85,
   86,   87,   88,    0,    0,    0,   89,   95,   90,  182,
   91,    0,   92,   93,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   68,    0,    0,   82,    0,
    0,   82,    0,   82,   82,   82,   82,   82,    0,    0,
    0,   82,    0,   82,    0,   82,    0,   82,   82,    0,
   82,    0,    0,   83,    0,   84,   85,   86,   87,   88,
    0,    0,    0,   89,    0,   90,    0,   91,    0,   92,
   93,    0,    0,    0,    0,    0,   81,    0,    0,   81,
    0,   81,   81,   81,   81,   81,    0,    0,    0,   81,
    0,   81,    0,   81,    0,   81,   81,    0,   77,    0,
    0,   77,    0,   77,   77,   77,   77,   77,    0,    0,
    0,   77,    0,   77,    0,   77,    0,   77,   77,    0,
   78,    0,    0,   78,    0,   78,   78,   78,   78,   78,
    0,    0,    0,   78,    0,   78,    0,   78,    0,   78,
   78,    0,   90,    0,    0,   90,    0,   90,   90,   90,
   90,   90,    0,    0,    0,   90,    0,   90,    0,   90,
    0,   90,   90,   82,    0,    0,   83,    0,   84,   85,
   86,   87,   88,    0,    0,    0,   89,    0,   90,    0,
   91,  203,   92,   93,   53,    0,    0,   53,    0,   53,
   53,   53,   53,   53,    0,    0,    0,   53,    0,   53,
   53,   53,   53,   53,   53,   53,    0,   53,   53,   53,
   53,   53,    0,    0,    0,   53,    0,   53,    0,   53,
   53,   53,   53,    0,   82,    0,    0,   83,    0,   84,
   85,   86,   87,   88,    0,    0,    0,   89,    0,   90,
    0,   91,   68,   92,   93,   68,    0,   68,   68,   68,
   68,   68,    0,    0,    0,   68,    0,   68,    0,   68,
    0,   68,   68,  108,  109,    0,  111,  113,    0,  115,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  143,  144,  145,  146,  147,  148,  149,  150,  151,  152,
  153,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  160,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  167,    0,    0,    0,  173,    0,    0,  174,    0,
  167,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  187,    0,    0,    0,    0,    0,    0,  190,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  198,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   53,  137,   42,   43,  267,   45,  123,   47,   54,   55,
   56,   57,   42,   44,  271,  257,  129,   47,  281,  103,
  104,  105,   40,   41,   42,   43,   44,   45,   59,   47,
  123,   24,  257,  258,  259,  171,  261,  262,  279,  125,
   58,   59,   60,   61,   62,  158,  280,  257,  101,   40,
   41,   42,   43,   44,   45,   41,   47,  193,   44,   44,
  257,   59,  257,  257,  200,  257,  202,   58,   59,   60,
   61,   62,   65,   40,   41,   42,   43,   44,   45,  257,
   47,  269,  195,   40,   77,   78,   79,   80,   40,   40,
   40,   58,   59,   60,   61,   62,   40,   40,   41,   40,
   43,   44,   45,  257,  258,  259,   41,  261,  262,   41,
  123,  257,   41,   41,  268,   58,   59,   60,   61,   62,
   41,   40,   41,   41,   43,   44,   45,   44,   41,   42,
   43,   46,   45,  257,   47,  257,  123,  257,   61,   58,
   59,   60,   61,   62,   40,   59,  257,   60,   61,   62,
   42,   43,   59,   45,   58,   47,   42,   43,   44,   45,
   44,   47,   40,  280,  287,  125,   41,   59,   60,   61,
   62,   59,   41,  285,   60,   61,   62,   42,   43,  286,
   45,   58,   47,   42,   43,   59,   45,   59,   47,  280,
   41,   59,  125,   58,   58,   60,   61,   62,  123,   59,
   41,   60,   61,   62,   59,   59,  285,   59,   37,   59,
   59,   73,  154,  201,  187,   -1,  257,   40,  166,  260,
   -1,  262,  263,  264,  265,  266,   -1,  267,   -1,  270,
   -1,  272,   -1,  274,   -1,  276,  277,  267,   -1,  257,
   -1,  281,  260,  284,  262,  263,  264,  265,  266,  267,
   -1,  281,  270,   -1,  272,   -1,  274,   -1,  276,  277,
   -1,   -1,   -1,  281,  282,  283,  257,   41,   -1,  260,
   -1,  262,  263,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,   40,  274,   -1,  276,  277,   -1,   -1,   -1,
  257,  282,  283,  260,   -1,  262,  263,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   40,  276,
  277,   -1,   -1,   -1,  257,  282,  283,  260,   -1,  262,
  263,  264,  265,  266,   -1,   -1,   40,  270,   -1,  272,
   -1,  274,   -1,  276,  277,   -1,   -1,   -1,  257,  282,
  283,  260,   -1,  262,  263,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,  267,  274,   -1,  276,  277,   91,
   -1,   40,   41,  282,  283,   44,   -1,  125,  281,  282,
  283,   -1,   -1,   -1,   -1,  267,   -1,   -1,   -1,   58,
   59,  267,   61,   40,   -1,   -1,   -1,   -1,   -1,  281,
  282,  283,   -1,   -1,   -1,  281,  282,  283,   -1,   -1,
   -1,   -1,  267,   -1,   -1,   -1,   -1,   -1,  267,   40,
   41,   -1,   -1,   44,   -1,   -1,  281,  282,  283,   -1,
   -1,   -1,  281,  282,  283,   -1,   -1,   58,   59,   -1,
   61,   40,   41,   -1,  257,   44,   93,  260,   -1,  262,
  263,  264,  265,  266,   -1,   -1,   -1,   -1,   -1,   58,
   59,   -1,   61,   40,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   -1,   61,   40,   41,   -1,   -1,   44,
   -1,   -1,   -1,  257,  258,  259,   -1,  261,  262,   -1,
   -1,   -1,   -1,   58,   59,   -1,   40,   -1,   -1,  257,
   -1,   -1,  260,   -1,  262,  263,  264,  265,  266,   -1,
   -1,   -1,  270,   -1,  272,   -1,  274,   40,  276,  277,
   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,
  262,  263,  264,  265,  266,   40,   -1,   -1,  270,   -1,
  272,   -1,  274,  257,  276,  277,  260,   -1,  262,  263,
  264,  265,  266,   -1,   -1,   -1,  270,   40,  272,  273,
  274,   -1,  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,  257,   -1,
   -1,  260,   -1,  262,  263,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,
  257,   -1,   -1,  260,   -1,  262,  263,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,   -1,   -1,   -1,   -1,   -1,  257,   -1,   -1,  260,
   -1,  262,  263,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,   -1,  274,   -1,  276,  277,   -1,  257,   -1,
   -1,  260,   -1,  262,  263,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,
  257,   -1,   -1,  260,   -1,  262,  263,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,   -1,  257,   -1,   -1,  260,   -1,  262,  263,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,
   -1,  276,  277,  257,   -1,   -1,  260,   -1,  262,  263,
  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,
  274,  275,  276,  277,  257,   -1,   -1,  260,   -1,  262,
  263,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
  273,  274,  257,  276,  277,  260,   -1,  262,  263,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,
  275,  276,  277,   -1,  257,   -1,   -1,  260,   -1,  262,
  263,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
   -1,  274,  257,  276,  277,  260,   -1,  262,  263,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,
   -1,  276,  277,   89,   90,   -1,   92,   93,   -1,   95,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  116,  117,  118,  119,  120,  121,  122,  123,  124,  125,
  126,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  138,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  157,   -1,   -1,   -1,  161,   -1,   -1,  164,   -1,
  166,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  177,   -1,   -1,   -1,   -1,   -1,   -1,  184,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  196,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=287;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","BOOL","NUM","STRING",
"DOUBLE","NEW","TRUE","FALSE","LITERAL","AND","VOID","MAIN","IF","CLASSE",
"WHILE","ENDWHILE","FOR","ENDFOR","ESCREVA","LEIA","STRUCT","PRIVATE","PUBLIC",
"OR","GREATEREQUAL","LESSEREQUAL","RETORNO","ENDIF","ELSE","BREAK",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 clas",
"$$2 :",
"clas : CLASSE IDENT $$2 '{' privados publicos '}'",
"privados : PRIVATE dList",
"publicos : PUBLIC mList",
"$$3 :",
"dList : type $$3 decl dList",
"dList :",
"decl : IDENT moreDecl ';'",
"moreDecl : ',' IDENT moreDecl",
"moreDecl :",
"type : INT",
"type : BOOL",
"type : DOUBLE",
"type : STRING",
"type : IDENT",
"$$4 :",
"mList : $$4 blocos main",
"blocos : blocos bl",
"blocos :",
"$$5 :",
"$$6 :",
"bl : INT IDENT $$5 '(' parametros ')' $$6 dList bloco",
"$$7 :",
"$$8 :",
"bl : BOOL IDENT $$7 '(' parametros ')' $$8 dList bloco",
"$$9 :",
"$$10 :",
"bl : DOUBLE IDENT $$9 '(' parametros ')' $$10 dList bloco",
"$$11 :",
"$$12 :",
"bl : STRING IDENT $$11 '(' parametros ')' $$12 dList bloco",
"$$13 :",
"bl : IDENT $$13 '(' parametros ')' blocoConstrutor",
"$$14 :",
"parametros : $$14 type IDENT lParametros",
"parametros :",
"$$15 :",
"lParametros : $$15 ',' type IDENT lParametros",
"lParametros :",
"$$16 :",
"main : VOID MAIN $$16 '(' ')' dList blocoConstrutor",
"main :",
"blocoConstrutor : '{' listacmd '}'",
"bloco : '{' listacmd retorno '}'",
"$$17 :",
"retorno : RETORNO exp $$17 ';'",
"listacmd : listacmd cmd",
"listacmd :",
"listacmdrep : listacmdrep cmd",
"listacmdrep : BREAK",
"listacmdrep :",
"cmd : exp ';'",
"$$18 :",
"cmd : IF exp $$18 ':' listacmd else ENDIF",
"cmd : while",
"cmd : for",
"cmd : ESCREVA escreva ';'",
"cmd : LEIA leia ';'",
"else : '[' ELSE ':' cmd listacmd ']'",
"else :",
"$$19 :",
"escreva : exp $$19 parametroEscreva",
"leia : exp",
"parametroEscreva : ',' exp",
"parametroEscreva :",
"$$20 :",
"while : WHILE exp $$20 cmd listacmdrep ENDWHILE",
"$$21 :",
"$$22 :",
"for : FOR IDENT '=' exp $$21 ';' exp $$22 ';' exp ':' cmd listacmdrep ENDFOR",
"exp : exp '+' exp",
"exp : exp '*' exp",
"exp : exp '-' exp",
"exp : exp '/' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp AND exp",
"exp : exp OR exp",
"exp : exp LESSEREQUAL exp",
"exp : exp GREATEREQUAL exp",
"exp : TRUE",
"exp : FALSE",
"exp : NUM",
"exp : DOUBLE",
"exp : '(' exp ')'",
"exp : LITERAL",
"exp : IDENT",
"exp : exp '=' exp",
"exp : metodo",
"$$23 :",
"exp : NEW IDENT $$23 '(' parametrosMetodo ')'",
"$$24 :",
"metodo : IDENT '.' IDENT $$24 '(' parametrosMetodo ')'",
"parametrosMetodo : exp lParametrosMetodo",
"parametrosMetodo :",
"lParametrosMetodo : ',' exp lParametrosMetodo",
"lParametrosMetodo :",
};

//#line 317 "exemploSem.y"

  private Yylex lexer;
  private int idTs;
  private String tipoClasse;
  private Object currType;
  private String currEscopo;
  private ClasseID currClass;
  private TS_entry currRetorno;
  private TabSimb ts;
  private int nroAtributos;
  private int nroAtributosParametros;
  private String atribs;
  private String atribsParametros;

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

  	yyparser.listarTS();

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
//#line 759 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 28 "exemploSem.y"
{ currEscopo = "Global"; currClass = ClasseID.VarGlobal;idTs = 0; }
break;
case 3:
//#line 30 "exemploSem.y"
{tipoClasse = (String)val_peek(0).sval;  TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)Tp_CLASS, currEscopo, ClasseID.TipoClasse));
                        }
break;
case 7:
//#line 46 "exemploSem.y"
{currType = val_peek(0).obj;}
break;
case 10:
//#line 50 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(2).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(2).sval + "< jah declarada");
                          else ts.insert(new TS_entry(val_peek(2).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 11:
//#line 57 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 13:
//#line 66 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 14:
//#line 67 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 15:
//#line 68 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 16:
//#line 69 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 17:
//#line 70 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
		 									if(val_peek(0).sval.equals(tipoClasse)){
											    yyval.obj = Tp_CLASS;
											}else{
                        yyval.obj = Tp_ERRO;
												yyerror("(sem) Nome de tipo <" + val_peek(0).sval + "> nao declarado ");
											}
              }
break;
case 18:
//#line 81 "exemploSem.y"
{currClass = ClasseID.VarLocal;}
break;
case 20:
//#line 85 "exemploSem.y"
{nroAtributos = 0;}
break;
case 22:
//#line 89 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval+idTs;idTs++;}
break;
case 23:
//#line 89 "exemploSem.y"
{currRetorno = Tp_INT;
TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                         if (nodo != null && nodo.getEscopo().equals(currEscopo))
                             yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                         else{ ts.insert(new TS_entry(val_peek(4).sval, Tp_INT, nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}}
break;
case 25:
//#line 95 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval+idTs;idTs++;}
break;
case 26:
//#line 95 "exemploSem.y"
{currRetorno = Tp_BOOL ;
   TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(4).sval, Tp_BOOL,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 28:
//#line 101 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval+idTs;idTs++;}
break;
case 29:
//#line 101 "exemploSem.y"
{ currRetorno = Tp_DOUBLE ;
   TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(4).sval, Tp_DOUBLE,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 31:
//#line 107 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval+idTs;idTs++;}
break;
case 32:
//#line 107 "exemploSem.y"
{ currRetorno = Tp_STRING ;
   TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(4).sval, Tp_STRING,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 34:
//#line 113 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval+idTs;idTs++;}
break;
case 35:
//#line 113 "exemploSem.y"
{
                            if(!(val_peek(5).sval.equals(tipoClasse))){
											  yyerror("(sem) Nome de tipo <" + val_peek(5).sval + "> nao declarado ");
											}else{TS_entry nodo = ts.pesquisaMetodo(val_peek(5).sval,nroAtributos,atribs);
                                               if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                                   yyerror("metodo ja declarado >" + val_peek(5).sval + "< jah declarada");
                                               else ts.insert(new TS_entry(val_peek(5).sval, Tp_CLASS,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
              }
break;
case 36:
//#line 123 "exemploSem.y"
{nroAtributos++;atribs = "";}
break;
case 37:
//#line 123 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else{ ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                              atribs+=((TS_entry)val_peek(2).obj).getTipoStrParam() ;}
                        }
break;
case 39:
//#line 132 "exemploSem.y"
{nroAtributos++;}
break;
case 40:
//#line 132 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else {ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                              atribs=atribs+""+((TS_entry)val_peek(2).obj).getTipoStrParam();};
                        }
break;
case 42:
//#line 142 "exemploSem.y"
{ currEscopo = "Main";idTs++;}
break;
case 47:
//#line 150 "exemploSem.y"
{ if (val_peek(0).obj != currRetorno)
  yyerror("Tipo de retorno incorreto"); }
break;
case 55:
//#line 164 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
          yyerror("Expressao deve ser booleana");}
          }
break;
case 63:
//#line 178 "exemploSem.y"
{if (val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser do tipo string");}
      }
break;
case 65:
//#line 183 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
break;
case 66:
//#line 188 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
break;
case 68:
//#line 194 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
      yyerror("Expressao deve ser booleana");}
      }
break;
case 70:
//#line 200 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(2).sval);
                         if (nodo == null)
                            yyerror("(sem) var <" + val_peek(2).sval + "> nao declarada");
                            else{
                              validaTipo(ATRIB, (TS_entry)nodo.getTipo(), (TS_entry)val_peek(0).obj);
                            }
      }
break;
case 71:
//#line 206 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
                          yyerror("Expressao deve ser booleana");}
                          }
break;
case 73:
//#line 213 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 74:
//#line 214 "exemploSem.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 75:
//#line 215 "exemploSem.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 76:
//#line 216 "exemploSem.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 77:
//#line 217 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 78:
//#line 218 "exemploSem.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 79:
//#line 219 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 80:
//#line 220 "exemploSem.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 81:
//#line 221 "exemploSem.y"
{ yyval.obj = validaTipo(LESSEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 82:
//#line 222 "exemploSem.y"
{ yyval.obj = validaTipo(GREATEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 83:
//#line 223 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 84:
//#line 224 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 85:
//#line 225 "exemploSem.y"
{ yyval.obj = Tp_INT;}
break;
case 86:
//#line 226 "exemploSem.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 87:
//#line 227 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 88:
//#line 228 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 89:
//#line 229 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                 if (nodo == null)
	                        yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada");
                      else{
                        if(!(nodo.getEscopo().equals(currEscopo))&& !(nodo.getEscopo().equals("Global"))){
                          yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada");
                          yyval.obj = Tp_ERRO;
                          }
                          else
			                    yyval.obj = nodo.getTipo();
                        }
			            }
break;
case 90:
//#line 241 "exemploSem.y"
{yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 91:
//#line 242 "exemploSem.y"
{yyval.obj = val_peek(0).obj;}
break;
case 92:
//#line 243 "exemploSem.y"
{nroAtributosParametros = 0; atribsParametros="";}
break;
case 93:
//#line 244 "exemploSem.y"
{
         TS_entry n = ts.pesquisaMetodo((String)val_peek(4).sval,nroAtributosParametros,atribsParametros);
         if(n == null){
           yyval.obj = Tp_ERRO;
           yyerror("metodo nao valido");
         }else{
           if(n.getTipoStr().equals("class"))
           yyval.obj = Tp_CLASS;
           else
           yyval.obj = Tp_ERRO;
         }
       }
break;
case 94:
//#line 261 "exemploSem.y"
{nroAtributosParametros = 0; atribsParametros="";}
break;
case 95:
//#line 261 "exemploSem.y"
{
                                                      TS_entry nodo = ts.pesquisa(val_peek(6).sval);
                                                      if(nodo == null){
                                                        yyerror("(sem) var <" + val_peek(6).sval + "> nao declarada");
                                                        yyval.obj = Tp_ERRO;
                                                      }else{
                                                        if(!(nodo.getTipoStr().equals("class"))){
                                                          yyerror("variavel nao é do tipo classe");
                                                          yyval.obj = Tp_ERRO;
                                                        }else{
                                                          TS_entry n = ts.pesquisaMetodo((String)val_peek(4).sval,nroAtributosParametros,atribsParametros);
                                                          if(n == null){
                                                            yyval.obj = Tp_ERRO;
                                                            yyerror("metodo nao valido");
                                                          }else{
                                                            yyval.obj = n.getTipo();
                                                          }
                                                        }

                                                      }
}
break;
case 96:
//#line 284 "exemploSem.y"
{ if(val_peek(1).obj == Tp_STRING)
                                                atribsParametros+="string";
                                                else if(val_peek(1).obj == Tp_BOOL){
                                                atribsParametros+="boolean";
                                                }else if(val_peek(1).obj == Tp_DOUBLE){
                                                atribsParametros+="double";
                                                }else if(val_peek(1).obj == Tp_INT){
                                                atribsParametros+="int";
                                                }else if(val_peek(1).obj == Tp_CLASS){
                                                atribsParametros+="tipo de classe";
                                                }
                                                nroAtributosParametros++;
                                                }
break;
case 98:
//#line 300 "exemploSem.y"
{ if(val_peek(1).obj == Tp_STRING)
                                                atribsParametros+="string";
                                                else if(val_peek(1).obj == Tp_BOOL){
                                                atribsParametros+="boolean";
                                                }else if(val_peek(1).obj == Tp_DOUBLE){
                                                atribsParametros+="double";
                                                }else if(val_peek(1).obj == Tp_INT){
                                                atribsParametros+="int";
                                                }else if(val_peek(1).obj == Tp_CLASS){
                                                atribsParametros+="tipo de classe ";
                                                }
                                                nroAtributosParametros++;
                                                }
break;
//#line 1291 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
