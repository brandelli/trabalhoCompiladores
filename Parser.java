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
  import java.io.*;
//#line 19 "Parser.java"




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
    4,    0,    5,    3,    6,    7,   11,    8,    8,   10,
   12,   12,    1,    1,    1,    1,    1,   14,    9,   13,
   13,   18,   16,   20,   16,   21,   16,   22,   16,   16,
   17,   17,   24,   24,   15,   15,   23,   19,   27,   26,
   25,   25,   29,   29,   29,   28,   30,   28,   28,   28,
   28,   28,   31,   31,   37,   34,   35,   36,   36,   38,
   32,   39,   40,   33,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,   41,   42,   42,   43,   43,
};
final static short yylen[] = {                            2,
    0,    2,    0,    7,    2,    2,    0,    4,    0,    3,
    3,    0,    1,    1,    1,    1,    1,    0,    3,    2,
    0,    0,    8,    0,    8,    0,    8,    0,    8,    5,
    3,    0,    4,    0,    6,    0,    3,    4,    0,    4,
    2,    0,    2,    1,    0,    2,    0,    7,    1,    1,
    3,    3,    6,    0,    0,    3,    1,    2,    0,    0,
    6,    0,    0,   14,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    1,    1,    1,    1,    3,    1,
    1,    3,    1,    6,    2,    0,    3,    0,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    2,    3,    0,    0,    0,    0,   17,
   13,   14,   16,   15,    7,    5,   18,    0,    0,    6,
   21,    4,    0,    0,    0,    0,    0,    8,    0,    0,
    0,    0,    0,    0,   19,   20,    0,   10,    0,    0,
    0,    0,    0,    0,   11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   31,   42,   30,   22,   24,   28,   26,    0,    0,    0,
    0,    0,    0,    0,   35,    0,    0,   77,   78,   75,
   76,   80,    0,    0,    0,    0,    0,   37,    0,    0,
   41,   49,   50,   83,    0,    0,    0,    0,   33,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   46,
   42,   23,   25,   29,   27,    0,    0,    0,    0,    0,
   51,   52,   79,   71,   72,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   42,    0,    0,    0,
   56,    0,    0,    0,    0,    0,   44,    0,    0,    0,
    0,   38,    0,   85,   84,    0,    0,   61,   43,    0,
    0,    0,    0,   48,    0,   40,   87,    0,    0,   42,
    0,    0,    0,   53,    0,    0,    0,   64,
};
final static short yydgoto[] = {                          1,
   15,   90,    4,    2,    6,    9,   18,   16,   20,   24,
   19,   27,   25,   21,   35,   36,   47,   71,  122,   72,
   74,   73,   63,   61,   70,  153,  171,   91,  158,  127,
  167,   92,   93,  105,  107,  151,  130,  128,  159,  179,
   94,  155,  164,
};
final static short yysindex[] = {                         0,
    0, -264, -246,    0,    0, -111, -265, -225, -251,    0,
    0,    0,    0,    0,    0,    0,    0,  -90, -219,    0,
    0,    0,   10, -225,  -54, -196,    3,    0,   23, -192,
 -186, -179, -176, -187,    0,    0,   10,    0, -225,   43,
   44,   45,   46,   48,    0, -162,   61, -225, -225, -225,
 -225,   62,   60,  -18,   65,   70,   71,   72, -225, -225,
    0,    0,    0,    0,    0,    0,    0,  -18, -135,  241,
 -225, -225, -225, -225,    0,   60,   84,    0,    0,    0,
    0,    0,  176,  176, -131,  176,  176,    0,  176,  107,
    0,    0,    0,    0,    9,    9,    9,    9,    0, -123,
  140,  140,   74,  140,   77,  140,   78,   86,  176,  176,
  176,  176,  176,  176,  176,  176,  176,  176,  176,    0,
    0,    0,    0,    0,    0,  103,   87,  516,  176,  100,
    0,    0,    0,    0,    0,  -39,  -39,  140,  -39,  -39,
  -32,  -32, -254, -254,  -40,  176,    0, -136,  140,  176,
    0,  176,   28,  113,  120,  267,    0,  285,  104,  140,
  140,    0,  176,    0,    0, -124, -120,    0,    0,  176,
  105,  113,  112,    0,  140,    0,    0,  516,  119,    0,
  176,  342,  134,    0,  516, -136,  455,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0, -109,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  121, -121,   47,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  121,    0,  143,    0,
    0,    0,    0,    0,    0,    0,    0,  143,  143,  143,
  143,    0,  145,    0,    0,    0,    0,    0,   66,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   66,   66,   66,   66,    0,  145,  -19,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  130,  534,    0,  -43,    0,  131,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  132,
    0,    0,    0,    0,    0,  320,  368,  434,  390,  412,
   56,   80,    8,   32,    0,  152,    0,  476,  138,    0,
    0,    0,    0,  157,    0,  -86,    0,    0,    0,  147,
  150,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  157,    0,    0,  151,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  494,    0,    0,
};
final static short yygindex[] = {                         0,
   59,  729,    0,    0,    0,    0,    0,  -15,    0,    0,
    0,  174,    0,    0,    0,    0,  -31,    0,  -51,    0,
    0,    0,  144,  137, -116,    0,    0,  -98,   29,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   49,
};
final static int YYTABLESIZE=910;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         89,
   55,    9,  119,  116,  145,  117,    3,  118,   28,  119,
    5,    7,  109,    8,  118,   55,   55,   56,   57,   58,
   81,   81,   81,   81,   81,   81,  110,   81,   17,  148,
  156,   10,   11,   12,   22,   13,   14,   23,   81,   81,
   81,   81,   81,   68,  123,  124,  125,   68,   68,   68,
   68,   68,   68,   26,   68,   95,   96,   97,   98,  169,
   37,   38,   39,  182,   40,   68,   68,   68,   68,   68,
   41,   66,   66,   66,   66,   66,   66,   42,   66,  180,
   43,   44,   48,   49,   50,   51,  186,   52,  169,   66,
   66,   66,   66,   66,   53,   65,   65,   46,   65,   65,
   65,   54,   59,   60,   62,   64,   46,   46,   46,   46,
   65,   66,   67,   65,   65,   65,   65,   65,   69,   67,
   67,   76,   67,   67,   67,  103,  133,  119,  116,  100,
  117,  121,  118,  126,  129,  131,  132,   67,   67,   67,
   67,   67,  146,  150,  147,  115,  113,  114,  119,  116,
  157,  117,  162,  118,  119,  116,  163,  117,    9,  118,
  165,  173,  170,  176,  174,  120,  115,  113,  114,  178,
    9,   36,  115,  113,  114,  119,  116,  181,  117,   12,
  118,  119,  116,   32,  117,   34,  118,   47,    9,   57,
   59,  185,   86,  115,  113,  114,   62,   88,   54,  115,
  113,  114,   29,   30,   31,   58,   32,   33,   39,   63,
   45,   75,   99,   34,  187,   89,   77,    0,    0,   78,
  177,   79,    0,   80,   81,   82,    0,  109,    0,   83,
    0,   84,    0,   85,  109,   86,   87,   81,    0,    0,
   81,  110,   81,  152,   81,   81,   81,   81,  110,    0,
   81,    0,   81,    0,   81,    0,   81,   81,    0,    0,
    0,   81,   81,   81,   68,    0,    0,   68,    0,   68,
    0,   68,   68,   68,    0,    0,    0,   68,    0,   68,
   89,   68,    0,   68,   68,    0,    0,    0,   66,   68,
   68,   66,    0,   66,    0,   66,   66,   66,    0,    0,
    0,   66,    0,   66,    0,   66,   89,   66,   66,    0,
    0,    0,   65,   66,   66,   65,    0,   65,    0,   65,
   65,   65,    0,    0,   89,   65,    0,   65,    0,   65,
    0,   65,   65,    0,    0,    0,   67,   65,   65,   67,
    0,   67,    0,   67,   67,   67,    0,    0,    0,   67,
    0,   67,  109,   67,    0,   67,   67,  166,    0,   74,
   74,   67,   67,   74,    0,   88,  110,  111,  112,    0,
    0,    0,    0,  109,    0,    0,    0,   74,   74,  109,
   74,   89,    0,    0,    0,    0,    0,  110,  111,  112,
    0,    0,    0,  110,  111,  112,    0,    0,    0,    0,
  109,    0,    0,    0,    0,    0,  109,   73,   73,    0,
    0,   73,    0,    0,  110,  111,  112,    0,    0,    0,
  110,  111,  112,    0,    0,   73,   73,    0,   73,   69,
   69,    0,   77,   69,  184,   78,    0,   79,    0,   80,
   81,   82,    0,    0,    0,    0,    0,   69,   69,    0,
   69,   70,   70,    0,    0,   70,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   70,
   70,    0,   70,   82,   82,    0,    0,   82,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   82,   82,    0,   89,    0,    0,   77,    0,    0,
   78,    0,   79,    0,   80,   81,   82,    0,    0,    0,
   83,    0,   84,    0,   85,   45,   86,   87,    0,    0,
    0,    0,    0,   77,    0,    0,   78,    0,   79,    0,
   80,   81,   82,   45,    0,    0,   83,    0,   84,    0,
   85,   77,   86,   87,   78,    0,   79,    0,   80,   81,
   82,    0,    0,    0,   83,   89,   84,  168,   85,    0,
   86,   87,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   60,    0,    0,   74,    0,    0,   74,
    0,   74,    0,   74,   74,   74,    0,    0,    0,   74,
    0,   74,    0,   74,    0,   74,   74,    0,   77,    0,
    0,   78,    0,   79,    0,   80,   81,   82,    0,    0,
    0,   83,    0,   84,    0,   85,    0,   86,   87,    0,
    0,    0,    0,    0,   73,    0,    0,   73,    0,   73,
    0,   73,   73,   73,    0,    0,    0,   73,    0,   73,
    0,   73,    0,   73,   73,    0,   69,    0,    0,   69,
    0,   69,    0,   69,   69,   69,    0,    0,    0,   69,
    0,   69,    0,   69,    0,   69,   69,    0,   70,    0,
    0,   70,    0,   70,    0,   70,   70,   70,    0,    0,
    0,   70,    0,   70,    0,   70,    0,   70,   70,    0,
   82,    0,    0,   82,    0,   82,    0,   82,   82,   82,
    0,    0,    0,   82,    0,   82,    0,   82,    0,   82,
   82,   77,    0,    0,   78,    0,   79,    0,   80,   81,
   82,    0,    0,    0,   83,    0,   84,    0,   85,  188,
   86,   87,   45,    0,    0,   45,    0,   45,    0,   45,
   45,   45,    0,    0,    0,   45,    0,   45,   45,   45,
   45,   45,   45,   45,    0,   45,    0,   45,   45,   45,
    0,    0,    0,   45,    0,   45,    0,   45,   45,   45,
   45,    0,   77,    0,    0,   78,    0,   79,    0,   80,
   81,   82,    0,    0,    0,   83,    0,   84,    0,   85,
   60,   86,   87,   60,    0,   60,    0,   60,   60,   60,
    0,    0,    0,   60,    0,   60,    0,   60,    0,   60,
   60,  101,  102,    0,  104,  106,    0,  108,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  134,  135,  136,
  137,  138,  139,  140,  141,  142,  143,  144,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  149,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  154,    0,    0,    0,  160,    0,
  161,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  172,    0,    0,    0,    0,    0,    0,  175,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  183,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   44,  123,   42,   43,  121,   45,  271,   47,   24,   42,
  257,  123,  267,  279,   47,   59,   48,   49,   50,   51,
   40,   41,   42,   43,   44,   45,  281,   47,  280,  128,
  147,  257,  258,  259,  125,  261,  262,  257,   58,   59,
   60,   61,   62,   59,   96,   97,   98,   40,   41,   42,
   43,   44,   45,   44,   47,   71,   72,   73,   74,  158,
  257,   59,   40,  180,  257,   58,   59,   60,   61,   62,
  257,   40,   41,   42,   43,   44,   45,  257,   47,  178,
  257,  269,   40,   40,   40,   40,  185,   40,  187,   58,
   59,   60,   61,   62,  257,   40,   41,   39,   43,   44,
   45,   41,   41,   44,  123,   41,   48,   49,   50,   51,
   41,   41,   41,   58,   59,   60,   61,   62,   60,   40,
   41,  257,   43,   44,   45,  257,   41,   42,   43,   46,
   45,  123,   47,  257,   61,   59,   59,   58,   59,   60,
   61,   62,   40,   44,   58,   60,   61,   62,   42,   43,
  287,   45,  125,   47,   42,   43,   44,   45,  280,   47,
   41,  286,   59,   59,  285,   59,   60,   61,   62,   58,
  280,  125,   60,   61,   62,   42,   43,   59,   45,   59,
   47,   42,   43,   41,   45,   41,   47,   58,  123,   59,
   59,   58,   41,   60,   61,   62,   59,   41,  285,   60,
   61,   62,  257,  258,  259,   59,  261,  262,   59,   59,
   37,   68,   76,  268,  186,   40,  257,   -1,   -1,  260,
  172,  262,   -1,  264,  265,  266,   -1,  267,   -1,  270,
   -1,  272,   -1,  274,  267,  276,  277,  257,   -1,   -1,
  260,  281,  262,  284,  264,  265,  266,  267,  281,   -1,
  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,   -1,
   -1,  281,  282,  283,  257,   -1,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
   40,  274,   -1,  276,  277,   -1,   -1,   -1,  257,  282,
  283,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   40,  276,  277,   -1,
   -1,   -1,  257,  282,  283,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   40,  270,   -1,  272,   -1,  274,
   -1,  276,  277,   -1,   -1,   -1,  257,  282,  283,  260,
   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,  267,  274,   -1,  276,  277,   91,   -1,   40,
   41,  282,  283,   44,   -1,  125,  281,  282,  283,   -1,
   -1,   -1,   -1,  267,   -1,   -1,   -1,   58,   59,  267,
   61,   40,   -1,   -1,   -1,   -1,   -1,  281,  282,  283,
   -1,   -1,   -1,  281,  282,  283,   -1,   -1,   -1,   -1,
  267,   -1,   -1,   -1,   -1,   -1,  267,   40,   41,   -1,
   -1,   44,   -1,   -1,  281,  282,  283,   -1,   -1,   -1,
  281,  282,  283,   -1,   -1,   58,   59,   -1,   61,   40,
   41,   -1,  257,   44,   93,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,
   61,   40,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   -1,   61,   40,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   -1,   40,   -1,   -1,  257,   -1,   -1,
  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,
  270,   -1,  272,   -1,  274,   40,  276,  277,   -1,   -1,
   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,
  264,  265,  266,   40,   -1,   -1,  270,   -1,  272,   -1,
  274,  257,  276,  277,  260,   -1,  262,   -1,  264,  265,
  266,   -1,   -1,   -1,  270,   40,  272,  273,  274,   -1,
  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   -1,  257,   -1,   -1,  260,
   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,   -1,  274,   -1,  276,  277,   -1,  257,   -1,
   -1,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,
   -1,   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
   -1,  274,   -1,  276,  277,   -1,  257,   -1,   -1,  260,
   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,   -1,  274,   -1,  276,  277,   -1,  257,   -1,
   -1,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,
  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,
  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,  275,
  276,  277,  257,   -1,   -1,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,  273,  274,
  257,  276,  277,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,  275,  276,
  277,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,
  257,  276,  277,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,   83,   84,   -1,   86,   87,   -1,   89,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  109,  110,  111,
  112,  113,  114,  115,  116,  117,  118,  119,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  129,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  146,   -1,   -1,   -1,  150,   -1,
  152,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  163,   -1,   -1,   -1,   -1,   -1,   -1,  170,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  181,
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
"bl : INT IDENT '(' parametros ')' $$5 dList bloco",
"$$6 :",
"bl : BOOL IDENT '(' parametros ')' $$6 dList bloco",
"$$7 :",
"bl : DOUBLE IDENT '(' parametros ')' $$7 dList bloco",
"$$8 :",
"bl : STRING IDENT '(' parametros ')' $$8 dList bloco",
"bl : IDENT '(' parametros ')' blocoConstrutor",
"parametros : type IDENT lParametros",
"parametros :",
"lParametros : ',' type IDENT lParametros",
"lParametros :",
"main : VOID MAIN '(' ')' dList blocoConstrutor",
"main :",
"blocoConstrutor : '{' listacmd '}'",
"bloco : '{' listacmd retorno '}'",
"$$9 :",
"retorno : RETORNO exp $$9 ';'",
"listacmd : listacmd cmd",
"listacmd :",
"listacmdrep : listacmdrep cmd",
"listacmdrep : BREAK",
"listacmdrep :",
"cmd : exp ';'",
"$$10 :",
"cmd : IF exp $$10 ':' listacmd else ENDIF",
"cmd : while",
"cmd : for",
"cmd : ESCREVA escreva ';'",
"cmd : LEIA leia ';'",
"else : '[' ELSE ':' cmd listacmd ']'",
"else :",
"$$11 :",
"escreva : exp $$11 parametroEscreva",
"leia : exp",
"parametroEscreva : ',' exp",
"parametroEscreva :",
"$$12 :",
"while : WHILE exp $$12 cmd listacmdrep ENDWHILE",
"$$13 :",
"$$14 :",
"for : FOR IDENT '=' exp $$13 ';' exp $$14 ';' exp ':' cmd listacmdrep ENDFOR",
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
"metodo : IDENT '.' IDENT '(' parametrosMetodo ')'",
"parametrosMetodo : exp lParametrosMetodo",
"parametrosMetodo :",
"lParametrosMetodo : ',' exp lParametrosMetodo",
"lParametrosMetodo :",
};

