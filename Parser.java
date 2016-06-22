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
   13,   16,   16,   16,   16,   16,   17,   17,   20,   20,
   15,   15,   19,   18,   22,   21,   21,   24,   24,   24,
   23,   25,   23,   23,   23,   23,   23,   26,   26,   29,
   30,   31,   31,   32,   27,   28,   33,   33,   33,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,   34,
   35,   35,   36,   36,
};
final static short yylen[] = {                            2,
    0,    2,    0,    7,    2,    2,    0,    4,    0,    3,
    3,    0,    1,    1,    1,    1,    1,    0,    3,    2,
    0,    7,    7,    7,    7,    6,    3,    0,    4,    0,
    6,    0,    3,    4,    3,    2,    0,    2,    1,    0,
    2,    0,    7,    1,    1,    3,    3,    6,    0,    2,
    1,    2,    0,    0,    6,   10,    4,    4,    4,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    1,
    1,    1,    1,    3,    1,    1,    4,    3,    1,    6,
    2,    0,    3,    0,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    2,    3,    0,    0,    0,    0,   17,
   13,   14,   16,   15,    7,    5,   18,    0,    0,    6,
   21,    4,    0,    0,    0,    0,    0,    8,    0,    0,
    0,    0,    0,    0,   19,   20,    0,   10,    0,    0,
    0,    0,    0,    0,   11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   27,    0,    0,    0,    0,    0,    0,    0,   37,   26,
    0,    0,    0,    0,   31,    0,    0,   37,   22,   23,
   25,   24,   29,    0,   72,   73,   70,   71,   75,    0,
    0,    0,    0,    0,   33,    0,    0,   36,   44,   45,
   79,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   41,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   50,   46,   47,   74,   66,   67,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,   77,    0,   37,    0,    0,    0,    0,   35,    0,
    0,    0,   39,    0,   57,   58,   59,    0,    0,   81,
   80,    0,    0,   55,   38,    0,    0,    0,   43,    0,
   83,    0,    0,   37,    0,    0,   56,   48,
};
final static short yydgoto[] = {                          1,
   15,   97,    4,    2,    6,    9,   18,   16,   20,   24,
   19,   27,   25,   21,   35,   36,   47,   79,   70,   61,
   77,  127,   98,  164,  130,  173,   99,  100,  110,  112,
  135,  131,  108,  101,  161,  170,
};
final static short yysindex[] = {                         0,
    0, -262, -243,    0,    0,  -91, -241, -228, -235,    0,
    0,    0,    0,    0,    0,    0,    0,  -78, -203,    0,
    0,    0,   12, -228, -170, -200,   -1,    0,   19, -196,
 -194, -193, -186, -197,    0,    0,   12,    0, -228,   33,
   43,   44,   45,   46,    0, -167,   64, -228, -228, -228,
 -228,   65,   63, -228,   67,   68,   69,   71, -228, -228,
    0,  -10, -228, -228, -228, -228,  -10, -143,    0,    0,
   -8,   -8,   -8,   -8,    0,   63,  326,    0,    0,    0,
    0,    0,    0,  -45,    0,    0,    0,    0,    0,  219,
  219, -228,  219,  219,    0,  219,  115,    0,    0,    0,
    0,  -40,  219, -141,  402,  402, -135,   66,  121,   73,
  402,   74,   94,  219,  219,  219,  219,  219,  219,  219,
  219,  219,  219,  219,    0,  219,   -2,  142,   84,   76,
  578,   77,  219,  219,    0,    0,    0,    0,    0,    0,
  -39,  -39,  402,  -39,  -39,  -27,  -27, -254, -254,  148,
    0,    0,  219,    0, -147, -250,  154,  402,    0,  396,
  101,  374,    0,  268,    0,    0,    0,  219,  219,    0,
    0, -137, -142,    0,    0,  312,  396,   92,    0,  578,
    0,  578, -147,    0,  483,  514,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0, -129,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  102, -121,   28,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  102,    0,  126,    0,
    0,    0,    0,    0,    0,    0,    0,  126,  126,  126,
  126,    0,  128,   47,    0,    0,    0,    0,   47,    0,
    0,    0,   47,   47,   47,   47,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  128,    0,    0,    0,    0,
    0,    0,    0,  -19,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  113,  601,    0,    0,  114,    0,
  119,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  235,  286,  462,  349,  429,   59,   86,    8,   35,    0,
    0,    0,  131,    0,  532,    0,    0,  127,    0,  139,
    0,  -97,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  139,    0,    0,    0,
    0,    0,  557,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  518,  766,    0,    0,    0,    0,    0,  511,    0,    0,
    0,  155,    0,    0,    0,    0,  -32,  -37,  133,  118,
  -73,    0, -120,   15,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   29,
};
final static int YYTABLESIZE=935;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         96,
  104,    9,  124,  121,  102,  122,  165,  123,    3,  166,
  155,  167,  114,    5,  124,   55,   56,   57,   58,  123,
   76,   76,   76,   76,   76,   76,  115,   76,   10,   11,
   12,    7,   13,   14,   80,   81,   82,    8,   76,   76,
   76,   76,   76,  175,   17,  103,   22,   63,   63,   63,
   63,   63,   63,   23,   63,   26,   37,   38,   39,  183,
   40,  184,   41,   42,  175,   63,   63,   63,   63,   63,
   43,   44,   48,   76,   61,   61,   61,   61,   61,   61,
  162,   61,   49,   50,   51,   52,   29,   30,   31,   53,
   32,   33,   61,   61,   61,   61,   61,   34,   60,   60,
   63,   60,   60,   60,   54,   59,   60,   63,   64,   65,
  186,   66,   69,   76,   78,  129,   60,   60,   60,   60,
   60,  132,  151,  153,  133,   62,   62,   61,   62,   62,
   62,  136,  137,  154,  138,  124,  121,  156,  122,  163,
  123,  171,  179,   62,   62,   62,   62,   62,  178,  182,
    9,   60,   32,  120,  118,  119,  124,  121,    9,  122,
   12,  123,  124,  121,  134,  122,   28,  123,   30,    9,
   42,   82,   53,  125,  120,  118,  119,   51,   62,   84,
  120,  118,  119,  124,  121,   52,  122,   49,  123,  124,
  121,   45,  122,   83,  123,  124,  121,  185,  122,   75,
  123,  120,  118,  119,    0,  181,  159,  120,  118,  119,
    0,    0,  168,  120,  118,  119,   84,    0,    0,   85,
    0,   86,    0,   87,   88,   89,    0,  114,    0,   90,
    0,   91,    0,   92,  152,   93,   94,   76,    0,  114,
   76,  115,   76,  126,   76,   76,   76,   76,    0,    0,
   76,    0,   76,  115,   76,    0,   76,   76,   96,    0,
    0,   76,   76,   76,   63,    0,    0,   63,    0,   63,
    0,   63,   63,   63,   69,   69,    0,   63,   69,   63,
    0,   63,    0,   63,   63,    0,    0,    0,    0,   63,
   63,   61,   69,   69,   61,   69,   61,    0,   61,   61,
   61,    0,    0,    0,   61,    0,   61,   96,   61,    0,
   61,   61,    0,    0,    0,   60,   61,   61,   60,    0,
   60,    0,   60,   60,   60,   68,   68,   69,   60,   68,
   60,    0,   60,    0,   60,   60,    0,    0,    0,    0,
   60,   60,   62,   68,   68,   62,   68,   62,    0,   62,
   62,   62,    0,  124,  121,   62,  122,   62,  123,   62,
  114,   62,   62,    0,    0,   96,    0,   62,   62,  180,
    0,  120,  118,  119,  115,  116,  117,    0,   68,    0,
    0,  114,    0,    0,    0,    0,    0,  114,   64,   64,
    0,    0,   64,    0,    0,  115,  116,  117,    0,    0,
    0,  115,  116,  117,    0,    0,   64,   64,  114,   64,
    0,    0,    0,   96,  114,    0,    0,    0,    0,    0,
  114,    0,  115,  116,  117,    0,    0,    0,  115,  116,
  117,    0,    0,    0,  115,  116,  117,  124,  121,  169,
  122,   64,  123,  124,  121,    0,  122,    0,  123,    0,
   95,    0,    0,    0,    0,  120,  118,  119,    0,    0,
    0,  120,  118,  119,  172,    0,    0,    0,   65,   65,
    0,    0,   65,    0,    0,   84,    0,    0,   85,    0,
   86,    0,   87,   88,   89,    0,   65,   65,    0,   65,
    0,   69,    0,    0,   69,    0,   69,    0,   69,   69,
   69,   78,   78,    0,   69,   78,   69,    0,   69,    0,
   69,   69,    0,    0,    0,    0,    0,    0,    0,   78,
   78,   65,   96,    0,   84,    0,    0,   85,    0,   86,
    0,   87,   88,   89,   28,    0,    0,   90,    0,   91,
  174,   92,   68,   93,   94,   68,    0,   68,    0,   68,
   68,   68,    0,   96,   78,   68,   46,   68,    0,   68,
    0,   68,   68,    0,   62,   46,   46,   46,   46,   67,
    0,   40,    0,   71,   72,   73,   74,   68,  114,    0,
    0,    0,   84,    0,    0,   85,    0,   86,    0,   87,
   88,   89,  115,  116,  117,   90,   40,   91,    0,   92,
    0,   93,   94,    0,    0,   64,  188,    0,   64,  107,
   64,    0,   64,   64,   64,    0,    0,   96,   64,    0,
   64,    0,   64,    0,   64,   64,    0,    0,    0,    0,
   84,    0,    0,   85,    0,   86,    0,   87,   88,   89,
   54,    0,    0,   90,    0,   91,    0,   92,    0,   93,
   94,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  114,    0,    0,    0,    0,    0,  114,    0,
    0,    0,    0,    0,    0,    0,  115,  116,  117,    0,
    0,    0,  115,  116,  117,   65,    0,    0,   65,    0,
   65,    0,   65,   65,   65,    0,    0,    0,   65,    0,
   65,    0,   65,    0,   65,   65,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   78,    0,
    0,   78,    0,   78,    0,   78,   78,   78,    0,    0,
    0,   78,    0,   78,    0,   78,    0,   78,   78,   84,
    0,    0,   85,    0,   86,    0,   87,   88,   89,    0,
    0,    0,   90,    0,   91,    0,   92,  187,   93,   94,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   84,    0,    0,   85,    0,   86,    0,   87,   88,   89,
    0,    0,    0,   90,    0,   91,    0,   92,   40,   93,
   94,   40,    0,   40,    0,   40,   40,   40,    0,    0,
    0,   40,    0,   40,   40,   40,    0,   40,   40,    0,
    0,    0,    0,   40,    0,    0,   40,    0,   40,    0,
   40,   40,   40,    0,    0,    0,   40,    0,   40,    0,
   40,   40,   40,   40,   84,    0,    0,   85,    0,   86,
    0,   87,   88,   89,    0,    0,    0,   90,    0,   91,
    0,   92,    0,   93,   94,  105,  106,   54,  109,  111,
   54,  113,   54,    0,   54,   54,   54,    0,  128,    0,
   54,    0,   54,    0,   54,    0,   54,   54,    0,  139,
  140,  141,  142,  143,  144,  145,  146,  147,  148,  149,
    0,  150,    0,    0,    0,    0,    0,    0,  157,  158,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  160,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  176,  177,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   46,  123,   42,   43,   78,   45,  257,   47,  271,  260,
  131,  262,  267,  257,   42,   48,   49,   50,   51,   47,
   40,   41,   42,   43,   44,   45,  281,   47,  257,  258,
  259,  123,  261,  262,   72,   73,   74,  279,   58,   59,
   60,   61,   62,  164,  280,   91,  125,   40,   41,   42,
   43,   44,   45,  257,   47,   44,  257,   59,   40,  180,
  257,  182,  257,  257,  185,   58,   59,   60,   61,   62,
  257,  269,   40,   93,   40,   41,   42,   43,   44,   45,
  154,   47,   40,   40,   40,   40,  257,  258,  259,  257,
  261,  262,   58,   59,   60,   61,   62,  268,   40,   41,
   93,   43,   44,   45,   41,   41,   44,   41,   41,   41,
  184,   41,  123,  257,  123,  257,   58,   59,   60,   61,
   62,  257,  125,   40,   59,   40,   41,   93,   43,   44,
   45,   59,   59,   58,   41,   42,   43,   61,   45,  287,
   47,   41,  285,   58,   59,   60,   61,   62,  286,   58,
  280,   93,  125,   60,   61,   62,   42,   43,  280,   45,
   59,   47,   42,   43,   44,   45,   41,   47,   41,  123,
   58,   41,   59,   59,   60,   61,   62,   59,   93,   41,
   60,   61,   62,   42,   43,   59,   45,  285,   47,   42,
   43,   37,   45,   76,   47,   42,   43,  183,   45,   67,
   47,   60,   61,   62,   -1,  177,   59,   60,   61,   62,
   -1,   -1,   59,   60,   61,   62,  257,   -1,   -1,  260,
   -1,  262,   -1,  264,  265,  266,   -1,  267,   -1,  270,
   -1,  272,   -1,  274,   93,  276,  277,  257,   -1,  267,
  260,  281,  262,  284,  264,  265,  266,  267,   -1,   -1,
  270,   -1,  272,  281,  274,   -1,  276,  277,   40,   -1,
   -1,  281,  282,  283,  257,   -1,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   40,   41,   -1,  270,   44,  272,
   -1,  274,   -1,  276,  277,   -1,   -1,   -1,   -1,  282,
  283,  257,   58,   59,  260,   61,  262,   -1,  264,  265,
  266,   -1,   -1,   -1,  270,   -1,  272,   40,  274,   -1,
  276,  277,   -1,   -1,   -1,  257,  282,  283,  260,   -1,
  262,   -1,  264,  265,  266,   40,   41,   93,  270,   44,
  272,   -1,  274,   -1,  276,  277,   -1,   -1,   -1,   -1,
  282,  283,  257,   58,   59,  260,   61,  262,   -1,  264,
  265,  266,   -1,   42,   43,  270,   45,  272,   47,  274,
  267,  276,  277,   -1,   -1,   40,   -1,  282,  283,   58,
   -1,   60,   61,   62,  281,  282,  283,   -1,   93,   -1,
   -1,  267,   -1,   -1,   -1,   -1,   -1,  267,   40,   41,
   -1,   -1,   44,   -1,   -1,  281,  282,  283,   -1,   -1,
   -1,  281,  282,  283,   -1,   -1,   58,   59,  267,   61,
   -1,   -1,   -1,   40,  267,   -1,   -1,   -1,   -1,   -1,
  267,   -1,  281,  282,  283,   -1,   -1,   -1,  281,  282,
  283,   -1,   -1,   -1,  281,  282,  283,   42,   43,   44,
   45,   93,   47,   42,   43,   -1,   45,   -1,   47,   -1,
  125,   -1,   -1,   -1,   -1,   60,   61,   62,   -1,   -1,
   -1,   60,   61,   62,   91,   -1,   -1,   -1,   40,   41,
   -1,   -1,   44,   -1,   -1,  257,   -1,   -1,  260,   -1,
  262,   -1,  264,  265,  266,   -1,   58,   59,   -1,   61,
   -1,  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,
  266,   40,   41,   -1,  270,   44,  272,   -1,  274,   -1,
  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   93,   40,   -1,  257,   -1,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   24,   -1,   -1,  270,   -1,  272,
  273,  274,  257,  276,  277,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   40,   93,  270,   39,  272,   -1,  274,
   -1,  276,  277,   -1,   54,   48,   49,   50,   51,   59,
   -1,   40,   -1,   63,   64,   65,   66,   60,  267,   -1,
   -1,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,  264,
  265,  266,  281,  282,  283,  270,   40,  272,   -1,  274,
   -1,  276,  277,   -1,   -1,  257,   93,   -1,  260,   92,
  262,   -1,  264,  265,  266,   -1,   -1,   40,  270,   -1,
  272,   -1,  274,   -1,  276,  277,   -1,   -1,   -1,   -1,
  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,  266,
   40,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  267,   -1,   -1,   -1,   -1,   -1,  267,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  281,  282,  283,   -1,
   -1,   -1,  281,  282,  283,  257,   -1,   -1,  260,   -1,
  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,   -1,
  272,   -1,  274,   -1,  276,  277,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,
   -1,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,  257,
   -1,   -1,  260,   -1,  262,   -1,  264,  265,  266,   -1,
   -1,   -1,  270,   -1,  272,   -1,  274,  275,  276,  277,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,  257,  276,
  277,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,  273,  274,   -1,  276,  277,   -1,
   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,
  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,
  274,  275,  276,  277,  257,   -1,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
   -1,  274,   -1,  276,  277,   90,   91,  257,   93,   94,
  260,   96,  262,   -1,  264,  265,  266,   -1,  103,   -1,
  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,  114,
  115,  116,  117,  118,  119,  120,  121,  122,  123,  124,
   -1,  126,   -1,   -1,   -1,   -1,   -1,   -1,  133,  134,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  153,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  168,  169,
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
"bl : INT IDENT '(' parametros ')' dList bloco",
"bl : BOOL IDENT '(' parametros ')' dList bloco",
"bl : DOUBLE IDENT '(' parametros ')' dList bloco",
"bl : STRING IDENT '(' parametros ')' dList bloco",
"bl : IDENT '(' parametros ')' dList blocoConstrutor",
"parametros : type IDENT lParametros",
"parametros :",
"lParametros : ',' type IDENT lParametros",
"lParametros :",
"main : VOID MAIN '(' ')' dList blocoConstrutor",
"main :",
"blocoConstrutor : '{' listacmd '}'",
"bloco : '{' listacmd retorno '}'",
"retorno : RETORNO exp ';'",
"listacmd : listacmd cmd",
"listacmd :",
"listacmdrep : listacmdrep cmd",
"listacmdrep : BREAK",
"listacmdrep :",
"cmd : exp ';'",
"$$5 :",
"cmd : IF exp $$5 ':' listacmd else ENDIF",
"cmd : while",
"cmd : for",
"cmd : ESCREVA escreva ';'",
"cmd : LEIA leia ';'",
"else : '[' ELSE ':' cmd listacmd ']'",
"else :",
"escreva : exp parametroEscreva",
"leia : exp",
"parametroEscreva : ',' exp",
"parametroEscreva :",
"$$6 :",
"while : WHILE exp $$6 cmd listacmdrep ENDWHILE",
"for : FOR atribuicaoFor ';' exp ';' exp ':' cmd listacmdrep ENDFOR",
"atribuicaoFor : type IDENT '=' IDENT",
"atribuicaoFor : type IDENT '=' NUM",
"atribuicaoFor : type IDENT '=' DOUBLE",
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
"exp : IDENT '[' exp ']'",
"exp : exp '=' exp",
"exp : metodo",
"metodo : IDENT '.' IDENT '(' parametrosMetodo ')'",
"parametrosMetodo : exp lParametrosMetodo",
"parametrosMetodo :",
"lParametrosMetodo : ',' exp lParametrosMetodo",
"lParametrosMetodo :",
};