//#line 243 "exemploSem.y"

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
//#line 728 "Parser.java"
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
//#line 25 "exemploSem.y"
{ currEscopo = "Global"; currClass = ClasseID.VarGlobal; }
break;
case 3:
//#line 27 "exemploSem.y"
{tipoClasse = (String)val_peek(0).sval;  TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)Tp_CLASS, currEscopo, ClasseID.TipoClasse));
                        }
break;
case 7:
//#line 43 "exemploSem.y"
{currType = val_peek(0).obj;}
break;
case 10:
//#line 47 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(2).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(2).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(2).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 11:
//#line 55 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 13:
//#line 64 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 14:
//#line 65 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 15:
//#line 66 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 16:
//#line 67 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 17:
//#line 68 "exemploSem.y"
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
//#line 79 "exemploSem.y"
{currClass = ClasseID.VarLocal;}
break;
case 22:
//#line 87 "exemploSem.y"
{currRetorno = Tp_INT; currEscopo = (String)val_peek(3).sval;
TS_entry nodo = ts.pesquisa(val_peek(3).sval);
                         if (nodo != null && nodo.getEscopo().equals(currEscopo))
                             yyerror("metodo ja declarado >" + val_peek(3).sval + "< jah declarada");

                         else ts.insert(new TS_entry(val_peek(3).sval, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));}
break;
case 24:
//#line 93 "exemploSem.y"
{currRetorno = Tp_BOOL ; currEscopo = (String)val_peek(3).sval;
   TS_entry nodo = ts.pesquisa(val_peek(3).sval);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(3).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(3).sval, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));}
break;
case 26:
//#line 99 "exemploSem.y"
{ currRetorno = Tp_DOUBLE ;currEscopo = (String)val_peek(3).sval;
   TS_entry nodo = ts.pesquisa(val_peek(3).sval);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(3).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(3).sval, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));}
break;
case 28:
//#line 105 "exemploSem.y"
{ currRetorno = Tp_STRING ;currEscopo = (String)val_peek(3).sval;
   TS_entry nodo = ts.pesquisa(val_peek(3).sval);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(3).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(3).sval, (TS_entry)currType, currEscopo, ClasseID.NomeFuncao));}
break;
case 30:
//#line 111 "exemploSem.y"
{         if(!(val_peek(4).sval.equals(tipoClasse))){
											  yyerror("(sem) Nome de tipo <" + val_peek(4).sval + "> nao declarado ");
											}
              }
break;
case 31:
//#line 117 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null)
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                        }
break;
case 33:
//#line 125 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null)
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                        }
break;
case 39:
//#line 142 "exemploSem.y"
{ if (val_peek(0).obj != currRetorno)
  yyerror("Tipo de retorno incorreto"); }