//#line 213 "exemploSem.y"

  private Yylex lexer;

  private TabSimb ts;

  public static TS_entry Tp_INT =  new TS_entry("int", null, "", ClasseID.TipoBase);
	public static TS_entry Tp_DOUBLE =  new TS_entry("double", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_STRING = new TS_entry("string", null, "", ClasseID.TipoBase);
	public static TS_entry Tp_CLASS = new TS_entry("classe", null, "", ClasseID.TipoClasse);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null, "", ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

	private String tipoClasse;
	private Object currType;
  private String currEscopo;
  private ClasseID currClass;

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
//#line 727 "Parser.java"
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
{ currEscopo = ""; currClass = ClasseID.VarGlobal; }
break;
case 3:
//#line 27 "exemploSem.y"
{tipoClasse = (String)val_peek(0).sval;}
break;
case 7:
//#line 38 "exemploSem.y"
{currType = val_peek(0).obj;}
break;
case 10:
//#line 42 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(2).sval);
    	                    if (nodo != null)
                              yyerror("(sem) variavel >" + val_peek(2).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(2).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 11:
//#line 50 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    if (nodo != null)
                              yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 13:
//#line 58 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 14:
//#line 59 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 15:
//#line 60 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 16:
//#line 61 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 17:
//#line 62 "exemploSem.y"
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
//#line 73 "exemploSem.y"
{currClass = ClasseID.VarLocal;}
break;
case 26:
//#line 85 "exemploSem.y"
{         if(!(val_peek(5).sval.equals(tipoClasse))){
											  yyerror("(sem) Nome de tipo <" + val_peek(5).sval + "> nao declarado ");
											}
              }
break;
case 27:
//#line 91 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null)
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                        }
break;
case 29:
//#line 99 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null)
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                        }
break;
case 42:
//#line 129 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
          yyerror("Expressao deve ser booleana");}
          }