break;
case 47:
//#line 156 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
          yyerror("Expressao deve ser booleana");}
          }
break;
case 55:
//#line 170 "exemploSem.y"
{if (val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser do tipo string");}
      }
break;
case 57:
//#line 175 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
break;
case 58:
//#line 180 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
break;
case 60:
//#line 186 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
      yyerror("Expressao deve ser booleana");}
      }
break;
case 62:
//#line 192 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(2).sval);
                         if (nodo == null)
                            yyerror("(sem) var <" + val_peek(2).sval + "> nao declarada");
                            else{
                              validaTipo(ATRIB, (TS_entry)nodo.getTipo(), (TS_entry)val_peek(0).obj);
                            }
      }
break;
case 63:
//#line 198 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
                          yyerror("Expressao deve ser booleana");}
                          }
break;
case 65:
//#line 205 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 66:
//#line 206 "exemploSem.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 67:
//#line 207 "exemploSem.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 68:
//#line 208 "exemploSem.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 69:
//#line 209 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 70:
//#line 210 "exemploSem.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 71:
//#line 211 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 72:
//#line 212 "exemploSem.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 73:
//#line 213 "exemploSem.y"
{ yyval.obj = validaTipo(LESSEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 74:
//#line 214 "exemploSem.y"
{ yyval.obj = validaTipo(GREATEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 75:
//#line 215 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 76:
//#line 216 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 77:
//#line 217 "exemploSem.y"
{ yyval.obj = Tp_INT;}
break;
case 78:
//#line 218 "exemploSem.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 79:
//#line 219 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 80:
//#line 220 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 81:
//#line 221 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                 if (nodo == null)
	                        yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada");
                      else
			                    yyval.obj = nodo.getTipo();
			            }
break;
case 82:
//#line 227 "exemploSem.y"
{yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
//#line 1130 "Parser.java"
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