break;
case 54:
//#line 153 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
      yyerror("Expressao deve ser booleana");}
      }
break;
case 60:
//#line 167 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 61:
//#line 168 "exemploSem.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 62:
//#line 169 "exemploSem.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 63:
//#line 170 "exemploSem.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 64:
//#line 171 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 65:
//#line 172 "exemploSem.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 66:
//#line 173 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 67:
//#line 174 "exemploSem.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 68:
//#line 175 "exemploSem.y"
{ yyval.obj = validaTipo(LESSEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 69:
//#line 176 "exemploSem.y"
{ yyval.obj = validaTipo(GREATEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 70:
//#line 177 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 71:
//#line 178 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 72:
//#line 179 "exemploSem.y"
{ yyval.obj = Tp_INT;}
break;
case 73:
//#line 180 "exemploSem.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 74:
//#line 181 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 75:
//#line 182 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 76:
//#line 183 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                 if (nodo == null)
	                        yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada");
                      else
			                    yyval.obj = nodo.getTipo();
			            }
break;
case 77:
//#line 191 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(3).sval);
    	             if (nodo == null)
	                     yyerror("(sem) var <" + val_peek(3).sval + "> nao declarada");
                   else
                       yyval.obj = validaTipo('[', nodo, (TS_entry)val_peek(1).obj);
						     }
break;
case 78:
//#line 197 "exemploSem.y"
{yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 80:
//#line 201 "exemploSem.y"
{ System.out.println("veio no metodo");}
break;
//#line 1061 "Parser.java"
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
